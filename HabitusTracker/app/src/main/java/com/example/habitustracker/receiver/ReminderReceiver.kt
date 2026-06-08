package com.example.habitustracker.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Aquí se mostraría la notificación local usando NotificationManager
        Log.d("ReminderReceiver", "¡Es hora de completar tus hábitos!")
    }
}
