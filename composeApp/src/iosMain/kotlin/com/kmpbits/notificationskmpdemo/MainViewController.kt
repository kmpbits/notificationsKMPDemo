package com.kmpbits.notificationskmpdemo

import androidx.compose.ui.window.ComposeUIViewController
import com.kmpbits.notificationskmpdemo.platform.PlatformActivity

fun MainViewController() = ComposeUIViewController { App(PlatformActivity()) }