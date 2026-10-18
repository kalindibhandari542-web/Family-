package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.GameRepository
import com.example.model.EndingType
import com.example.ui.components.PhoneStatusBar
import com.example.ui.theme.DramaRed
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.ui.theme.SaffronGold

@Composable
fun SettingsReplayScreen(
    gameState: GameRepository.GameState,
    onToggleSound: (Boolean) -> Unit,
    onToggleHaptics: (Boolean) -> Unit,
    onJumpToChapter: (Int) -> Unit,
    onResetGame: () -> Unit,
    onBack: () -> Unit
) {
    BackHandler { onBack() }
    var showResetDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
    ) {
        PhoneStatusBar(profile = gameState.playerProfile)

        Surface(color = Color(0xFF475569), shadowElevation = 4.dp) {
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
                        text = "Settings & Replay ⚙️",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Audio, Chapter Replay, and System",
                        fontSize = 11.sp,
                        color = Color(0xFFE2E8F0)
                    )
                }
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            // Audio & Vibration Toggles
            item {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(text = "Preferences", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Sound Effects & Chimes 🔊", fontSize = 13.sp)
                            Switch(
                                checked = gameState.isSoundEnabled,
                                onCheckedChange = onToggleSound
                            )
                        }

                        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), color = Color(0xFFF1F5F9))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("Haptic Feedback & Vibration 📳", fontSize = 13.sp)
                            Switch(
                                checked = gameState.isHapticsEnabled,
                                onCheckedChange = onToggleHaptics
                            )
                        }
                    }
                }
            }

            // Chapter Replay System (10 Chapters)
            item {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(text = "Replay Chapter 🔄", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text(
                            text = "Jump to any chapter you've previously unlocked:",
                            fontSize = 11.5.sp,
                            color = Color(0xFF64748B)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        val chapterTitles = listOf(
                            1 to "Ch 1: Meet the Family (Morning Spam & Food)",
                            2 to "Ch 2: Normal Chaos (TV War & Late Night SOS)",
                            3 to "Ch 3: First Secret (Cousin Fight & Crush)",
                            4 to "Ch 4: Relationship Drama (Salary & Rishta)",
                            5 to "Ch 5: Money Mystery (Screenshot & Loan)",
                            6 to "Ch 6: Unknown Number (Property Clues)",
                            7 to "Ch 7: Chaotic Events (Wedding & Horlicks Stash)",
                            8 to "Ch 8: Major Betrayal (Viral Fufa & Land Offer)",
                            9 to "Ch 9: Truth Revealed (Adv. Mathur & Confessions)",
                            10 to "Ch 10: Final Decision (All 5 Endings)"
                        )

                        chapterTitles.forEach { (chNum, chTitle) ->
                            val isUnlocked = chNum <= gameState.maxChapterUnlocked
                            val firstScenarioInChapter = (chNum - 1) * 5 + 1

                            Surface(
                                color = if (isUnlocked) Color(0xFFF0FDF4) else Color(0xFFF8FAFC),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 3.dp)
                                    .clickable(enabled = isUnlocked) {
                                        onJumpToChapter(firstScenarioInChapter)
                                    }
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = chTitle,
                                        fontSize = 11.5.sp,
                                        fontWeight = if (isUnlocked) FontWeight.SemiBold else FontWeight.Normal,
                                        color = if (isUnlocked) Color(0xFF15803D) else Color.Gray
                                    )
                                    if (isUnlocked) {
                                        Icon(
                                            imageVector = Icons.Default.PlayArrow,
                                            contentDescription = "Play",
                                            tint = Color(0xFF15803D),
                                            modifier = Modifier.size(16.dp)
                                        )
                                    } else {
                                        Text("🔒", fontSize = 11.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Endings Unlocked Gallery
            item {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = "Major Endings Gallery (${gameState.unlockedEndings.size}/5)",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        EndingType.entries.forEach { end ->
                            val unlocked = gameState.unlockedEndings.contains(end)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = if (unlocked) end.badge else "🔒", fontSize = 16.sp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Column {
                                    Text(
                                        text = end.title,
                                        fontSize = 12.sp,
                                        fontWeight = if (unlocked) FontWeight.Bold else FontWeight.Normal,
                                        color = if (unlocked) Color(0xFF1E293B) else Color.Gray
                                    )
                                    if (unlocked) {
                                        Text(text = end.subtitle, fontSize = 10.sp, color = SaffronGold)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Reset Game Progress
            item {
                OutlinedButton(
                    onClick = { showResetDialog = true },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = DramaRed),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Reset Game & Start Fresh (New Game)")
                }
            }
        }
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Reset Game Progress?") },
            text = { Text("This will reset all your stats, unlocked secrets, and return you to Chapter 1. Are you sure?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showResetDialog = false
                        onResetGame()
                    }
                ) {
                    Text("Yes, Reset", color = DramaRed, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}
