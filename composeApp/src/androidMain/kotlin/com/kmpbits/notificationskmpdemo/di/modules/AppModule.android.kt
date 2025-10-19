package com.kmpbits.notificationskmpdemo.di.modules

import com.kmpbits.notificationskmpdemo.services.NotificationService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val serviceModule = module {
    singleOf(::NotificationService)
}