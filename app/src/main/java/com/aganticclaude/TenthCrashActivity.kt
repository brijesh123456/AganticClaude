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

class TenthCrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TenthCrashScreen()
        }
    }
}

@Composable
fun TenthCrashScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔴 Crash 1 → NullPointerException
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from TenthCrashActivity - NullPointerException")
                    setCustomKey("crash_type", "NullPointerException")
                }

                val text: String? = null
                val length = text!!.length   // ❌ Force NullPointerException
            }
        ) {
            Text("Crash - Null Pointer")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔵 Crash 2 → IndexOutOfBoundsException
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from TenthCrashActivity - IndexOutOfBounds")
                    setCustomKey("crash_type", "IndexOutOfBoundsException")
                }

                val list = listOf(1, 2, 3)
                val crash = list[5]   // ❌ Force IndexOutOfBoundsException
            }
        ) {
            Text("Crash - Index Out Of Bounds")
        }
    }
}