package com.aganticclaude

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aganticclaude.ui.theme.AganticClaudeTheme

data class CrashInfo(
    val id: Int,
    val name: String,
    val shortDesc: String,
    val fullDescription: String,
    val iconLabel: String,
    val iconColor: Color
)

val crashItems = listOf(
    CrashInfo(
        id = 0,
        name = "NullPointerException",
        shortDesc = "Null object dereference",
        fullDescription = "A NullPointerException occurs when the app tries to access a method or property on a null object reference. " +
                "This is one of the most common crashes in Android apps. The app terminates immediately with a fatal error " +
                "because Kotlin's !! operator forces an unwrap on a null value.",
        iconLabel = "N",
        iconColor = Color(0xFFE53935)
    ),
    CrashInfo(
        id = 1,
        name = "ArrayIndexOutOfBoundsException",
        shortDesc = "Invalid array index access",
        fullDescription = "This crash happens when the app tries to access an array index that does not exist. " +
                "For example, accessing index 10 on an array with only 3 elements will throw this exception. " +
                "The JVM catches this at runtime and terminates the app with a fatal crash.",
        iconLabel = "AI",
        iconColor = Color(0xFFF57C00)
    ),
    CrashInfo(
        id = 2,
        name = "ClassCastException",
        shortDesc = "Incompatible type cast",
        fullDescription = "A ClassCastException is thrown when the app tries to cast an object to an incompatible type. " +
                "For example, casting a String to an Integer will fail at runtime. " +
                "Using the safe as? operator instead of as prevents this crash.",
        iconLabel = "CC",
        iconColor = Color(0xFFFBC02D)
    ),
    CrashInfo(
        id = 3,
        name = "ArithmeticException",
        shortDesc = "Division by zero failure",
        fullDescription = "An ArithmeticException occurs during illegal arithmetic operations such as dividing an integer by zero. " +
                "This causes a fatal crash if not handled. A simple null or zero check before the division " +
                "prevents this from occurring in production.",
        iconLabel = "AE",
        iconColor = Color(0xFFE64A19)
    ),
    CrashInfo(
        id = 4,
        name = "StackOverflowError",
        shortDesc = "Infinite recursion depth",
        fullDescription = "A StackOverflowError occurs when a method calls itself indefinitely, exhausting the call stack memory. " +
                "Each recursive call consumes stack space until the system runs out, crashing the app. " +
                "Adding a base-case condition to the recursion prevents this error.",
        iconLabel = "SO",
        iconColor = Color(0xFF7B1FA2)
    ),
    CrashInfo(
        id = 5,
        name = "NumberFormatException",
        shortDesc = "Invalid string to number parse",
        fullDescription = "A NumberFormatException is thrown when the app tries to parse a string that is not a valid number. " +
                "Calling toInt() on the string 'NotANumber' will throw this exception. " +
                "Using toIntOrNull() with a fallback value is the safe alternative.",
        iconLabel = "NF",
        iconColor = Color(0xFF1565C0)
    ),
    CrashInfo(
        id = 6,
        name = "IllegalStateException",
        shortDesc = "Object in invalid state",
        fullDescription = "An IllegalStateException is thrown when a method is invoked at an illegal or inappropriate time. " +
                "It signals that the application is in the wrong state for the requested operation. " +
                "Validating state before calling the operation prevents this crash.",
        iconLabel = "IS",
        iconColor = Color(0xFF00695C)
    ),
    CrashInfo(
        id = 7,
        name = "RuntimeException",
        shortDesc = "Generic fatal crash",
        fullDescription = "A RuntimeException is the parent class of many common exceptions. " +
                "When thrown directly, it acts as a generic fatal crash that immediately terminates the app. " +
                "Wrapping risky operations in try-catch blocks allows the app to handle the error gracefully.",
        iconLabel = "RE",
        iconColor = Color(0xFFC62828)
    ),
    CrashInfo(
        id = 8,
        name = "onAnrCrash",
        shortDesc = "App Not Responding crash event",
        fullDescription = "An ANR (App Not Responding) occurs when the main thread is blocked for more than 5 seconds. " +
                "This is simulated by running an infinite loop on the main thread, freezing the UI. " +
                "Moving heavy work to a background thread or coroutine prevents this.",
        iconLabel = "AN",
        iconColor = Color(0xFFE53935)
    ),
    CrashInfo(
        id = 9,
        name = "onFatalCrash",
        shortDesc = "Fatal crash — app terminated",
        fullDescription = "A fatal crash immediately terminates the app and is logged to Firebase Crashlytics. " +
                "This is simulated by throwing an unhandled RuntimeException on the main thread. " +
                "Fatal crashes require the user to restart the app to continue.",
        iconLabel = "FC",
        iconColor = Color(0xFFF57C00)
    ),
    CrashInfo(
        id = 10,
        name = "onNonFatalCrash",
        shortDesc = "Non-fatal crash — app survived",
        fullDescription = "A non-fatal crash is a caught exception that is manually recorded to Firebase Crashlytics " +
                "without terminating the app. This is useful for tracking errors that are handled gracefully " +
                "but still indicate a problem that needs attention.",
        iconLabel = "NF",
        iconColor = Color(0xFFFBC02D)
    ),
    CrashInfo(
        id = 11,
        name = "onRegression",
        shortDesc = "Crash rate regression detected",
        fullDescription = "A regression alert fires when a previously stable crash reappears or when the crash " +
                "rate significantly increases compared to a previous version. " +
                "This is simulated by throwing an exception tagged with regression metadata.",
        iconLabel = "RG",
        iconColor = Color(0xFF7B1FA2)
    ),
    CrashInfo(
        id = 12,
        name = "onStabilityDigest",
        shortDesc = "Periodic stability summary report",
        fullDescription = "A stability digest is a periodic summary of crash trends sent by Firebase Crashlytics. " +
                "This simulates a fatal exception that would appear in a daily or weekly digest report, " +
                "helping teams monitor overall app health over time.",
        iconLabel = "SD",
        iconColor = Color(0xFF1565C0)
    ),
    CrashInfo(
        id = 13,
        name = "onVelocityAlert",
        shortDesc = "Rapid crash velocity spike alert",
        fullDescription = "A velocity alert fires when the number of crashes per session exceeds a set threshold " +
                "in a short period of time. This simulates a sudden spike by throwing a fatal exception " +
                "tagged with velocity alert metadata in Firebase Crashlytics.",
        iconLabel = "VA",
        iconColor = Color(0xFF00695C)
    )
)

class CrashTestListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AganticClaudeTheme(darkTheme = false) {
                CrashListScreen(
                    onNavigateBack = { finish() },
                    onCrashSelected = { crashId ->
                        val intent = Intent(this, CrashIssueDetailsActivity::class.java)
                        intent.putExtra("crash_id", crashId)
                        startActivity(intent)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CrashListScreen(onNavigateBack: () -> Unit, onCrashSelected: (Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crash Test Library") },
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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4F6F8))
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            itemsIndexed(crashItems) { _, crash ->
                CrashListItem(crash = crash, onClick = { onCrashSelected(crash.id) })
            }
        }
    }
}

@Composable
fun CrashListItem(crash: CrashInfo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .background(crash.iconColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = crash.iconLabel,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.labelLarge
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = crash.name,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = crash.shortDesc,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
