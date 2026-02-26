
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
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 🔴 First Button
        Button(
            onClick = {
                FirebaseCrashlytics.getInstance()
                    .log("Crash test from First Button")

                throw RuntimeException("Test Crash from First Button")
            }
        ) {
            Text("Crash App")
        }

        Spacer(modifier = Modifier.height(20.dp))

//        // 🔵 Second Button
//        SecondaryCrashButton()
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        // 🟢 Third Button
//        ThirdCrashButton()
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        // 🟢 Fourth Button
//        FourthCrashButton()
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        // 🟢 Fifth Button
//        FifthCrashButton()
    }
}

//@Composable
//fun SecondaryCrashButton() {
//    Button(
//        onClick = {
//            FirebaseCrashlytics.getInstance().apply {
//                log("Crash triggered from SecondaryCrashButton")
//                setCustomKey("crash_source", "Second Button")
//            }
//
//            throw RuntimeException("Test Crash from Second Button")
//        }
//    ) {
//        Text("Crash From Second Button")
//    }
//}
//
//@Composable
//fun ThirdCrashButton() {
//    Button(
//        onClick = {
//            FirebaseCrashlytics.getInstance().apply {
//                log("Crash triggered from ThirdCrashButton")
//                setCustomKey("crash_source", "Third Button")
//            }
//
//            throw RuntimeException("Test Crash from Third Button")
//        }
//    ) {
//        Text("Crash From Third Button")
//    }
//}
//
//@Composable
//fun FourthCrashButton() {
//    Button(
//        onClick = {
//            FirebaseCrashlytics.getInstance().apply {
//                log("Crash triggered from FourthCrashButton")
//                setCustomKey("crash_source", "Fourth Button")
//            }
//
//            throw RuntimeException("Test Crash from Fourth Button")
//        }
//    ) {
//        Text("Crash From Fourth Button")
//    }
//}
//
//@Composable
//fun FifthCrashButton() {
//    Button(
//        onClick = {
//            FirebaseCrashlytics.getInstance().apply {
//                log("Crash triggered from FifthCrashButton")
//                setCustomKey("crash_source", "Fifth Button")
//            }
//
//            throw RuntimeException("Test Crash from Fifth Button")
//        }
//    ) {
//        Text("Crash From Fifth Button")
//    }
//}