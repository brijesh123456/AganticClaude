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

class SixthCrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SixthCrashScreen()
        }
    }
}

@Composable
fun SixthCrashScreen() {

    val context = LocalContext.current  // ✅ Get context for navigation

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔴 First Crash Button
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from SixthCrashActivity - Button 1")
                    setCustomKey("crash_button", "SixthActivity_Button1")
                }

                throw RuntimeException("Crash from SixthCrashActivity Button 1")
            }
        ) {
            Text("Crash Button 1")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔵 Open SeventhCrashActivity
        Button(
            onClick = {
                context.startActivity(
                    Intent(context, SeventhCrashActivity::class.java)
                )
            }
        ) {
            Text("Open Seventh Crash Activity")
        }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // 🟢 Third Crash Button → Divide by Zero Crash
    Button(
        onClick = {
            FirebaseCrashlytics.getInstance().apply {
                log("Crash from ThirdCrashActivity - Button 3 (Divide by Zero)")
                setCustomKey("crash_button", "ThirdActivity_Button3")
            }

            // ⚠️ Force crash: divide by zero
            val crash = 10 / 0
        }
    ) {
        Text("Crash Button 3 (Divide by Zero)")
    }
}