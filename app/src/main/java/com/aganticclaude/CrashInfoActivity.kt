package com.aganticclaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aganticclaude.ui.theme.AganticClaudeTheme
import com.google.firebase.crashlytics.FirebaseCrashlytics

class CrashInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val crashId = intent.getIntExtra("crash_id", 0)
        val crash = crashItems.getOrElse(crashId) { crashItems[0] }
        setContent {
            AganticClaudeTheme(darkTheme = false) {
                CrashDetailScreen(
                    crash = crash,
                    onNavigateBack = { finish() },
                    onTriggerCrash = { triggerCrash(crash) }
                )
            }
        }
    }

    private fun triggerCrash(crash: CrashInfo) {
        FirebaseCrashlytics.getInstance().apply {
            log("Crash triggered from CrashDetailActivity")
            setCustomKey("crash_type", crash.name)
        }
        when (crash.id) {
            0 -> {
                val text: String? = null
                val length = text!!.length
            }
            1 -> {
                val arr = intArrayOf(1, 2, 3)
                val value = arr[10]
            }
            2 -> {
                val obj: Any = "This is a String"
                val number = obj as Int
            }
            3 -> {
                val result = 100 / 0
            }
            4 -> {
                fun recurse(): Int = recurse() + 1
                recurse()
            }
            5 -> {
                val number = "NotANumber".toInt()
            }
            6 -> {
                throw IllegalStateException("Forced IllegalStateException from CrashDetailActivity")
            }
            7 -> {
                throw RuntimeException("Forced RuntimeException from CrashDetailActivity")
            }
            8 -> {
                // Simulate ANR by blocking the main thread
                Thread.sleep(6000)
            }
            9 -> {
                throw RuntimeException("Forced fatal crash: onFatalCrash alert simulation")
            }
            10 -> {
                FirebaseCrashlytics.getInstance().recordException(
                    RuntimeException("Non-fatal crash recorded: onNonFatalCrash alert simulation")
                )
                throw RuntimeException("Forced crash after non-fatal record: onNonFatalCrash")
            }
            11 -> {
                throw RuntimeException("Regression detected: onRegression alert simulation")
            }
            12 -> {
                throw RuntimeException("Stability digest crash: onStabilityDigest alert simulation")
            }
            13 -> {
                FirebaseCrashlytics.getInstance().recordException(
                    RuntimeException("Velocity spike alert: onVelocityAlert alert simulation")
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrashDetailScreen(
    crash: CrashInfo,
    onNavigateBack: () -> Unit,
    onTriggerCrash: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = crash.name,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F6F8))
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Icon circle
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .background(crash.iconColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = crash.iconLabel,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = crash.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = crash.shortDesc,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Description card
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "What causes this crash?",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = crash.fullDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onTriggerCrash,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
            ) {
                Text(
                    text = "Trigger Crash",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Warning: this will crash the app and log to Firebase Crashlytics",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
