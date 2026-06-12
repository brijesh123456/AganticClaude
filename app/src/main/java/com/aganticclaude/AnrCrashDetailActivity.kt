package com.aganticclaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aganticclaude.ui.theme.AganticClaudeTheme
import com.google.firebase.crashlytics.FirebaseCrashlytics

class AnrCrashDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val alert = crashItems.first { it.id == 8 }
        setContent {
            AganticClaudeTheme(darkTheme = false) {
                CrashDetailScreen(
                    crash = alert,
                    onNavigateBack = { finish() },
                    onTriggerCrash = { triggerAlert() }
                )
            }
        }
    }

    private fun triggerAlert() {
        FirebaseCrashlytics.getInstance().apply {
            log("Alert triggered: onAnrCrash")
            setCustomKey("alert_type", "onAnrCrash")
            setCustomKey("alert_category", "ANR")
        }
        // Simulate ANR by blocking the main thread
        Thread.sleep(6000)
    }
}
