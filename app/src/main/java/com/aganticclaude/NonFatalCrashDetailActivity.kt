package com.aganticclaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aganticclaude.ui.theme.AganticClaudeTheme
import com.google.firebase.crashlytics.FirebaseCrashlytics

class NonFatalCrashDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val alert = crashItems.first { it.id == 10 }
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
        val crashlytics = FirebaseCrashlytics.getInstance()
        crashlytics.log("Alert triggered: onNonFatalCrash")
        crashlytics.setCustomKey("alert_type", "onNonFatalCrash")
        crashlytics.setCustomKey("alert_category", "NonFatal")
        crashlytics.recordException(
            RuntimeException("Non-fatal crash recorded: onNonFatalCrash alert simulation")
        )
        throw RuntimeException("Forced crash after non-fatal record: onNonFatalCrash")
    }
}
