package com.aganticclaude

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.aganticclaude.ui.theme.AganticClaudeTheme
import com.google.firebase.crashlytics.FirebaseCrashlytics

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AganticClaudeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Hello Android!")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                FirebaseCrashlytics.getInstance().log("Crash test initiated")

                Handler(Looper.getMainLooper()).postDelayed({
                    val list = listOf(1, 2)
                    val crash = list[5]   // FATAL
                }, 3000) // 3 seconds delay
            }
        ) {
            Text(text = "Click Me")
        }
    }
}
