package com.aganticclaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import com.google.firebase.crashlytics.FirebaseCrashlytics

class EighthCrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EighthCrashScreen()
        }
    }
}

@Composable
fun EighthCrashScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔴 Crash Button
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from EighthCrashActivity")
                    setCustomKey("crash_screen", "EighthCrashActivity")
                }

                throw RuntimeException("Fatal crash from EighthCrashActivity")
            }
        ) {
            Text("Crash in Eighth Activity")
        }
    }
}