package com.example.personalapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.random.Random

class LoginActivity : AppCompatActivity() {

    var NOTIFICATION_ID = 0
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    val channelId = "i.apps.notifications"
    val description = "Test notification"
    lateinit var otp : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bt_otp.setOnClickListener {

            val username = et_username.text;
            val password = et_password.text;

            if(username.toString().trim()=="pritam".trim()&&password.toString().trim()=="abc".trim()){
                deliverNotification()
                tv_username.setVisibility(View.INVISIBLE)
                tv_password.setVisibility(View.INVISIBLE)
                et_otp.setVisibility(View.VISIBLE)
                et_username.setVisibility(View.INVISIBLE)
                et_password.setVisibility(View.INVISIBLE)
                et_otp.setVisibility(View.VISIBLE)
                bt_otp.setVisibility(View.INVISIBLE)
                bt_login.setVisibility(View.VISIBLE)
            }
            else{
                Toast.makeText(this, "Please Enter valid Username/Password", Toast.LENGTH_SHORT).show()
            }

        }

        bt_login.setOnClickListener{
            if(et_otp.text.toString().trim()==otp.trim()){
                startActivity(Intent(this,MainActivity::class.java))
                tv_username.setVisibility(View.VISIBLE)
                tv_password.setVisibility(View.VISIBLE)
                et_otp.setVisibility(View.INVISIBLE)
                et_username.setVisibility(View.VISIBLE)
                et_password.setVisibility(View.VISIBLE)
                et_otp.setVisibility(View.INVISIBLE)
                bt_otp.setVisibility(View.VISIBLE)
                bt_login.setVisibility(View.INVISIBLE)
            }
            else{
                Toast.makeText(this, "Please Enter valid OTP", Toast.LENGTH_SHORT).show()
            }
        }

    }



    private fun deliverNotification() {
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        otp = Random.nextInt(1000, 9999).toString()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("abc")
                .setContentText("Your OTP is " + otp)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
//                .addAction(R.drawable.ic_baseline_copy)
        } else {

            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("abc")
                .setContentText("Your OTP is " + otp)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher_background))
        }
        notificationManager.notify(1234, builder.build())
    }
}