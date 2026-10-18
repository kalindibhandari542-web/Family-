package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.ui.components.PhoneStatusBar
import com.example.ui.theme.EmeraldGreenPrimary

data class GalleryItem(
    val title: String,
    val sender: String,
    val description: String,
    val resName: String
)

@Composable
fun GalleryScreen(
    profile: com.example.model.PlayerProfile,
    onBack: () -> Unit
) {
    BackHandler { onBack() }
    val context = LocalContext.current
    var selectedItem by remember { mutableStateOf<GalleryItem?>(null) }

    val items = listOf(
        GalleryItem(
            title = "Shubh Prabhat Rose Blast",
            sender = "Mahesh Chacha",
            description = "10,000 sparkling roses and glowing diya with morning blessings forwarded at 6:30 AM.",
            resName = "img_good_morning"
        ),
        GalleryItem(
            title = "Royal Wedding Invitation",
            sender = "Neha",
            description = "Gold-embossed royal invitation card for the big Udaipur destination wedding celebration.",
            resName = "img_wedding_card"
        ),
        GalleryItem(
            title = "1974 Ancestral Land Deed",
            sender = "Dadaji",
            description = "The authentic government stamped 1974 land deed for 2 acres near Jaipur Highway.",
            resName = "img_secret_deed"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF1F5F9))
    ) {
        PhoneStatusBar(profile = profile)

        Surface(color = Color(0xFF0284C7), shadowElevation = 4.dp) {
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
                        text = "Family Gallery 📸",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Memes, Cards, & Evidence Vault",
                        fontSize = 11.sp,
                        color = Color(0xFFE0F2FE)
                    )
                }
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(items) { item ->
                val resId = context.resources.getIdentifier(item.resName, "drawable", context.packageName)
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    shadowElevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedItem = item }
                ) {
                    Column {
                        if (resId != 0) {
                            Image(
                                painter = painterResource(id = resId),
                                contentDescription = item.title,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(130.dp)
                                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(130.dp)
                                    .background(Color(0xFFE2E8F0)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("📸 Image")
                            }
                        }
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(text = item.title, fontSize = 12.sp, fontWeight = FontWeight.Bold, maxLines = 1)
                            Text(text = "By ${item.sender}", fontSize = 10.sp, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }

    if (selectedItem != null) {
        val item = selectedItem!!
        val resId = context.resources.getIdentifier(item.resName, "drawable", context.packageName)
        Dialog(onDismissRequest = { selectedItem = null }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    if (resId != 0) {
                        Image(
                            painter = painterResource(id = resId),
                            contentDescription = item.title,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(260.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = item.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Sent by: ${item.sender}", fontSize = 11.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = item.description, fontSize = 12.sp, color = Color(0xFF334155))
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(
                        onClick = { selectedItem = null },
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreenPrimary),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Close")
                    }
                }
            }
        }
    }
}
