package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CallMissed
import androidx.compose.material.icons.filled.PhoneCallback
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CharacterId
import com.example.ui.components.PhoneStatusBar
import com.example.ui.theme.DramaRed
import com.example.ui.theme.MoneyGreen

data class CallRecord(
    val character: CharacterId,
    val time: String,
    val isMissed: Boolean,
    val durationOrVoicemail: String,
    val audioSnippet: String? = null
)

@Composable
fun CallsScreen(
    profile: com.example.model.PlayerProfile,
    onBack: () -> Unit
) {
    BackHandler { onBack() }
    var activeVoicemailIndex by remember { mutableStateOf<Int?>(null) }

    val callLogs = remember {
        listOf(
            CallRecord(CharacterId.MOM, "Today, 10:15 AM", false, "Outgoing • 2 min 14 sec", "“Beta don't forget to take an umbrella. It might rain!”"),
            CallRecord(CharacterId.MAMA, "Yesterday, 6:35 PM", true, "Missed Call • (3 rings)", "“Beta pick up, need to talk about the Pune restaurant urgent!”"),
            CallRecord(CharacterId.DADI, "2 days ago", false, "Voice Memo • 0:32 sec", "“Listen carefully children... 30 years ago on Diwali 1994, Sunita didn't insult Anita on purpose!”"),
            CallRecord(CharacterId.UNKNOWN_NUMBER, "3 days ago", true, "Private / Encrypted", "“Look into registry serial #749. Time is running out.”")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
    ) {
        PhoneStatusBar(profile = profile)

        Surface(color = Color(0xFF0F766E), shadowElevation = 4.dp) {
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
                        text = "Phone & Voicemails 📞",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Call history & audio messages",
                        fontSize = 11.sp,
                        color = Color(0xFFCCFBF1)
                    )
                }
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(callLogs.indices.toList()) { idx ->
                val call = callLogs[idx]
                val isExpanded = activeVoicemailIndex == idx

                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    shadowElevation = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                                    .background(call.character.avatarColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = call.character.emoji, fontSize = 20.sp)
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = call.character.displayName,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1E293B)
                                )
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = if (call.isMissed) Icons.Default.CallMissed else Icons.Default.PhoneCallback,
                                        contentDescription = null,
                                        tint = if (call.isMissed) DramaRed else MoneyGreen,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${call.durationOrVoicemail} • ${call.time}",
                                        fontSize = 10.5.sp,
                                        color = Color(0xFF64748B)
                                    )
                                }
                            }

                            if (call.audioSnippet != null) {
                                IconButton(
                                    onClick = {
                                        activeVoicemailIndex = if (isExpanded) null else idx
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = "Listen",
                                        tint = Color(0xFF0F766E)
                                    )
                                }
                            }
                        }

                        if (isExpanded && call.audioSnippet != null) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Surface(
                                color = Color(0xFFF0FDF4),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(8.dp)) {
                                    Text(
                                        text = "Audio Transcript:",
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF15803D)
                                    )
                                    Text(
                                        text = call.audioSnippet,
                                        fontSize = 11.5.sp,
                                        fontStyle = FontStyle.Italic,
                                        color = Color(0xFF1E293B)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
