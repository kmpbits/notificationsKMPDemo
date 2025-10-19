package com.kmpbits.notificationskmpdemo.di

import com.kmpbits.notificationskmpdemo.di.modules.serviceModule
import com.kmpbits.notificationskmpdemo.di.modules.viewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            serviceModule,
            viewModelModule
        )
    }

@Suppress("unused") //using in iOS
fun initKoin() = initKoin {}