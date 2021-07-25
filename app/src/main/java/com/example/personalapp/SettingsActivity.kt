package com.example.personalapp

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_settings.*
import java.util.*

class SettingsActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    var NOTIFICATION_ID = 0
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    val channelId = "i.apps.notifications"
    val description = "Test notification"
    private val REQUEST_CODE = 100
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

//        tv_current_time.text = Date().toString()

        // Creating the pending intent to send to the BroadcastReceiver
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MyAlarmReceiver(getString(R.string.notification_message))::class.java)
        pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        alarmToggle.setOnCheckedChangeListener(this)

    }

//    override fun onDestroy() {
//        super.onDestroy()
//        // Cancels the pendingIntent if it is no longer needed after this activity is destroyed.
//        alarmManager.cancel(pendingIntent)
//    }

    private fun deliverNotification() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.notif_burger)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Alarm is set")
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
        } else {

            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.notif_burger)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Alarm is Set")
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
        }
        notificationManager.notify(1234, builder.build())
    }

//    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager


    override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
        if(isChecked){
            setAnAlarm(15000)
            deliverNotification()
            setAlarm()
        }
        else{
            notificationManager.cancelAll()
            alarmManager.cancel(pendingIntent)
        }
    }

    private fun setAlarm() {
        // Setting the specific time for the alarm manager to trigger the intent, in this example, the alarm is set to go off at 23:30, update the time according to your need
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 19)
        calendar.set(Calendar.MINUTE, 45)

        // Starts the alarm manager
        alarmManager.setRepeating(
            AlarmManager.RTC,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }


    private fun setAnAlarm(i: Int) {

    }
}