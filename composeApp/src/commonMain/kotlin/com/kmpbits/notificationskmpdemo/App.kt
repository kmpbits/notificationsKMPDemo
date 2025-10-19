package com.kmpbits.notificationskmpdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmpbits.notificationskmpdemo.platform.PlatformActivity
import com.kmpbits.notificationskmpdemo.viewmodels.AppViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(activity: PlatformActivity) {
    val viewModel = koinViewModel<AppViewModel>()
    val notificationsEnabled by viewModel.notificationEnabledState.collectAsState()

    MaterialTheme {
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { viewModel.askNotificationPermission(activity) },
                enabled = !notificationsEnabled
            ) {
                Text("Ask for Permissions")
            }

            Button(
                onClick = { viewModel.showNotification() },
                enabled = notificationsEnabled
            ) {
                Text("Show notification")
            }
        }
    }
}