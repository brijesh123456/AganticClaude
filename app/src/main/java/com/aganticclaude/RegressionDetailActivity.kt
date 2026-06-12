package com.aganticclaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aganticclaude.ui.theme.AganticClaudeTheme
import com.google.firebase.crashlytics.FirebaseCrashlytics

class RegressionDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val alert = crashItems.first { it.id == 11 }
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
            log("Alert triggered: onRegression")
            setCustomKey("alert_type", "onRegression")
            setCustomKey("alert_category", "Regression")
        }
        throw RuntimeException("Regression detected: onRegression alert simulation")
    }
}
