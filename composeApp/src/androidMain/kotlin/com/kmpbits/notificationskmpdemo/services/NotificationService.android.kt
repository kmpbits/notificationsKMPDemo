package com.kmpbits.notificationskmpdemo.services

import com.kmpbits.notificationskmpdemo.platform.PlatformActivity
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.kmpbits.notificationskmpdemo.R
import com.kmpbits.notificationskmpdemo.utils.Constants

actual class NotificationService(
    private val context: Context
) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        createNotificationChannel()
    }

    actual fun showNotification(
        title: String,
        message: String?
    ) {

        val notification = NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .build()

        notificationManager.notify(100, notification)
    }

    actual fun requestPermission(
        activity: PlatformActivity
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(activity.activity.applicationContext, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_DENIED
            ) {
                ActivityCompat.requestPermissions(
                    activity.activity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    REQUEST_CODE_NOTIFICATIONS
                )
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.CHANNEL_ID,
                "Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationManager.createNotificationChannel(channel)
        }
    }

    companion object {
        // This is needed to check the result on MainActivity
        const val REQUEST_CODE_NOTIFICATIONS: Int = 1
    }

    actual suspend fun areNotificationsEnabled(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            notificationManager.areNotificationsEnabled()
        else
            NotificationManagerCompat.from(context).areNotificationsEnabled()
    }
}