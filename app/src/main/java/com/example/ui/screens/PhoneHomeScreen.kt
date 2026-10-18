package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.GameRepository
import com.example.model.PhoneApp
import com.example.ui.components.PhoneStatusBar
import com.example.ui.theme.SaffronGold

@Composable
fun PhoneHomeScreen(
    gameState: GameRepository.GameState,
    onOpenApp: (PhoneApp) -> Unit
) {
    val wallpaperColors = when (gameState.wallpaperIndex) {
        1 -> listOf(Color(0xFF1A1A2E), Color(0xFF16213E), Color(0xFF0F3460)) // Midnight Blue
        2 -> listOf(Color(0xFF3E2723), Color(0xFF4E342E), Color(0xFF6D4C41)) // Royal Sepia
        3 -> listOf(Color(0xFF4A148C), Color(0xFF311B92), Color(0xFF1A237E)) // Cyber Saffron / Purple
        else -> listOf(Color(0xFF062E25), Color(0xFF0B463A), Color(0xFF115E4E)) // Classic Indian Emerald
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(wallpaperColors))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Simulated phone status bar
            PhoneStatusBar(profile = gameState.playerProfile)

            Spacer(modifier = Modifier.height(16.dp))

            // Phone Clock Widget
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                Text(
                    text = "10:14",
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
                Text(
                    text = "Sunday, October 18 • Apna Phone OS",
                    fontSize = 13.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Active Story Summary Card
                Surface(
                    color = Color.White.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onOpenApp(PhoneApp.FAMILY_CHAT) }
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(SaffronGold),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "❤️", fontSize = 20.sp)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Apna Parivaar (Active)",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = gameState.currentScenario?.title ?: "Chapter 1",
                                fontSize = 11.5.sp,
                                color = Color(0xFFD1FAE5)
                            )
                        }
                        Text(text = "Tap 📱", fontSize = 12.sp, color = SaffronGold, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // App Launcher Grid (3 columns)
            val apps = listOf(
                PhoneApp.FAMILY_CHAT,
                PhoneApp.PRIVATE_CHATS,
                PhoneApp.CALLS,
                PhoneApp.GALLERY,
                PhoneApp.SECRET_BOOK,
                PhoneApp.ACHIEVEMENTS,
                PhoneApp.DAILY_EVENTS,
                PhoneApp.SHOP,
                PhoneApp.SETTINGS
            )

            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(apps) { app ->
                    AppIconItem(
                        app = app,
                        badgeCount = when (app) {
                            PhoneApp.FAMILY_CHAT -> if (gameState.currentMessages.isNotEmpty()) 1 else 0
                            PhoneApp.SECRET_BOOK -> gameState.secrets.count { it.isDiscovered }
                            PhoneApp.ACHIEVEMENTS -> gameState.achievements.count { it.isUnlocked }
                            else -> 0
                        },
                        onClick = { onOpenApp(app) }
                    )
                }
            }

            // Bottom Navigation Dock
            Surface(
                color = Color.Black.copy(alpha = 0.35f),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    DockIcon("📞", "Calls") { onOpenApp(PhoneApp.CALLS) }
                    DockIcon("💬", "Direct") { onOpenApp(PhoneApp.PRIVATE_CHATS) }
                    DockIcon("❤️", "Family") { onOpenApp(PhoneApp.FAMILY_CHAT) }
                    DockIcon("⚙️", "Settings") { onOpenApp(PhoneApp.SETTINGS) }
                }
            }
        }
    }
}

@Composable
private fun AppIconItem(
    app: PhoneApp,
    badgeCount: Int,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = app.iconEmoji, fontSize = 28.sp)

            if (badgeCount > 0) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = 4.dp, y = (-4).dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE53935))
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$badgeCount",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = app.title,
            fontSize = 11.5.sp,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

@Composable
private fun DockIcon(
    emoji: String,
    label: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.22f)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = emoji, fontSize = 22.sp)
        }
        Text(text = label, fontSize = 9.sp, color = Color.White.copy(alpha = 0.9f))
    }
}
