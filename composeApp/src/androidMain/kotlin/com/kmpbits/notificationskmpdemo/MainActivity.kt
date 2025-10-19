package com.kmpbits.notificationskmpdemo

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kmpbits.notificationskmpdemo.di.initKoin
import com.kmpbits.notificationskmpdemo.platform.PlatformActivity
import org.koin.android.ext.koin.androidContext

class ComposeApp: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@ComposeApp)
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val platformActivity = PlatformActivity(this)

        setContent {
            App(platformActivity)
        }
    }
}