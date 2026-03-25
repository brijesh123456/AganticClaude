package com.aganticclaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.unit.dp
import com.google.firebase.crashlytics.FirebaseCrashlytics

class ThirteenthCrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ThirteenthCrashScreen()
        }
    }
}

@Composable
fun ThirteenthCrashScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Thirteenth Crash Test Menu",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 1. OutOfMemoryError
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from ThirteenthCrashActivity - OutOfMemoryError")
                    setCustomKey("crash_type", "OutOfMemoryError")
                }
                val list = ArrayList<ByteArray>()
                while (true) {
                    list.add(ByteArray(1024 * 1024)) // Allocate 1MB chunks until OOM
                }
            }
        ) {
            Text("1. Out Of Memory Error")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2. ConcurrentModificationException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from ThirteenthCrashActivity - ConcurrentModificationException")
                    setCustomKey("crash_type", "ConcurrentModificationException")
                }
                val list = mutableListOf(1, 2, 3, 4, 5)
                for (item in list) {
                    list.remove(item) // Modify list while iterating
                }
            }
        ) {
            Text("2. Concurrent Modification Exception")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 3. StringIndexOutOfBoundsException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from ThirteenthCrashActivity - StringIndexOutOfBoundsException")
                    setCustomKey("crash_type", "StringIndexOutOfBoundsException")
                }
                val str = "Hello"
                val ch = str[100] // Access index beyond string length
            }
        ) {
            Text("3. String Index Out Of Bounds Exception")
        }
    }
}
