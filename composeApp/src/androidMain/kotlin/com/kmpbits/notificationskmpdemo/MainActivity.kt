package com.kmpbits.notificationskmpdemo

import android.app.Application
import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.kmpbits.notificationskmpdemo.di.initKoin
import com.kmpbits.notificationskmpdemo.events.NotificationPermissionType
import com.kmpbits.notificationskmpdemo.events.NotificationStateEvent
import com.kmpbits.notificationskmpdemo.platform.PlatformActivity
import com.kmpbits.notificationskmpdemo.services.NotificationService
import kotlinx.coroutines.launch
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
        deviceId: Int
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId)

        if (requestCode == NotificationService.REQUEST_CODE_NOTIFICATIONS) {
            NotificationStateEvent.send(
                if (grantResults.isNotEmpty() && grantResults[0] == 0)
                    NotificationPermissionType.GRANTED
                else
                    NotificationPermissionType.DENIED
            )
        }
    }
}