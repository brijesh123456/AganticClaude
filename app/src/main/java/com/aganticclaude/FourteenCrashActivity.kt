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

class FourteenCrashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FourteenCrashScreen()
        }
    }
}

@Composable
fun FourteenCrashScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Crash Test Menu 14",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // 1. ConcurrentModificationException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                try {
                    FirebaseCrashlytics.getInstance().apply {
                        log("Crash from FourteenCrashActivity - ConcurrentModificationException")
                        setCustomKey("crash_type", "ConcurrentModificationException")
                    }
                    val list = java.util.ArrayList<Int>()
                    list.addAll(listOf(1, 2, 3, 4, 5))
                    val iter = list.iterator()
                    iter.next() // advance iterator
                    list.add(6) // modify backing list directly, not via iterator
                    iter.next() // throws ConcurrentModificationException
                } catch (e: Exception) {
                    FirebaseCrashlytics.getInstance().recordException(e)
                }
            }
        ) {
            Text("1. Concurrent Modification Exception")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2. StringIndexOutOfBoundsException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                try {
                    FirebaseCrashlytics.getInstance().apply {
                        log("Crash from FourteenCrashActivity - StringIndexOutOfBoundsException")
                        setCustomKey("crash_type", "StringIndexOutOfBoundsException")
                    }
                    val str = "Hello"
                    val ch = str.getOrNull(100) ?: ' '
                } catch (e: Exception) {
                    FirebaseCrashlytics.getInstance().recordException(e)
                }
            }
        ) {
            Text("2. String Index Out Of Bounds")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 3. OutOfMemoryError
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                try {
                    FirebaseCrashlytics.getInstance().apply {
                        log("Crash from FourteenCrashActivity - OutOfMemoryError")
                        setCustomKey("crash_type", "OutOfMemoryError")
                    }
                    val arr = IntArray(Int.MAX_VALUE)
                } catch (e: Throwable) {
                    FirebaseCrashlytics.getInstance().recordException(e)
                }
            }
        ) {
            Text("3. Out Of Memory Error")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 4. IllegalArgumentException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                try {
                    FirebaseCrashlytics.getInstance().apply {
                        log("Crash from FourteenCrashActivity - IllegalArgumentException")
                        setCustomKey("crash_type", "IllegalArgumentException")
                    }
                    require(false) { "Invalid argument provided" }
                } catch (e: Exception) {
                    FirebaseCrashlytics.getInstance().recordException(e)
                }
            }
        ) {
            Text("4. Illegal Argument Exception")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 5. NoSuchElementException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                try {
                    FirebaseCrashlytics.getInstance().apply {
                        log("Crash from FourteenCrashActivity - NoSuchElementException")
                        setCustomKey("crash_type", "NoSuchElementException")
                    }
                    val emptyList = emptyList<Int>()
                    val first = emptyList.firstOrNull() ?: throw NoSuchElementException("List is empty")
                } catch (e: Exception) {
                    FirebaseCrashlytics.getInstance().recordException(e)
                }
            }
        ) {
            Text("5. No Such Element Exception")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 6. UnsupportedOperationException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                try {
                    FirebaseCrashlytics.getInstance().apply {
                        log("Crash from FourteenCrashActivity - UnsupportedOperationException")
                        setCustomKey("crash_type", "UnsupportedOperationException")
                    }
                    val readOnlyList = listOf(1, 2, 3)
                    (readOnlyList as MutableList<Int>).add(4)
                } catch (e: Exception) {
                    FirebaseCrashlytics.getInstance().recordException(e)
                }
            }
        ) {
            Text("6. Unsupported Operation Exception")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 7. NegativeArraySizeException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                try {
                    FirebaseCrashlytics.getInstance().apply {
                        log("Crash from FourteenCrashActivity - NegativeArraySizeException")
                        setCustomKey("crash_type", "NegativeArraySizeException")
                    }
                    val arr = arrayOfNulls<Int>(-1)
                } catch (e: Throwable) {
                    FirebaseCrashlytics.getInstance().recordException(e)
                }
            }
        ) {
            Text("7. Negative Array Size Exception")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 8. TypeCastException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                try {
                    FirebaseCrashlytics.getInstance().apply {
                        log("Crash from FourteenCrashActivity - TypeCastException")
                        setCustomKey("crash_type", "TypeCastException")
                    }
                    val value: Any = "I am a String"
                    val num = value as Int
                } catch (e: Exception) {
                    FirebaseCrashlytics.getInstance().recordException(e)
                }
            }
        ) {
            Text("8. Type Cast Exception")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 9. IndexOutOfBoundsException
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                try {
                    FirebaseCrashlytics.getInstance().apply {
                        log("Crash from FourteenCrashActivity - IndexOutOfBoundsException")
                        setCustomKey("crash_type", "IndexOutOfBoundsException")
                    }
                    val list = listOf("a", "b", "c")
                    val item = list.getOrNull(99) ?: 0
                } catch (e: Exception) {
                    FirebaseCrashlytics.getInstance().recordException(e)
                }
            }
        ) {
            Text("9. Index Out Of Bounds Exception")
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 10. AssertionError
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                try {
                    FirebaseCrashlytics.getInstance().apply {
                        log("Crash from FourteenCrashActivity - AssertionError")
                        setCustomKey("crash_type", "AssertionError")
                    }
                    val value = 42
                    assert(value == 0) { "Assertion failed: value must be 0" }
                } catch (e: Throwable) {
                    FirebaseCrashlytics.getInstance().recordException(e)
                }
            }
        ) {
            Text("10. Assertion Error")
        }
    }
}
