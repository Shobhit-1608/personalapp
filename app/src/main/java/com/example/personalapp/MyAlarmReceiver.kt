package com.example.personalapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyAlarmReceiver(final var msg: String) : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

}
