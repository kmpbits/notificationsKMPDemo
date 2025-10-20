package com.kmpbits.notificationskmpdemo.events

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

object NotificationStateEvent {
    private val _event = Channel<NotificationPermissionType>()

    fun observe() = _event.receiveAsFlow()

    fun send(event: NotificationPermissionType) {
        _event.trySend(event)
    }
}

enum class NotificationPermissionType {
    GRANTED,
    DENIED,
}