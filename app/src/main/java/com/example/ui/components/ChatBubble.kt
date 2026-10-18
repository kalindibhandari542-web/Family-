package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CharacterId
import com.example.model.ChatMessage
import com.example.ui.theme.IncomingBubbleDark
import com.example.ui.theme.IncomingBubbleLight
import com.example.ui.theme.OutgoingBubbleDark
import com.example.ui.theme.OutgoingBubbleLight

@Composable
fun ChatBubble(
    message: ChatMessage,
    relationshipScore: Int = 50,
    onAvatarClick: ((CharacterId) -> Unit)? = null
) {
    val isPlayer = message.isFromPlayer
    val isSystem = message.isSystemAnnouncement

    if (isSystem) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.85f),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = message.text,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        return
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        horizontalArrangement = if (isPlayer) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        if (!isPlayer) {
            // Sender Avatar
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(CircleShape)
                    .background(message.sender.avatarColor)
                    .clickable { onAvatarClick?.invoke(message.sender) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = message.sender.emoji,
                    fontSize = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
        }

        // Bubble Container
        Column(
            modifier = Modifier.widthIn(max = 280.dp),
            horizontalAlignment = if (isPlayer) Alignment.End else Alignment.Start
        ) {
            Surface(
                color = if (isPlayer) {
                    OutgoingBubbleLight
                } else {
                    IncomingBubbleLight
                },
                shape = RoundedCornerShape(
                    topStart = 16.dp,
                    topEnd = 16.dp,
                    bottomStart = if (isPlayer) 16.dp else 2.dp,
                    bottomEnd = if (isPlayer) 2.dp else 16.dp
                ),
                shadowElevation = 1.dp,
                modifier = Modifier.border(
                    width = 0.5.dp,
                    color = Color.Black.copy(alpha = 0.06f),
                    shape = RoundedCornerShape(14.dp)
                )
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    // Header for incoming messages
                    if (!isPlayer) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = message.sender.displayName,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = message.sender.avatarColor
                            )
                            val tier = message.sender.relationshipTier(relationshipScore)
                            Text(
                                text = tier.label,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = tier.badgeColor
                            )
                        }
                    }

                    // Forwarded indicator
                    if (message.isForwarded) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 2.dp)
                        ) {
                            Text(
                                text = "↗ Forwarded many times",
                                fontSize = 10.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.Gray
                            )
                        }
                    }

                    // Deleted message state
                    if (message.isDeleted) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "🚫 This message was deleted",
                                fontSize = 12.sp,
                                fontStyle = FontStyle.Italic,
                                color = Color.Gray
                            )
                        }
                    } else if (message.isVoiceNote) {
                        // Voice note simulation
                        VoiceNoteBubbleContent(
                            duration = message.voiceDuration,
                            transcript = message.voiceTranscript
                        )
                    } else {
                        // Image Card if attached
                        if (message.imageResName != null) {
                            CardImageContent(imageName = message.imageResName)
                            Spacer(modifier = Modifier.height(4.dp))
                        }

                        // Message Text
                        Text(
                            text = message.text,
                            fontSize = 13.5.sp,
                            color = Color(0xFF111B21),
                            lineHeight = 18.sp
                        )
                    }

                    // Timestamp & Read ticks
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 2.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = message.timestamp,
                            fontSize = 9.5.sp,
                            color = Color.Gray
                        )
                        if (isPlayer) {
                            Spacer(modifier = Modifier.width(3.dp))
                            Icon(
                                imageVector = Icons.Default.DoneAll,
                                contentDescription = "Read",
                                tint = Color(0xFF34B7F1),
                                modifier = Modifier.size(13.dp)
                            )
                        }
                    }
                }
            }

            // Reactions pill if any
            if (message.reactions.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .offset(y = (-6).dp)
                        .padding(horizontal = 6.dp)
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .border(0.5.dp, Color(0xFFDDDDDD), RoundedCornerShape(12.dp))
                        .padding(horizontal = 6.dp, vertical = 1.dp)
                ) {
                    Text(
                        text = message.reactions.joinToString(" "),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun VoiceNoteBubbleContent(
    duration: String,
    transcript: String?
) {
    var isPlaying by remember { mutableStateOf(false) }
    var showTranscript by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { isPlaying = !isPlaying },
                modifier = Modifier.size(36.dp)
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Stop else Icons.Default.PlayArrow,
                    contentDescription = if (isPlaying) "Stop" else "Play",
                    tint = Color(0xFF0F5A47),
                    modifier = Modifier.size(24.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                // Waveform bar
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    repeat(18) { idx ->
                        val height = if (isPlaying) {
                            (8 + ((idx * 7) % 18)).dp
                        } else {
                            (6 + ((idx * 3) % 12)).dp
                        }
                        Box(
                            modifier = Modifier
                                .width(3.dp)
                                .height(height)
                                .background(
                                    if (idx < 8 && isPlaying) Color(0xFF0F5A47) else Color.Gray.copy(alpha = 0.5f),
                                    RoundedCornerShape(2.dp)
                                )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = if (isPlaying) "0:07 / $duration" else duration, fontSize = 10.sp, color = Color.Gray)
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = "Audio",
                        tint = Color(0xFF0F5A47),
                        modifier = Modifier.size(12.dp)
                    )
                }
            }
        }

        // Tap for transcript button
        if (transcript != null) {
            TextButton(
                onClick = { showTranscript = !showTranscript },
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier.height(26.dp)
            ) {
                Text(
                    text = if (showTranscript) "Hide transcript 🔼" else "Tap for transcript 📝",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF0F5A47)
                )
            }

            AnimatedVisibility(visible = showTranscript) {
                Surface(
                    color = Color(0xFFFFF9C4),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                ) {
                    Text(
                        text = "“$transcript”",
                        fontSize = 11.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFF3E2723),
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun CardImageContent(imageName: String) {
    val context = LocalContext.current
    val resId = remember(imageName) {
        context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    if (resId != 0) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = "Chat media",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFFE0F2F1), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "📸 [Photo Attachment]", fontSize = 12.sp, color = Color(0xFF00695C))
        }
    }
}
