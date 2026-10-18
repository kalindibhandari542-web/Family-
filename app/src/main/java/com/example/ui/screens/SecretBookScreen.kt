package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SecretItem
import com.example.ui.components.PhoneStatusBar
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.ui.theme.SaffronGold
import com.example.ui.theme.SecretPurple
import com.example.ui.theme.WarmGold

@Composable
fun SecretBookScreen(
    secrets: List<SecretItem>,
    profile: com.example.model.PlayerProfile,
    onBack: () -> Unit
) {
    BackHandler { onBack() }

    val discoveredCount = secrets.count { it.isDiscovered }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
    ) {
        PhoneStatusBar(profile = profile)

        // Header
        Surface(color = SecretPurple, shadowElevation = 4.dp) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column {
                    Text(
                        text = "The Secret Book 📖",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "$discoveredCount / ${secrets.size} Family Secrets Unlocked",
                        fontSize = 11.sp,
                        color = Color(0xFFF3E8FF)
                    )
                }
            }
        }

        // Secrets list
        LazyColumn(
            contentPadding = PaddingValues(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(secrets) { secret ->
                SecretCard(secret = secret)
            }
        }
    }
}

@Composable
private fun SecretCard(secret: SecretItem) {
    Surface(
        color = if (secret.isDiscovered) Color(0xFFFFFBEB) else Color.White,
        shape = RoundedCornerShape(14.dp),
        border = androidx.compose.foundation.BorderStroke(
            width = if (secret.isDiscovered) 1.5.dp else 1.dp,
            color = if (secret.isDiscovered) WarmGold else Color(0xFFE2E8F0)
        ),
        shadowElevation = 2.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (secret.isDiscovered) WarmGold else Color(0xFFE2E8F0)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (secret.isDiscovered) Icons.Default.LockOpen else Icons.Default.Lock,
                    contentDescription = null,
                    tint = if (secret.isDiscovered) Color(0xFF3E2723) else Color.Gray,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Secret #${secret.number.toString().padStart(2, '0')}: ${secret.title}",
                        fontSize = 13.5.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (secret.isDiscovered) Color(0xFF78350F) else Color(0xFF64748B)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                if (secret.isDiscovered) {
                    Text(
                        text = secret.fullRevelation,
                        fontSize = 12.sp,
                        color = Color(0xFF1E293B),
                        lineHeight = 17.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "🔓 Unlocked in Story",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFB45309)
                    )
                } else {
                    Text(
                        text = "🔒 ${secret.teaser}",
                        fontSize = 12.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = Color(0xFF94A3B8)
                    )
                }
            }
        }
    }
}
