package com.xpmw.aplictionnotification

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

lateinit var notificationChannel: Notification
lateinit var notificationManager: NotificationManager
lateinit var builder: NotificationCompat.Builder

fun Context.showNotification(channelId: String, title: String, body: String){
    notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val intent = Intent(this, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
}