package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.*
import com.example.ui.theme.EmeraldGreenDark
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.ui.theme.SaffronGold
import com.example.ui.theme.WarmGold

@Composable
fun OnboardingScreen(
    onComplete: (PlayerProfile) -> Unit
) {
    var step by remember { mutableIntStateOf(1) }

    // Customization state
    var playerName by remember { mutableStateOf("Aarav") }
    var selectedGender by remember { mutableStateOf(Gender.MALE) }
    var selectedAge by remember { mutableIntStateOf(24) }
    var selectedAvatarId by remember { mutableIntStateOf(1) }
    var selectedStyle by remember { mutableStateOf(PersonalityStyle.DIPLOMAT) }

    val avatars = listOf("🙋‍♂️", "🙋‍♀️", "🧑‍💻", "👩‍💻", "😎", "🤩")

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF062B23), Color(0xFF0F5A47), Color(0xFF133E35))
                )
            )
            .statusBarsPadding()
            .navigationBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Stepper dots
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 12.dp)
            ) {
                repeat(4) { idx ->
                    Box(
                        modifier = Modifier
                            .width(if (step == idx + 1) 28.dp else 8.dp)
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(if (step >= idx + 1) SaffronGold else Color.White.copy(alpha = 0.3f))
                    )
                }
            }

            // Screen content based on step
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                when (step) {
                    1 -> {
                        // Screen 1: Welcome
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "❤️", fontSize = 64.sp)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "WELCOME TO THE FAMILY.",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Surface(
                                color = Color.Black.copy(alpha = 0.25f),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = "“Unfortunately, you can't leave the group.” 😂",
                                    fontSize = 15.sp,
                                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                    color = SaffronGold,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                    textAlign = TextAlign.Center
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "You have just been added to Apna Parivaar ❤️\n20 relatives. Unending Good Morning spam. Leaked screenshots. Family feuds. And 5 distinct endings.",
                                fontSize = 13.5.sp,
                                color = Color(0xFFD1FAE5),
                                textAlign = TextAlign.Center,
                                lineHeight = 20.sp
                            )
                        }
                    }
                    2 -> {
                        // Screen 2: Meet Your Family
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "MEET YOUR FAMILY (20 CHARACTERS)",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = SaffronGold
                            )
                            Text(
                                text = "Scroll through the relatives you'll navigate:",
                                fontSize = 12.sp,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            LazyColumn(
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(CharacterId.entries.filter { it != CharacterId.PLAYER }) { char ->
                                    Surface(
                                        color = Color.White.copy(alpha = 0.1f),
                                        shape = RoundedCornerShape(10.dp),
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(10.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Box(
                                                modifier = Modifier
                                                    .size(38.dp)
                                                    .clip(CircleShape)
                                                    .background(char.avatarColor),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(text = char.emoji, fontSize = 20.sp)
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column {
                                                Text(
                                                    text = char.displayName,
                                                    fontSize = 13.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    color = Color.White
                                                )
                                                Text(
                                                    text = char.relationshipTitle,
                                                    fontSize = 11.sp,
                                                    color = SaffronGold
                                                )
                                                Text(
                                                    text = "“${char.catchphrase}”",
                                                    fontSize = 10.5.sp,
                                                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                                                    color = Color(0xFFCBD5E1)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    3 -> {
                        // Screen 3: One Rule
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "📱⚠️", fontSize = 56.sp)
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "YOU HAVE ONE RULE...",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Surface(
                                color = SaffronGold,
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = "THINK BEFORE YOU SEND.",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF3E2723),
                                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "Every response alters your Family Trust, Drama level, and Character Relationships.\n\nAccidental screenshots, wrong groups, and mysterious callers will test your loyalty. Can you keep the family together... or will you trigger a Family War?",
                                fontSize = 13.sp,
                                color = Color(0xFFE2E8F0),
                                textAlign = TextAlign.Center,
                                lineHeight = 19.sp
                            )
                        }
                    }
                    4 -> {
                        // Screen 4: Customize Player
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.fillMaxSize()
                        ) {
                            item {
                                Text(
                                    text = "CUSTOMIZE YOUR PROFILE",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = SaffronGold
                                )
                                Text(
                                    text = "How shall the family address you?",
                                    fontSize = 12.sp,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                            }

                            // Name field
                            item {
                                OutlinedTextField(
                                    value = playerName,
                                    onValueChange = { playerName = it },
                                    label = { Text("Your Name") },
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = SaffronGold,
                                        unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                                        focusedTextColor = Color.White,
                                        unfocusedTextColor = Color.White,
                                        focusedLabelColor = SaffronGold,
                                        unfocusedLabelColor = Color.White.copy(alpha = 0.7f)
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }

                            // Gender & Age selector
                            item {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Gender.entries.forEach { g ->
                                        FilterChip(
                                            selected = selectedGender == g,
                                            onClick = { selectedGender = g },
                                            label = { Text(g.label, fontSize = 12.sp) },
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                }
                            }

                            // Avatar selector
                            item {
                                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                                    Text("Choose Avatar:", fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
                                    Spacer(modifier = Modifier.height(6.dp))
                                    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                                        items(avatars.indices.toList()) { idx ->
                                            Box(
                                                modifier = Modifier
                                                    .size(48.dp)
                                                    .clip(CircleShape)
                                                    .background(if (selectedAvatarId == idx + 1) SaffronGold else Color.White.copy(alpha = 0.2f))
                                                    .border(
                                                        width = if (selectedAvatarId == idx + 1) 2.dp else 0.dp,
                                                        color = Color.White,
                                                        shape = CircleShape
                                                    )
                                                    .clickable { selectedAvatarId = idx + 1 },
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(text = avatars[idx], fontSize = 22.sp)
                                            }
                                        }
                                    }
                                }
                            }

                            // Personality Style
                            item {
                                Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                                    Text("Personality Style & Starting Perk:", fontSize = 12.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
                                    Spacer(modifier = Modifier.height(6.dp))
                                    PersonalityStyle.entries.forEach { style ->
                                        Surface(
                                            color = if (selectedStyle == style) SaffronGold.copy(alpha = 0.25f) else Color.White.copy(alpha = 0.08f),
                                            shape = RoundedCornerShape(10.dp),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 3.dp)
                                                .clickable { selectedStyle = style }
                                                .border(
                                                    width = if (selectedStyle == style) 1.5.dp else 0.dp,
                                                    color = SaffronGold,
                                                    shape = RoundedCornerShape(10.dp)
                                                )
                                        ) {
                                            Row(
                                                modifier = Modifier.padding(10.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Column(modifier = Modifier.weight(1f)) {
                                                    Text(text = style.title, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 13.sp)
                                                    Text(text = style.description, color = Color(0xFFCBD5E1), fontSize = 11.sp)
                                                    Text(text = "🎁 Perk: ${style.bonus}", color = SaffronGold, fontSize = 10.5.sp, fontWeight = FontWeight.SemiBold)
                                                }
                                                if (selectedStyle == style) {
                                                    Icon(imageVector = Icons.Default.Check, contentDescription = "Selected", tint = SaffronGold)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Bottom Continue / Start Button
            Button(
                onClick = {
                    if (step < 4) {
                        step++
                    } else {
                        val profile = PlayerProfile(
                            name = playerName.ifBlank { "Aarav" },
                            gender = selectedGender,
                            age = selectedAge,
                            avatarId = selectedAvatarId,
                            personalityStyle = selectedStyle
                        )
                        onComplete(profile)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = SaffronGold),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = if (step < 4) "Next ➡️" else "Join Apna Parivaar ❤️",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E1C00)
                )
            }
        }
    }
}
