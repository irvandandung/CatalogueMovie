package com.example.cataloguemovie.Widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import com.example.cataloguemovie.BuildConfig
import com.example.cataloguemovie.Movies.Db.RoomMovieConnect
import com.example.cataloguemovie.R
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class MovieBanner : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

//    override fun onEnabled(context: Context) {
//        // Enter relevant functionality for when the first widget is created
//    }
//
//    override fun onDisabled(context: Context) {
//        // Enter relevant functionality for when the last widget is disabled
//    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action != null){
            if (intent.action == TOAST_ACTION) {
                val viewIndex = intent.getIntExtra(EXTRA_ITEM, 0)
                Toast.makeText(context, "Touched view $viewIndex", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val TOAST_ACTION = "com.example.cataloguemovie.TOAST_ACTION"
        const val EXTRA_ITEM = "com.example.cataloguemovie.EXTRA_ITEM"
        private fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val intent = Intent(context, StackWidgetService::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()

//            val widgetText = context.getString(R.string.appwidget_text)
            // Construct the RemoteViews object
            val views = RemoteViews(context.packageName, R.layout.movie_banner)
//            views.setTextViewText(R.id.appwidget_text, widgetText)
            views.setRemoteAdapter(R.id.stack_view, intent)
            views.setEmptyView(R.id.stack_view, R.id.empty_view)

            val toastIntent = Intent(context, MovieBanner::class.java)
            toastIntent.action = TOAST_ACTION
            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()
            val toastPendingIntent = PendingIntent.getBroadcast(context, 0, toastIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            views.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent)
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }
}

internal class StackRemoteViewsFactory(private val mContext: Context) : RemoteViewsService.RemoteViewsFactory {
    private val mWidgetItems = arrayListOf<Bitmap>()

    override fun onCreate() {

    }

    private fun getBitmapFromURL(src: String): Bitmap? {
        return try {
            val url = URL(src)
            val connection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input = connection.getInputStream()
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            // Log exception
            null
        }
    }

    override fun getLoadingView(): RemoteViews? = null
    override fun getItemId(p0: Int): Long = 0

    override fun onDataSetChanged() {
        val movieDao = RoomMovieConnect.getInstance(mContext)
        val listmovie = movieDao?.movieDao()?.alldatamoviewidget()
        if (listmovie!!.size > 0){
            for (i in 0 until listmovie.size){
                getBitmapFromURL("${BuildConfig.URL_POSTER}${listmovie[i].backdropPath}")?.let{mWidgetItems.add(it)}
            }
        }

    }

    override fun hasStableIds(): Boolean = false

    override fun getViewAt(p0: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.widget_item)
        rv.setImageViewBitmap(R.id.imageView, mWidgetItems[p0])
        val extras = bundleOf(
            MovieBanner.EXTRA_ITEM to p0
        )
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)
        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent)
        return rv
    }

    override fun getCount(): Int = mWidgetItems.size

    override fun getViewTypeCount(): Int = 1

    override fun onDestroy() {
    }
}

class StackWidgetService:RemoteViewsService(){
    override fun onGetViewFactory(p0: Intent?): RemoteViewsFactory {
        return StackRemoteViewsFactory(this.applicationContext)
    }
}