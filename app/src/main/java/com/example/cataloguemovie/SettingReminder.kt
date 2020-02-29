package com.example.cataloguemovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cataloguemovie.Reminder.EightMorningReminder
import com.example.cataloguemovie.Reminder.SevenMorningReminder
import kotlinx.android.synthetic.main.activity_setting_reminder.*

class SettingReminder : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_reminder)

//        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        toolbar.setNavigationOnClickListener { finish() }

        val dailyReminder = SevenMorningReminder()
        dailyReminderSwitch.isChecked = dailyReminder.isAlarmSet(this)

        val releaseReminder = EightMorningReminder()
        releaseTodaySwitch.isChecked = releaseReminder.isAlarmSet(this)

        dailyReminderSwitch.setOnCheckedChangeListener { _, isChecked ->
            Log.d("test", isChecked.toString())
            if (isChecked){
                Toast.makeText(this, getString(R.string.toast_rem), Toast.LENGTH_LONG).show()
                dailyReminder.setRepeatingAlarm(this)
            }else{
                Toast.makeText(this, getString(R.string.toas_no_rem), Toast.LENGTH_LONG).show()
                dailyReminder.cancelAlarm(this)
            }
        }

        releaseTodaySwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                releaseReminder.setRepeatingAlarm(this)
            } else {
                releaseReminder.cancelAlarm(this)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
