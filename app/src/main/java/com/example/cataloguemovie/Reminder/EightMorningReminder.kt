package com.example.cataloguemovie.Reminder

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.cataloguemovie.Api.ApiServices
import com.example.cataloguemovie.Api.Retrofitbuilder
import com.example.cataloguemovie.Helper.getCurrentDate
import com.example.cataloguemovie.Movies.Data.DataMovies
import com.example.cataloguemovie.Movies.Data.Movies
import com.example.cataloguemovie.R
import com.example.cataloguemovie.ReleaseToday.MainReleaseActivity
import retrofit2.Call
import retrofit2.Response
import java.util.*

class EightMorningReminder : BroadcastReceiver(){
    private var apidiscover = Retrofitbuilder().createreleasemovie(ApiServices::class.java)
    private val REQUEST_CODE = 102
    override fun onReceive(context : Context?, p1: Intent?) {
        val title = context?.getString(R.string.eightthemorning)
        val message = context?.getString(R.string.eightthemorningmessage)
        val notifId = REQUEST_CODE
        getRealesemovie(context, title, message, notifId)
    }

    private fun getRealesemovie(context: Context?, title: String?, message: String?, notifId: Int) {
        apidiscover.getMovieReleaseToday(language = "en-US", releasegte = getCurrentDate(), releaselte = getCurrentDate()).enqueue(object : retrofit2.Callback<DataMovies>{
            override fun onFailure(call: Call<DataMovies>, t: Throwable) {
                Log.d("ErrorRealeseMovie", t.message+"\n ora dadi")
            }

            override fun onResponse(call: Call<DataMovies>, response: Response<DataMovies>) {
                showNotification(context, title, message, notifId, response.body())
            }
        })
    }

    private fun showNotification(
        context: Context?,
        title: String?,
        message: String?,
        notifId: Int,
        list : DataMovies?
    ) {

        val CHANNEL_ID = "CHANNEL_EIGHT"
        val CHANNEL_NAME = "8 in the morning notification"
        val listMovie : List<Movies>
        var content =""
        listMovie = list?.movie!!
        listMovie.map {
            content += "${it.title}\n"
        }
        val notificationIntent = Intent(context, MainReleaseActivity::class.java)
        notificationIntent.putExtra(MainReleaseActivity.DATE_REALESE, getCurrentDate())
        val pendingIntent = TaskStackBuilder.create(context)
            .addParentStack(MainReleaseActivity::class.java)
            .addNextIntent(notificationIntent)
            .getPendingIntent(110, PendingIntent.FLAG_UPDATE_CURRENT)

        val notificationManagerCompat = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setSmallIcon(R.drawable.ic_live_tv_black_24dp)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_live_tv_black_24dp))
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("*"+message+"*\n"+content))
            .setColor(ContextCompat.getColor(context, android.R.color.black))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)

            builder.setChannelId(CHANNEL_ID)

            notificationManagerCompat.createNotificationChannel(channel)

        }

        val notification = builder.build()

        notificationManagerCompat.notify(notifId, notification)
    }

    fun setRepeatingAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, EightMorningReminder::class.java)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 8)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, 0)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent)
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, EightMorningReminder::class.java)

        val pendingIntent = PendingIntent.getBroadcast(context, REQUEST_CODE, intent, 0)
        pendingIntent.cancel()

        alarmManager.cancel(pendingIntent)
    }

    fun isAlarmSet(context: Context): Boolean {
        val intent = Intent(context, EightMorningReminder::class.java)

        return PendingIntent.getBroadcast(context, REQUEST_CODE, intent, PendingIntent.FLAG_NO_CREATE) != null
    }

}