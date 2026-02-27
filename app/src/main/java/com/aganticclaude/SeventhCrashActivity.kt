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

class SeventhCrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SeventhCrashScreen()
        }
    }
}

@Composable
fun SeventhCrashScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔴 Crash 1 → NullPointerException
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from SeventhCrashActivity - NullPointerException")
                    setCustomKey("crash_type", "NullPointerException")
                }

                val text: String? = null
                text!!.length   // Force NullPointerException
            }
        ) {
            Text("Crash - Null Pointer")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔵 Crash 2 → IllegalStateException
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from SeventhCrashActivity - IllegalStateException")
                    setCustomKey("crash_type", "IllegalStateException")
                }

                throw IllegalStateException("Illegal state triggered in SeventhCrashActivity")
            }
        ) {
            Text("Crash - Illegal State")
        }
    }
}