package com.kmpbits.notificationskmpdemo.services

import com.kmpbits.notificationskmpdemo.events.NotificationPermissionType
import com.kmpbits.notificationskmpdemo.events.NotificationStateEvent
import com.kmpbits.notificationskmpdemo.platform.PlatformActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSUUID
import platform.UIKit.UIApplication
import platform.UIKit.registerForRemoteNotifications
import platform.UserNotifications.UNAuthorizationOptionAlert
import platform.UserNotifications.UNAuthorizationOptionBadge
import platform.UserNotifications.UNAuthorizationOptionSound
import platform.UserNotifications.UNAuthorizationStatusAuthorized
import platform.UserNotifications.UNMutableNotificationContent
import platform.UserNotifications.UNNotificationRequest
import platform.UserNotifications.UNNotificationSound.Companion.defaultSound
import platform.UserNotifications.UNUserNotificationCenter

actual class NotificationService {

    private val notificationCenter = UNUserNotificationCenter.currentNotificationCenter()

    actual fun showNotification(
        title: String,
        message: String?
    ) {
        val content = UNMutableNotificationContent().apply {
            setTitle(title)
            message?.let {
                setBody(it)
            }
            setSound(defaultSound)
        }

        val request = UNNotificationRequest.requestWithIdentifier(
            identifier = NSUUID().UUIDString(),
            content = content,
            trigger = null
        )

        notificationCenter.addNotificationRequest(request) {
            // Do something with the error
            print(it)
        }
    }

    actual fun requestPermission(
        activity: PlatformActivity
    ) {
        val center = UNUserNotificationCenter.currentNotificationCenter()
        center.requestAuthorizationWithOptions(
            options = UNAuthorizationOptionSound or UNAuthorizationOptionAlert or UNAuthorizationOptionBadge,
            completionHandler = { granted, error ->
                if (error == null) {
                    UIApplication.sharedApplication.registerForRemoteNotifications()
                    NotificationStateEvent.send(NotificationPermissionType.GRANTED)
                } else {
                    NotificationStateEvent.send(NotificationPermissionType.DENIED)
                }
            }
        )
    }

    actual suspend fun areNotificationsEnabled(): Boolean {
        return suspendCancellableCoroutine { continuation ->
            notificationCenter.getNotificationSettingsWithCompletionHandler { settings ->
                continuation.resumeWith(Result.success(settings?.authorizationStatus == UNAuthorizationStatusAuthorized))
            }
        }
    }
}