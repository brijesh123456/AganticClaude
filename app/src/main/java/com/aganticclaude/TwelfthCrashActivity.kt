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

class TwelfthCrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TwelfthCrashScreen()
        }
    }
}

@Composable
fun TwelfthCrashScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Crash Test Menu",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 1. NullPointerException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from TwelfthCrashActivity - NullPointerException")
                    setCustomKey("crash_type", "NullPointerException")
                }
                val text: String? = null
                val length = text!!.length
            }
        ) {
            Text("1. Null Pointer Exception")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2. ArrayIndexOutOfBoundsException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from TwelfthCrashActivity - ArrayIndexOutOfBoundsException")
                    setCustomKey("crash_type", "ArrayIndexOutOfBoundsException")
                }
                val arr = intArrayOf(1, 2, 3)
                val crash = arr[10]
            }
        ) {
            Text("2. Array Index Out Of Bounds")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 3. ClassCastException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from TwelfthCrashActivity - ClassCastException")
                    setCustomKey("crash_type", "ClassCastException")
                }
                val obj: Any = "This is a String"
                val number = obj as Int
            }
        ) {
            Text("3. Class Cast Exception")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 4. ArithmeticException (Divide by Zero)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from TwelfthCrashActivity - ArithmeticException")
                    setCustomKey("crash_type", "ArithmeticException")
                }
                val result = 100 / 0
            }
        ) {
            Text("4. Divide By Zero")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 5. StackOverflowError
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from TwelfthCrashActivity - StackOverflowError")
                    setCustomKey("crash_type", "StackOverflowError")
                }
                try {
                    fun recurse(): Int = recurse() + 1
                    recurse()
                } catch (e: StackOverflowError) {
                    FirebaseCrashlytics.getInstance().recordException(e)
                }
            }
        ) {
            Text("5. Stack Overflow")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 6. NumberFormatException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from TwelfthCrashActivity - NumberFormatException")
                    setCustomKey("crash_type", "NumberFormatException")
                }
                val number = "NotANumber".toInt()
            }
        ) {
            Text("6. Number Format Exception")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 7. IllegalStateException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from TwelfthCrashActivity - IllegalStateException")
                    setCustomKey("crash_type", "IllegalStateException")
                }
                throw IllegalStateException("Forced IllegalStateException from TwelfthCrashActivity")
            }
        ) {
            Text("7. Illegal State Exception")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 8. RuntimeException (Generic Fatal Crash)
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                FirebaseCrashlytics.getInstance().apply {
                    log("Crash from TwelfthCrashActivity - RuntimeException")
                    setCustomKey("crash_type", "RuntimeException")
                }
                throw RuntimeException("Forced fatal RuntimeException from TwelfthCrashActivity")
            }
        ) {
            Text("8. Runtime Exception (Fatal)")
        }
    }
}
