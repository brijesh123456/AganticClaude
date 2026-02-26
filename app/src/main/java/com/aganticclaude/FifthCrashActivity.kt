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

class FifthCrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FifthCrashScreen()
        }
    }
}

@Composable
fun FifthCrashScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Crash using ArrayIndexOutOfBoundsException
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from FifthCrashActivity - ArrayIndexOutOfBounds")
                    setCustomKey("crash_type", "ArrayIndexOutOfBounds")
                }

                val list = listOf(1, 2, 3)
                val crash = list[10]  // This will throw ArrayIndexOutOfBoundsException
            }
        ) {
            Text("Crash With Array Index Error")
        }
    }
}