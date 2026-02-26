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

class FourthCrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FourthCrashScreen()
        }
    }
}

@Composable
fun FourthCrashScreen() {

    val context = LocalContext.current  // ✅ Get context to open new activity

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔴 First Crash Button
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from FourthCrashActivity - Button 1")
                    setCustomKey("crash_button", "FourthActivity_Button1")
                }

                throw RuntimeException("Crash from FourthCrashActivity Button 1")
            }
        ) {
            Text("Crash Button 1")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔵 Second Crash Button
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from FourthCrashActivity - Button 2")
                    setCustomKey("crash_button", "FourthActivity_Button2")
                }

                throw RuntimeException("Crash from FourthCrashActivity Button 2")
            }
        ) {
            Text("Crash Button 2")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🟢 Third Crash Button
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from FourthCrashActivity - Button 3")
                    setCustomKey("crash_button", "FourthActivity_Button3")
                }

                throw RuntimeException("Crash from FourthCrashActivity Button 3")
            }
        ) {
            Text("Crash Button 3")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔵 New Button → Open FifthCrashActivity
        Button(
            onClick = {
                context.startActivity(
                    Intent(context, FifthCrashActivity::class.java)
                )
            }
        ) {
            Text("Open Fifth Crash Activity")
        }
    }
}