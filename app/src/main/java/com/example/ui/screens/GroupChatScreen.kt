package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.GameRepository
import com.example.model.CharacterId
import com.example.ui.components.ChatBubble
import com.example.ui.components.DecisionBar
import com.example.ui.components.PhoneStatusBar
import com.example.ui.theme.EmeraldGreenDark
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.ui.theme.LightChatBackground
import kotlinx.coroutines.launch

@Composable
fun GroupChatScreen(
    gameState: GameRepository.GameState,
    onSelectChoice: (com.example.model.ChatChoice) -> Unit,
    onProceedNext: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateSettings: () -> Unit,
    onViewEnding: () -> Unit
) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var selectedCharacterForProfile by remember { mutableStateOf<CharacterId?>(null) }

    // Auto-scroll to bottom when messages update
    LaunchedEffect(gameState.currentMessages.size) {
        if (gameState.currentMessages.isNotEmpty()) {
            scope.launch {
                listState.animateScrollToItem(gameState.currentMessages.size - 1)
            }
        }
    }

    val scenario = gameState.currentScenario
    val isChoiceMade = gameState.currentMessages.any { it.isFromPlayer }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightChatBackground)
    ) {
        // Simulated Phone Status Bar
        PhoneStatusBar(profile = gameState.playerProfile)

        // Custom Chat Top App Bar
        Surface(
            color = EmeraldGreenPrimary,
            shadowElevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Home Button (Fake Phone launcher)
                IconButton(
                    onClick = onNavigateHome,
                    modifier = Modifier.size(38.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Phone Home",
                        tint = Color.White
                    )
                }

                // Group Avatar
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE91E63)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "❤️", fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Title & Subtitle
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Apna Parivaar ❤️",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Ch ${scenario?.chapter ?: 1}/10 • Scen ${scenario?.scenarioId ?: 1}/50",
                        fontSize = 10.5.sp,
                        color = Color(0xFFD1FAE5)
                    )
                }

                // Settings icon
                IconButton(
                    onClick = onNavigateSettings,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        // Active Scenario Title Banner
        Surface(
            color = EmeraldGreenDark.copy(alpha = 0.9f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = scenario?.title ?: "Chat Scenario",
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFFEF3C7)
                )
                Text(
                    text = scenario?.category ?: "",
                    fontSize = 10.sp,
                    color = Color(0xFFE2E8F0)
                )
            }
        }

        // Chat messages
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(gameState.currentMessages) { msg ->
                    val relScore = gameState.relationships[msg.sender] ?: 50
                    ChatBubble(
                        message = msg,
                        relationshipScore = relScore,
                        onAvatarClick = { char ->
                            if (char != CharacterId.PLAYER) {
                                selectedCharacterForProfile = char
                            }
                        }
                    )
                }
            }
        }

        // Bottom Decision Bar
        DecisionBar(
            choices = scenario?.choices ?: emptyList(),
            isChoiceMade = isChoiceMade,
            impactText = gameState.lastChoiceImpactText,
            secretsList = gameState.secrets,
            onSelectChoice = onSelectChoice,
            onProceedNext = onProceedNext,
            onViewEnding = onViewEnding,
            hasEndingTriggered = gameState.currentEnding != null
        )
    }

    // Character Detail Dialog when avatar is clicked
    if (selectedCharacterForProfile != null) {
        val char = selectedCharacterForProfile!!
        val score = gameState.relationships[char] ?: 50
        val tier = char.relationshipTier(score)

        Dialog(onDismissRequest = { selectedCharacterForProfile = null }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                shadowElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(char.avatarColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = char.emoji, fontSize = 32.sp)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = char.displayName,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B)
                    )
                    Text(
                        text = char.relationshipTitle,
                        fontSize = 12.sp,
                        color = Color(0xFF64748B)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "“${char.catchphrase}”",
                        fontSize = 12.sp,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                        color = EmeraldGreenDark
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Relationship Meter
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Relationship:", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        Text(text = "${tier.label} ($score/100)", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = tier.badgeColor)
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    LinearProgressIndicator(
                        progress = { score / 100f },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = tier.badgeColor,
                        trackColor = Color(0xFFE2E8F0)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = char.bio,
                        fontSize = 11.5.sp,
                        color = Color(0xFF334155),
                        lineHeight = 16.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { selectedCharacterForProfile = null },
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreenPrimary),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Close Profile")
                    }
                }
            }
        }
    }
}
