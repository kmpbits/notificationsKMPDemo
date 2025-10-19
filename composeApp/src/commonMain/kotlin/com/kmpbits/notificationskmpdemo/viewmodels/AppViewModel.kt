package com.kmpbits.notificationskmpdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmpbits.notificationskmpdemo.getPlatform
import com.kmpbits.notificationskmpdemo.platform.PlatformActivity
import com.kmpbits.notificationskmpdemo.services.NotificationService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val notificationService: NotificationService
) : ViewModel() {

    private val _notificationEnabledState = MutableStateFlow(false)
    val notificationEnabledState = _notificationEnabledState.asStateFlow()

    init {
        viewModelScope.launch {
            _notificationEnabledState.value = notificationService.areNotificationsEnabled()
        }
    }

    fun askNotificationPermission(activity: PlatformActivity) {
        notificationService.requestPermission(activity) {
            _notificationEnabledState.value = it
        }
    }

    fun showNotification() {
        notificationService.showNotification(
            title = "Hello, ${getPlatform().name}!",
            message = "This is a notification message."
        )
    }
}