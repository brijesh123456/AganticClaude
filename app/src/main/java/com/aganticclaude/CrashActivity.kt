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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CrashScreen()
        }
    }

    // ✅ Method for reporting non-fatal errors
    fun reportNonFatalError(message: String) {
        FirebaseCrashlytics.getInstance().apply {
            log("Non-fatal error reported: $message")
            recordException(Exception(message))
        }
    }
}

@Composable
fun CrashScreen() {

    val context = LocalContext.current
    val activity = context as CrashActivity

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔴 Open ThirdCrashActivity
        Button(
            onClick = {
                context.startActivity(
                    Intent(context, ThirdCrashActivity::class.java)
                )
            }
        ) {
            Text("Open Third Crash Activity")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔵 Crash Button (Fatal)
        // 🔶 NEW → ClassCastException Crash
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from CrashActivity - ClassCastException")
                    setCustomKey("crash_type", "ClassCastException")
                }

                val obj: Any = "This is a String"
                val number = obj as Int   // ❌ Force ClassCastException
            }
        ) {
            Text("Crash - Class Cast Exception")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🟢 Report Non-Fatal Error
        Button(
            onClick = {
                activity.reportNonFatalError("Manual non-fatal test error")
            }
        ) {
            Text("Report Non-Fatal Error")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🟣 Open EighthCrashActivity
        Button(
            onClick = {
                context.startActivity(
                    Intent(context, EighthCrashActivity::class.java)
                )
            }
        ) {
            Text("Open Eighth Crash Activity")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🟡 NEW → Open NinthCrashActivity
        Button(
            onClick = {
                context.startActivity(
                    Intent(context, NinthCrashActivity::class.java)
                )
            }
        ) {
            Text("Open Ninth Crash Activity")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from CrashActivity - DivideByZero")
                    setCustomKey("crash_type", "ArithmeticException")
                }

                val crash = 100 / 0  // Force ArithmeticException
            }
        ) {
            Text("Crash - Divide by Zero")
        }
    }
}