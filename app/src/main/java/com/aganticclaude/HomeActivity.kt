package com.aganticclaude

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.aganticclaude.ui.theme.AganticClaudeTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AganticClaudeTheme(darkTheme = false) {
                HomeScreen(
                    onBrowseCrashes = {
                        startActivity(Intent(this, CrashListActivity::class.java))
                    },
                    onViewCrashDetail = {
                        startActivity(Intent(this, CrashDetailActivity::class.java).apply {
                            putExtra("crash_id", 0)
                        })
                    },
                    onBrowseAlertTypes = {
                        startActivity(Intent(this, AlertTypesListActivity::class.java))
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onBrowseCrashes: () -> Unit, onViewCrashDetail: () -> Unit, onBrowseAlertTypes: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Crash Lab",
                        fontWeight = FontWeight.ExtraBold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF4F6F8))
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {

        // Description — just above the cards
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "This app is designed to simulate various Android crashes " +
                        "and demonstrate how Claude AI automatically detects and fixes them.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF5A6473),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Card 1 — Browse Crash Tests
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Crash Simulations",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A2E)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Browse all crash types with descriptions and trigger them individually.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6B7280)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onBrowseCrashes,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Browse Crash Tests")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Card 2 — Crash Detail
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Crash Detail",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A2E)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "View detailed information about a specific crash type and trigger it.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6B7280)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedButton(
                        onClick = onViewCrashDetail,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Open Crash Detail")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Card 3 — Alert Types
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Alert Types",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A2E)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Browse alert event types such as ANR, fatal, non-fatal, regression, and velocity alerts.",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6B7280)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onBrowseAlertTypes,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Browse Alert Types")
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
    }
}
