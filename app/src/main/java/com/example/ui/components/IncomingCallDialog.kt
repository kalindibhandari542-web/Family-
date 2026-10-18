package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CallEnd
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.SimulatedCall
import com.example.ui.theme.DramaRed
import com.example.ui.theme.MoneyGreen

@Composable
fun IncomingCallDialog(
    call: SimulatedCall?,
    isCallOngoing: Boolean,
    onAnswer: () -> Unit,
    onDecline: () -> Unit
) {
    if (call == null) return

    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.15f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Dialog(
        onDismissRequest = onDecline,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF0F172A), Color(0xFF1E293B), Color(0xFF0F5A47))
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (isCallOngoing) "CALL IN PROGRESS" else "INCOMING WHATSAPP CALL...",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF94A3B8),
                    letterSpacing = 1.5.sp
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Avatar with pulsating ring
                Box(
                    modifier = Modifier
                        .size(110.dp)
                        .scale(if (!isCallOngoing) scale else 1f)
                        .clip(CircleShape)
                        .background(call.character.avatarColor),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = call.character.emoji, fontSize = 48.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = call.character.displayName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = call.character.relationshipTitle,
                    fontSize = 13.sp,
                    color = Color(0xFFCBD5E1)
                )

                Spacer(modifier = Modifier.height(28.dp))

                if (isCallOngoing) {
                    // Call speech dialogue box
                    Surface(
                        color = Color.White.copy(alpha = 0.12f),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "🔊 Speaker On (${call.durationText})",
                                    fontSize = 11.sp,
                                    color = Color(0xFF38BDF8),
                                    fontWeight = FontWeight.SemiBold
                                )
                                Icon(
                                    imageVector = Icons.Default.VolumeUp,
                                    contentDescription = "Speaker",
                                    tint = Color(0xFF38BDF8),
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "“${call.monologueScript}”",
                                fontSize = 14.sp,
                                color = Color.White,
                                lineHeight = 20.sp,
                                textAlign = TextAlign.Start
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(36.dp))

                    // End Call button
                    FloatingActionButton(
                        onClick = onDecline,
                        containerColor = DramaRed,
                        contentColor = Color.White,
                        shape = CircleShape,
                        modifier = Modifier.size(64.dp)
                    ) {
                        Icon(imageVector = Icons.Default.CallEnd, contentDescription = "End Call", modifier = Modifier.size(28.dp))
                    }
                } else {
                    // Ringing state: Answer (Green) or Decline (Red)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Decline
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            FloatingActionButton(
                                onClick = onDecline,
                                containerColor = DramaRed,
                                contentColor = Color.White,
                                shape = CircleShape,
                                modifier = Modifier.size(64.dp)
                            ) {
                                Icon(imageVector = Icons.Default.CallEnd, contentDescription = "Decline", modifier = Modifier.size(28.dp))
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Decline", fontSize = 12.sp, color = Color(0xFFE2E8F0))
                        }

                        // Answer
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            FloatingActionButton(
                                onClick = onAnswer,
                                containerColor = MoneyGreen,
                                contentColor = Color.White,
                                shape = CircleShape,
                                modifier = Modifier.size(64.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Call, contentDescription = "Answer", modifier = Modifier.size(28.dp))
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Answer", fontSize = 12.sp, color = Color(0xFFE2E8F0))
                        }
                    }
                }
            }
        }
    }
}
