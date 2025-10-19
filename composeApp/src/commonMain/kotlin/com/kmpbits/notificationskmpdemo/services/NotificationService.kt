package com.kmpbits.notificationskmpdemo.services

import com.kmpbits.notificationskmpdemo.platform.PlatformActivity

expect class NotificationService {

    fun showNotification(
        title: String,
        message: String?
    )

    fun requestPermission(
        activity: PlatformActivity,
        onFinished: (Boolean) -> Unit
    )

    suspend fun areNotificationsEnabled(): Boolean
}