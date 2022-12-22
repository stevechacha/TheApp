package com.chacha.theapp.core.service


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.chacha.theapp.R
import com.chacha.theapp.core.presentation.activities.MainActivity

fun createNotificationChannel(context: Context) {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    displayNotification(context = context, title = "", message = "")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = context.resources.getString(R.string.default_notification_channel_id)
        val descriptionText = context.resources.getString(R.string.fcm_channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(
            context.resources.getString(R.string.default_notification_channel_id),
             name,
            importance
        ).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun displayNotification(context: Context, title:String, message:String) {
    createNotificationChannel(context)

    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

    val notification = NotificationCompat.Builder(context, "my_channel_id")
        .setSmallIcon(R.drawable.account_circle)
        .setContentTitle(title)
        .setContentText(message)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setOnlyAlertOnce(true)
        .build()

    NotificationManagerCompat.from(context).notify(1, notification)
}
