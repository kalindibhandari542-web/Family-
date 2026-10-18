package com.example.ui.components

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ChatChoice
import com.example.model.SecretItem
import com.example.ui.theme.EmeraldGreenDark
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.ui.theme.SaffronGold
import com.example.ui.theme.WarmGold

@Composable
fun DecisionBar(
    choices: List<ChatChoice>,
    isChoiceMade: Boolean,
    impactText: String?,
    secretsList: List<SecretItem>,
    onSelectChoice: (ChatChoice) -> Unit,
    onProceedNext: () -> Unit,
    onViewEnding: (() -> Unit)? = null,
    hasEndingTriggered: Boolean = false,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = Color(0xFFF0F2F5),
        shadowElevation = 8.dp,
        tonalElevation = 4.dp,
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isChoiceMade) {
                // Header prompt
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "🤔 Choose your reply (Think before you send!):",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = EmeraldGreenDark
                    )
                }

                // List of selectable choices
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    choices.forEach { choice ->
                        // Check if choice is secret-dependent
                        val isLocked = choice.requiredSecretId != null &&
                                secretsList.none { it.id == choice.requiredSecretId && it.isDiscovered }

                        if (!isLocked) {
                            ChoiceCard(
                                choice = choice,
                                onClick = { onSelectChoice(choice) }
                            )
                        }
                    }
                }
            } else {
                // Choice consequence & Next Step
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (impactText != null) {
                        Surface(
                            color = Color(0xFFE8F5E9),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF81C784))
                        ) {
                            Text(
                                text = "✨ $impactText",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1B5E20),
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Share Card Button
                        OutlinedButton(
                            onClick = {
                                val shareIntent = Intent().apply {
                                    action = Intent.ACTION_SEND
                                    putExtra(Intent.EXTRA_TEXT, "Family WhatsApp Drama: $impactText! Playing 'Family WhatsApp' 😂")
                                    type = "text/plain"
                                }
                                context.startActivity(Intent.createChooser(shareIntent, "Share with friends"))
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Share, contentDescription = "Share", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Share", fontSize = 12.sp)
                        }

                        // Next / View Ending Button
                        Button(
                            onClick = {
                                if (hasEndingTriggered && onViewEnding != null) {
                                    onViewEnding()
                                } else {
                                    onProceedNext()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (hasEndingTriggered) SaffronGold else EmeraldGreenPrimary
                            ),
                            modifier = Modifier.weight(1.5f),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = if (hasEndingTriggered) "View Ending 🏆" else "Continue Story ➡️",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.NavigateNext,
                                contentDescription = "Next",
                                modifier = Modifier.size(18.dp),
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ChoiceCard(
    choice: ChatChoice,
    onClick: () -> Unit
) {
    val isSecret = choice.isSecretUnlock
    val borderColor = if (isSecret) WarmGold else Color(0xFFD0D7DE)
    val backgroundBrush = if (isSecret) {
        Brush.horizontalGradient(listOf(Color(0xFFFFFDE7), Color(0xFFFFF8E1)))
    } else {
        Brush.horizontalGradient(listOf(Color.White, Color.White))
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .border(
                width = if (isSecret) 1.5.dp else 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            ),
        shape = RoundedCornerShape(12.dp),
        shadowElevation = 1.dp
    ) {
        Box(modifier = Modifier.background(backgroundBrush)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isSecret) {
                    Icon(
                        imageVector = Icons.Default.LockOpen,
                        contentDescription = "Secret Choice",
                        tint = WarmGold,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = choice.text,
                        fontSize = 12.5.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF1E293B)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = choice.consequenceBadge,
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (isSecret) WarmGold else EmeraldGreenDark
                    )
                }

                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Send",
                    tint = EmeraldGreenPrimary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
