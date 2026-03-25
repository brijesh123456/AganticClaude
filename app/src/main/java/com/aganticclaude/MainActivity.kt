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

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(
                onOpenCrash = { startActivity(Intent(this, CrashActivity::class.java)) },
                onOpenCrashMenu = { startActivity(Intent(this, TwelfthCrashActivity::class.java)) },
                onOpenThirteenthCrash = { startActivity(Intent(this, ThirteenthCrashActivity::class.java)) }
            )
        }
    }
}

@Composable
fun MainScreen(onOpenCrash: () -> Unit, onOpenCrashMenu: () -> Unit, onOpenThirteenthCrash: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onOpenCrash) {
            Text("Open Crash Screen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onOpenCrashMenu) {
            Text("Open Crash Test Menu")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onOpenThirteenthCrash) {
            Text("Open Thirteenth Crash Menu")
        }
    }
}