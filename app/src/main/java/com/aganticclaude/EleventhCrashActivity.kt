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

class EleventhCrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EleventhCrashScreen()
        }
    }
}

@Composable
fun EleventhCrashScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔴 Crash 1 → NumberFormatException
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from EleventhCrashActivity - NumberFormatException")
                    setCustomKey("crash_type", "NumberFormatException")
                }

                val number = "NotANumber".toInt()  // ❌ Force NumberFormatException
            }
        ) {
            Text("Crash - Number Format")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔵 Crash 2 → Kotlin NullPointerException (lateinit)
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from EleventhCrashActivity - UninitializedPropertyAccessException")
                    setCustomKey("crash_type", "UninitializedPropertyAccessException")
                }

                lateinit var name: String
                val length = name.length  // ❌ Force UninitializedPropertyAccessException
            }
        ) {
            Text("Crash - Lateinit Property")
        }
    }
}