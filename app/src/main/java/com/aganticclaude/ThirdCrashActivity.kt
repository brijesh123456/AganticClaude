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

class ThirdCrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThirdCrashScreen()
        }
    }
}

@Composable
fun ThirdCrashScreen() {

    // ✅ Get the Compose context
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔴 First Button → Open FourthCrashActivity
        Button(
            onClick = {
                context.startActivity(
                    Intent(context, FourthCrashActivity::class.java)
                )
            }
        ) {
            Text("Open Fourth Crash Activity")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🔵 Second Crash Button → RuntimeException
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from ThirdCrashActivity - Button 2")
                    setCustomKey("crash_button", "ThirdActivity_Button2")
                }

                throw RuntimeException("Crash from ThirdCrashActivity Button 2")
            }
        ) {
            Text("Crash Button 2")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 🟢 Third Crash Button → Divide by Zero Crash
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from ThirdCrashActivity - Button 3 (Divide by Zero)")
                    setCustomKey("crash_button", "ThirdActivity_Button3")
                }

                // ⚠️ Handled: divide by zero
                try {
                    val crash = 10 / 0
                } catch (e: ArithmeticException) {
                    FirebaseCrashlytics.getInstance().recordException(e)
                }
            }
        ) {
            Text("Crash Button 3 (Divide by Zero)")
        }
    }
}