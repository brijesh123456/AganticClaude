package com.aganticclaude

import android.content.Intent
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

class NinthCrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NinthCrashScreen(this)
        }
    }
}

@Composable
fun NinthCrashScreen(activity: ComponentActivity) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔴 Crash 1 → ClassCastException
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from NinthCrashActivity - ClassCastException")
                    setCustomKey("crash_type", "ClassCastException")
                }

                val obj: Any = "This is a String"
                val number = obj as Int
            }
        ) {
            Text("Crash - Class Cast Exception")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔵 Crash 2 → Divide by Zero
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from NinthCrashActivity - DivideByZero")
                    setCustomKey("crash_type", "ArithmeticException")
                }

                val crash = 100 / 0
            }
        ) {
            Text("Crash - Divide by Zero")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🟣 Crash 3 → IllegalStateException
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from NinthCrashActivity - IllegalStateException")
                    setCustomKey("crash_type", "IllegalStateException")
                }

                throw IllegalStateException("Forced Illegal State Crash")
            }
        ) {
            Text("Crash - Illegal State")
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 🟢 Open TenthCrashActivity
        Button(
            onClick = {
                activity.startActivity(
                    Intent(activity, TenthCrashActivity::class.java)
                )
            }
        ) {
            Text("Open Tenth Crash Activity")
        }

        Spacer(modifier = Modifier.height(30.dp))

        // 🟢 Open TenthCrashActivity
        Button(
            onClick = {
                activity.startActivity(
                    Intent(activity, EleventhCrashActivity::class.java)
                )
            }
        ) {
            Text("Open Eleventh Crash Activity")
        }
    }
}