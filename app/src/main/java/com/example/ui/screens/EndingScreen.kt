package com.example.ui.screens

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.EndingType
import com.example.model.PlayerProfile
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.ui.theme.SaffronGold
import com.example.ui.theme.WarmGold

@Composable
fun EndingScreen(
    ending: EndingType,
    unlockedEndings: Set<EndingType>,
    profile: PlayerProfile,
    onPlayAgain: () -> Unit,
    onViewSecrets: () -> Unit
) {
    BackHandler { onPlayAgain() }
    val context = LocalContext.current

    val bgColors = when (ending) {
        EndingType.HAPPY_FAMILY -> listOf(Color(0xFF0F5A47), Color(0xFF1B5E20), Color(0xFF2E7D32))
        EndingType.FAMILY_WAR -> listOf(Color(0xFF450A0A), Color(0xFF7F1D1D), Color(0xFF1E293B))
        EndingType.SECRET_MILLIONAIRE -> listOf(Color(0xFF78350F), Color(0xFF92400E), Color(0xFFB45309))
        EndingType.THE_BETRAYAL -> listOf(Color(0xFF18181B), Color(0xFF27272A), Color(0xFF3F3F46))
        EndingType.NEW_BEGINNING -> listOf(Color(0xFF0C4A6E), Color(0xFF075985), Color(0xFF0284C7))
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(bgColors))
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = ending.badge,
                    fontSize = 60.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = ending.title.uppercase(),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = ending.subtitle,
                    fontSize = 14.sp,
                    color = SaffronGold,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Epilogue Quote Card
            item {
                Surface(
                    color = Color.Black.copy(alpha = 0.35f),
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, WarmGold.copy(alpha = 0.5f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = ending.epilogueQuote,
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFFFEF3C7),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp),
                        lineHeight = 22.sp
                    )
                }
            }

            // Story Conclusion Narrative
            item {
                Surface(
                    color = Color.White.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "EPILOGUE",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = SaffronGold,
                            letterSpacing = 1.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = ending.storyConclusion,
                            fontSize = 13.5.sp,
                            color = Color.White,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            // Player Final Stats Summary
            item {
                Surface(
                    color = Color.White.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "FINAL FAMILY METRICS",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFCBD5E1)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            EndingStatItem("❤️ Trust", "${profile.trust}%")
                            EndingStatItem("🔥 Drama", "${profile.drama}%")
                            EndingStatItem("💰 Net Worth", "₹${profile.money}")
                            EndingStatItem("🤫 Secrets", "${profile.secretsCount}/10")
                        }
                    }
                }
            }

            // Endings Collection Tracker (1/5 ... 5/5)
            item {
                Surface(
                    color = Color.Black.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(14.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "ENDINGS UNLOCKED: ${unlockedEndings.size} / 5",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = SaffronGold
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            EndingType.entries.forEach { e ->
                                val isFound = unlockedEndings.contains(e)
                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .clip(CircleShape)
                                        .background(if (isFound) WarmGold else Color.White.copy(alpha = 0.15f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = if (isFound) e.badge else "🔒",
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Action Buttons
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Share Card
                    OutlinedButton(
                        onClick = {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "I completed 'Family WhatsApp' and reached ${ending.title}!\n\n${ending.shareableTitle}\n\nCan you survive Apna Parivaar without getting disowned? 😂")
                                type = "text/plain"
                            }
                            context.startActivity(Intent.createChooser(sendIntent, "Share Ending"))
                        },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(imageVector = Icons.Default.Share, contentDescription = "Share", modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Share Card")
                    }

                    // View Secret Book
                    OutlinedButton(
                        onClick = onViewSecrets,
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = SaffronGold),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Secret Book 📖")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = onPlayAgain,
                    colors = ButtonDefaults.buttonColors(containerColor = SaffronGold),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "Play Again", tint = Color(0xFF2E1C00))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Play Again / Replay Story 🔄",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF2E1C00)
                    )
                }
            }
        }
    }
}

@Composable
private fun EndingStatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 11.sp, color = Color(0xFFCBD5E1))
        Text(text = value, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}
