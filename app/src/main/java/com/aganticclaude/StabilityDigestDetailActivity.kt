package com.aganticclaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aganticclaude.ui.theme.AganticClaudeTheme
import com.google.firebase.crashlytics.FirebaseCrashlytics

class StabilityDigestDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val alert = crashItems.first { it.id == 12 }
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
            log("Alert triggered: onStabilityDigest")
            setCustomKey("alert_type", "onStabilityDigest")
            setCustomKey("alert_category", "StabilityDigest")
        }
        throw RuntimeException("Stability digest crash: onStabilityDigest alert simulation")
    }
}
