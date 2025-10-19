package com.kmpbits.notificationskmpdemo.di.modules

import com.kmpbits.notificationskmpdemo.viewmodels.AppViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val serviceModule: Module

val viewModelModule = module {
    viewModelOf(::AppViewModel)
}