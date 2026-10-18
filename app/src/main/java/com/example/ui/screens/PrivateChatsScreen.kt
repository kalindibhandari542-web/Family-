package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.GameRepository
import com.example.model.CharacterId
import com.example.model.ChatMessage
import com.example.ui.components.ChatBubble
import com.example.ui.components.PhoneStatusBar
import com.example.ui.theme.EmeraldGreenDark
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.ui.theme.LightChatBackground

@Composable
fun PrivateChatsScreen(
    gameState: GameRepository.GameState,
    onBack: () -> Unit
) {
    BackHandler { onBack() }

    var selectedContact by remember { mutableStateOf<CharacterId?>(null) }

    // Direct message sample data per key character
    val contactMessages = remember(gameState.currentScenarioId) {
        mapOf(
            CharacterId.KABIR to listOf(
                ChatMessage("pm_k1", CharacterId.KABIR, "Bro don't tell Dad I took his 65W charger...", "7:03 PM"),
                ChatMessage("pm_k2", CharacterId.PLAYER, "Bro you better put it back before he checks CCTV! 😂", "7:04 PM", isFromPlayer = true),
                ChatMessage("pm_k3", CharacterId.KABIR, "Already hid it under sofa pillow. You're a lifesaver 🙏", "7:05 PM")
            ),
            CharacterId.MOM to listOf(
                ChatMessage("pm_m1", CharacterId.MOM, "Beta did you finish your dinner? Drink turmeric milk before sleeping.", "10:30 PM"),
                ChatMessage("pm_m2", CharacterId.PLAYER, "Yes Mom! Had hot dinner, going to sleep soon ❤️", "10:32 PM", isFromPlayer = true),
                ChatMessage("pm_m3", CharacterId.MOM, "God bless you. Wake up early for walk tomorrow.", "10:33 PM")
            ),
            CharacterId.MAMA to listOf(
                ChatMessage("pm_s1", CharacterId.MAMA, "Beta @You, Pune market is picking up. That Parivaar Spices idea you gave in the group was visionary!", "8:45 PM"),
                ChatMessage("pm_s2", CharacterId.PLAYER, "We will make it a big success Mama! Focus on quality.", "8:46 PM", isFromPlayer = true)
            ),
            CharacterId.SIMRAN to listOf(
                ChatMessage("pm_si1", CharacterId.SIMRAN, "Aryan said thank you for being so welcoming in the group chat.", "5:40 PM"),
                ChatMessage("pm_si2", CharacterId.PLAYER, "He's a great guy Simran. Cousins always have your back! 👊", "5:42 PM", isFromPlayer = true)
            ),
            CharacterId.UNKNOWN_NUMBER to listOf(
                ChatMessage("pm_u1", CharacterId.UNKNOWN_NUMBER, "“A wise person listens to what is said, but watches what is done.”", "9:00 PM"),
                ChatMessage("pm_u2", CharacterId.PLAYER, "Who are you really? Why are you sending cryptic quotes?", "9:01 PM", isFromPlayer = true),
                ChatMessage("pm_u3", CharacterId.UNKNOWN_NUMBER, "All questions will be answered when the time is ripe. Watch the elders.", "9:02 PM")
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightChatBackground)
    ) {
        PhoneStatusBar(profile = gameState.playerProfile)

        // Header
        Surface(color = EmeraldGreenPrimary, shadowElevation = 4.dp) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    if (selectedContact != null) selectedContact = null else onBack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                if (selectedContact != null) {
                    val char = selectedContact!!
                    Box(
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(char.avatarColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = char.emoji, fontSize = 16.sp)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = char.displayName,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = "Confidential Direct Chat",
                            fontSize = 10.sp,
                            color = Color(0xFFD1FAE5)
                        )
                    }
                } else {
                    Text(
                        text = "Direct Messages 💬",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        if (selectedContact != null) {
            // Active 1-on-1 chat
            val char = selectedContact!!
            val messages = contactMessages[char] ?: listOf(
                ChatMessage("pm_def", char, "Hey @You! Catch you later in Apna Parivaar group!", "10:00 AM")
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                items(messages) { msg ->
                    ChatBubble(message = msg, relationshipScore = gameState.relationships[char] ?: 50)
                }
            }
        } else {
            // Contacts list for private chats
            val activeContacts = listOf(
                CharacterId.KABIR,
                CharacterId.MOM,
                CharacterId.MAMA,
                CharacterId.SIMRAN,
                CharacterId.UNKNOWN_NUMBER
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                items(activeContacts) { char ->
                    val score = gameState.relationships[char] ?: 50
                    val tier = char.relationshipTier(score)
                    val lastMsg = contactMessages[char]?.lastOrNull()?.text ?: "No recent direct messages"

                    Surface(
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedContact = char }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(char.avatarColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = char.emoji, fontSize = 22.sp)
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = char.displayName,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF1E293B)
                                    )
                                    Text(
                                        text = tier.label,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = tier.badgeColor
                                    )
                                }
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = lastMsg,
                                    fontSize = 12.sp,
                                    color = Color(0xFF64748B),
                                    maxLines = 1
                                )
                            }
                        }
                    }
                    HorizontalDivider(thickness = 0.5.dp, color = Color(0xFFE2E8F0))
                }
            }
        }
    }
}
