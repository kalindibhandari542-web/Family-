package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PlayerProfile
import com.example.ui.components.PhoneStatusBar
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.ui.theme.SaffronGold

@Composable
fun ShopScreen(
    profile: PlayerProfile,
    currentWallpaperIndex: Int,
    unlockedPacks: Set<String>,
    onSelectWallpaper: (Int) -> Unit,
    onUnlockPack: (String) -> Unit,
    onBack: () -> Unit
) {
    BackHandler { onBack() }

    val wallpapers = listOf(
        "Classic Indian Emerald" to Color(0xFF0F5A47),
        "Midnight Blue" to Color(0xFF16213E),
        "Royal Sepia" to Color(0xFF4E342E),
        "Cyber Saffron" to Color(0xFF4A148C)
    )

    val bonusStoryPacks = listOf(
        Triple("pack_goa", "🌴 Goa Family Vacation", "All 21 relatives cram into a beach villa. Suresh Mama rents a jetski. Dad complains about sunblock prices."),
        Triple("pack_wedding", "💍 The Big Fat Indian Wedding", "5 days of continuous sangeet choreography, missing sherwani buttons, and buffet diplomacy."),
        Triple("pack_diwali", "🪔 Diwali Sweets Disaster", "Anita Bua and Sunita Mom's sweet-making showdown with 15 varieties of Laddoo.")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
    ) {
        PhoneStatusBar(profile = profile)

        Surface(color = Color(0xFF7C3AED), shadowElevation = 4.dp) {
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
                        text = "Parivaar Bazaar & Packs 🛍️",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Customize phone wallpapers & bonus stories",
                        fontSize = 11.sp,
                        color = Color(0xFFEDE9FE)
                    )
                }
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(14.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.weight(1f)
        ) {
            // Section 1: Phone Wallpapers
            item {
                Text(
                    text = "📱 Phone Wallpapers",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    wallpapers.forEachIndexed { index, (name, color) ->
                        Surface(
                            color = color,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(64.dp)
                                .clickable { onSelectWallpaper(index) }
                                .border(
                                    width = if (currentWallpaperIndex == index) 2.5.dp else 0.dp,
                                    color = SaffronGold,
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                if (currentWallpaperIndex == index) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Active",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Section 2: Bonus Story Packs
            item {
                Text(
                    text = "📖 Bonus Story Packs",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B)
                )
            }

            items(bonusStoryPacks) { (packId, title, desc) ->
                val isUnlocked = unlockedPacks.contains(packId)
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    shadowElevation = 2.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            if (isUnlocked) {
                                Surface(
                                    color = Color(0xFFDCFCE7),
                                    shape = RoundedCornerShape(6.dp)
                                ) {
                                    Text(
                                        text = "UNLOCKED ✅",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF16A34A),
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = desc, fontSize = 11.5.sp, color = Color(0xFF475569))

                        if (!isUnlocked) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { onUnlockPack(packId) },
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreenPrimary),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("Unlock Pack (Free In-Game Bonus)")
                            }
                        }
                    }
                }
            }
        }
    }
}
