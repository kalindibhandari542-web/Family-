package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BatteryFull
import androidx.compose.material.icons.filled.SignalCellularAlt
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PlayerProfile
import com.example.ui.theme.DramaRed
import com.example.ui.theme.MoneyGreen
import com.example.ui.theme.TrustPink

@Composable
fun PhoneStatusBar(
    profile: PlayerProfile,
    modifier: Modifier = Modifier,
    timeText: String = "10:14 AM"
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.95f),
        tonalElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)) {
            // Fake Phone Top Hardware Status Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = timeText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.SignalCellularAlt,
                        contentDescription = "Cellular",
                        modifier = Modifier.size(13.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Icon(
                        imageVector = Icons.Default.Wifi,
                        contentDescription = "WiFi",
                        modifier = Modifier.size(13.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Icon(
                        imageVector = Icons.Default.BatteryFull,
                        contentDescription = "Battery",
                        modifier = Modifier.size(13.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "94%",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Game Stats Bar (Subtle & Clean)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Trust
                StatPill(
                    icon = "❤️",
                    label = "Trust",
                    value = "${profile.trust}%",
                    badgeColor = TrustPink
                )
                // Drama
                StatPill(
                    icon = "🔥",
                    label = "Drama",
                    value = "${profile.drama}%",
                    badgeColor = DramaRed
                )
                // Money
                StatPill(
                    icon = "💰",
                    label = "Cash",
                    value = "₹${profile.money}",
                    badgeColor = MoneyGreen
                )
                // Secrets
                StatPill(
                    icon = "🤫",
                    label = "Secrets",
                    value = "${profile.secretsCount}",
                    badgeColor = Color(0xFF7B1FA2)
                )
            }
        }
    }
}

@Composable
private fun StatPill(
    icon: String,
    label: String,
    value: String,
    badgeColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(badgeColor.copy(alpha = 0.12f), RoundedCornerShape(12.dp))
            .padding(horizontal = 7.dp, vertical = 2.dp)
    ) {
        Text(text = icon, fontSize = 11.sp)
        Spacer(modifier = Modifier.width(3.dp))
        Text(
            text = "$label: $value",
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = badgeColor
        )
    }
}
