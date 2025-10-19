package com.kmpbits.notificationskmpdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform