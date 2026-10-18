package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.audio.HapticFeedbackHelper
import com.example.audio.SoundPlayer
import com.example.data.GameRepository
import com.example.model.EndingType
import com.example.model.PhoneApp
import com.example.ui.components.IncomingCallDialog
import com.example.ui.components.NotificationBanner
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme

enum class ScreenDestination {
    GROUP_CHAT,
    PHONE_HOME,
    PRIVATE_CHATS,
    CALLS,
    GALLERY,
    SECRET_BOOK,
    ACHIEVEMENTS,
    DAILY_EVENTS,
    SHOP,
    SETTINGS,
    ENDING
}

class MainActivity : ComponentActivity() {

    private lateinit var soundPlayer: SoundPlayer
    private lateinit var hapticHelper: HapticFeedbackHelper
    private lateinit var repository: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        soundPlayer = SoundPlayer()
        hapticHelper = HapticFeedbackHelper(this)
        repository = GameRepository(this, soundPlayer, hapticHelper)

        setContent {
            MyApplicationTheme {
                val gameState by repository.gameState.collectAsStateWithLifecycle()
                var currentScreen by remember { mutableStateOf(ScreenDestination.GROUP_CHAT) }

                // Automatically route to Ending screen if ending is triggered
                LaunchedEffect(gameState.currentEnding) {
                    if (gameState.currentEnding != null) {
                        currentScreen = ScreenDestination.ENDING
                    }
                }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        if (!gameState.isOnboarded) {
                            OnboardingScreen(
                                onComplete = { profile ->
                                    repository.completeOnboarding(profile)
                                    currentScreen = ScreenDestination.GROUP_CHAT
                                }
                            )
                        } else {
                            // Subscreen navigation with proper BackHandler
                            when (currentScreen) {
                                ScreenDestination.GROUP_CHAT -> {
                                    GroupChatScreen(
                                        gameState = gameState,
                                        onSelectChoice = { choice ->
                                            repository.chooseOption(choice)
                                        },
                                        onProceedNext = {
                                            repository.proceedToNextScenario()
                                        },
                                        onNavigateHome = {
                                            currentScreen = ScreenDestination.PHONE_HOME
                                        },
                                        onNavigateSettings = {
                                            currentScreen = ScreenDestination.SETTINGS
                                        },
                                        onViewEnding = {
                                            if (gameState.currentEnding != null) {
                                                currentScreen = ScreenDestination.ENDING
                                            }
                                        }
                                    )
                                }
                                ScreenDestination.PHONE_HOME -> {
                                    BackHandler { currentScreen = ScreenDestination.GROUP_CHAT }
                                    PhoneHomeScreen(
                                        gameState = gameState,
                                        onOpenApp = { app ->
                                            currentScreen = when (app) {
                                                PhoneApp.FAMILY_CHAT -> ScreenDestination.GROUP_CHAT
                                                PhoneApp.PRIVATE_CHATS -> ScreenDestination.PRIVATE_CHATS
                                                PhoneApp.CALLS -> ScreenDestination.CALLS
                                                PhoneApp.GALLERY -> ScreenDestination.GALLERY
                                                PhoneApp.SECRET_BOOK -> ScreenDestination.SECRET_BOOK
                                                PhoneApp.ACHIEVEMENTS -> ScreenDestination.ACHIEVEMENTS
                                                PhoneApp.DAILY_EVENTS -> ScreenDestination.DAILY_EVENTS
                                                PhoneApp.SHOP -> ScreenDestination.SHOP
                                                PhoneApp.SETTINGS -> ScreenDestination.SETTINGS
                                            }
                                        }
                                    )
                                }
                                ScreenDestination.PRIVATE_CHATS -> {
                                    PrivateChatsScreen(
                                        gameState = gameState,
                                        onBack = { currentScreen = ScreenDestination.PHONE_HOME }
                                    )
                                }
                                ScreenDestination.CALLS -> {
                                    CallsScreen(
                                        profile = gameState.playerProfile,
                                        onBack = { currentScreen = ScreenDestination.PHONE_HOME }
                                    )
                                }
                                ScreenDestination.GALLERY -> {
                                    GalleryScreen(
                                        profile = gameState.playerProfile,
                                        onBack = { currentScreen = ScreenDestination.PHONE_HOME }
                                    )
                                }
                                ScreenDestination.SECRET_BOOK -> {
                                    SecretBookScreen(
                                        secrets = gameState.secrets,
                                        profile = gameState.playerProfile,
                                        onBack = { currentScreen = ScreenDestination.PHONE_HOME }
                                    )
                                }
                                ScreenDestination.ACHIEVEMENTS -> {
                                    AchievementsScreen(
                                        achievements = gameState.achievements,
                                        profile = gameState.playerProfile,
                                        onBack = { currentScreen = ScreenDestination.PHONE_HOME }
                                    )
                                }
                                ScreenDestination.DAILY_EVENTS -> {
                                    DailyEventsScreen(
                                        events = gameState.dailyEvents,
                                        profile = gameState.playerProfile,
                                        onCompleteEvent = { id, opt ->
                                            repository.completeDailyEvent(id, opt)
                                        },
                                        onBack = { currentScreen = ScreenDestination.PHONE_HOME }
                                    )
                                }
                                ScreenDestination.SHOP -> {
                                    ShopScreen(
                                        profile = gameState.playerProfile,
                                        currentWallpaperIndex = gameState.wallpaperIndex,
                                        unlockedPacks = gameState.bonusPacksUnlocked,
                                        onSelectWallpaper = { repository.setWallpaper(it) },
                                        onUnlockPack = { repository.unlockBonusPack(it) },
                                        onBack = { currentScreen = ScreenDestination.PHONE_HOME }
                                    )
                                }
                                ScreenDestination.SETTINGS -> {
                                    SettingsReplayScreen(
                                        gameState = gameState,
                                        onToggleSound = { repository.toggleSound(it) },
                                        onToggleHaptics = { repository.toggleHaptics(it) },
                                        onJumpToChapter = { scenarioId ->
                                            repository.jumpToScenario(scenarioId)
                                            currentScreen = ScreenDestination.GROUP_CHAT
                                        },
                                        onResetGame = {
                                            repository.resetGame()
                                            currentScreen = ScreenDestination.GROUP_CHAT
                                        },
                                        onBack = { currentScreen = ScreenDestination.GROUP_CHAT }
                                    )
                                }
                                ScreenDestination.ENDING -> {
                                    val ending = gameState.currentEnding ?: EndingType.HAPPY_FAMILY
                                    EndingScreen(
                                        ending = ending,
                                        unlockedEndings = gameState.unlockedEndings,
                                        profile = gameState.playerProfile,
                                        onPlayAgain = {
                                            repository.jumpToScenario(1)
                                            currentScreen = ScreenDestination.GROUP_CHAT
                                        },
                                        onViewSecrets = {
                                            currentScreen = ScreenDestination.SECRET_BOOK
                                        }
                                    )
                                }
                            }

                            // Notification Banner overlay
                            NotificationBanner(
                                notification = gameState.activeNotification,
                                onDismiss = { repository.dismissNotification() },
                                onClick = {
                                    val target = gameState.activeNotification?.targetApp
                                    if (target != null) {
                                        currentScreen = when (target) {
                                            PhoneApp.FAMILY_CHAT -> ScreenDestination.GROUP_CHAT
                                            PhoneApp.PRIVATE_CHATS -> ScreenDestination.PRIVATE_CHATS
                                            else -> ScreenDestination.GROUP_CHAT
                                        }
                                    }
                                }
                            )

                            // Incoming Call Modal Dialog overlay
                            IncomingCallDialog(
                                call = gameState.activeCall,
                                isCallOngoing = gameState.activeCallOngoing,
                                onAnswer = { repository.answerCall() },
                                onDecline = { repository.endCall() }
                            )
                        }
                    }
                }
            }
        }
    }
}
