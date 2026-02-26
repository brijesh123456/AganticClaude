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

    // ✅ New method for reporting non-fatal errors
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

        // 🔴 First Button → Open ThirdCrashActivity
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

        // 🔵 Second Button → Crash
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance()
                    .log("Crash from Second Button")

                throw RuntimeException("Crash from Second Button")
            }
        ) {
            Text("Crash From Second Button")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🟢 Third Button → Non-Fatal Error
        Button(
            onClick = {
                throw RuntimeException("Crash from Sixth Button")
            }
        ) {
            Text("Report Non-Fatal Error")
        }
    }
}