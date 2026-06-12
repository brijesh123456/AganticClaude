package com.aganticclaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aganticclaude.ui.theme.AganticClaudeTheme
import com.google.firebase.crashlytics.FirebaseCrashlytics

class VelocityAlertDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val alert = crashItems.first { it.id == 13 }
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
        val exception = RuntimeException("Velocity spike alert: onVelocityAlert alert simulation")
        FirebaseCrashlytics.getInstance().apply {
            log("Alert triggered: onVelocityAlert")
            setCustomKey("alert_type", "onVelocityAlert")
            setCustomKey("alert_category", "VelocityAlert")
            recordException(exception)
        }
    }
}
