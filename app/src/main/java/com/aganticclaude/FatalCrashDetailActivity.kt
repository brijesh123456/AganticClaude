package com.aganticclaude

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.aganticclaude.ui.theme.AganticClaudeTheme
import com.google.firebase.crashlytics.FirebaseCrashlytics

class FatalCrashDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val alert = crashItems.first { it.id == 9 }
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
            log("Alert triggered: onFatalCrash")
            setCustomKey("alert_type", "onFatalCrash")
            setCustomKey("alert_category", "Fatal")
        }
        throw RuntimeException("Forced fatal crash: onFatalCrash alert simulation")
    }
}
