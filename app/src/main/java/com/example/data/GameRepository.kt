package com.example.data

import android.content.Context
import android.content.SharedPreferences
import com.example.audio.HapticFeedbackHelper
import com.example.audio.SoundPlayer
import com.example.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameRepository(
    context: Context,
    private val soundPlayer: SoundPlayer,
    private val hapticHelper: HapticFeedbackHelper
) {
    private val prefs: SharedPreferences = context.getSharedPreferences("family_chat_save_data", Context.MODE_PRIVATE)

    data class GameState(
        val isOnboarded: Boolean = false,
        val playerProfile: PlayerProfile = PlayerProfile(),
        val currentScenarioId: Int = 1,
        val maxChapterUnlocked: Int = 1,
        val relationships: Map<CharacterId, Int> = CharacterId.entries.associateWith { 50 },
        val currentScenario: ScenarioData? = ScenarioProvider.getById(1),
        val currentMessages: List<ChatMessage> = ScenarioProvider.getById(1)?.introDialogues ?: emptyList(),
        val secrets: List<SecretItem> = SecretRepository.getInitialSecrets(),
        val achievements: List<AchievementItem> = AchievementRepository.getInitialAchievements(),
        val unlockedEndings: Set<EndingType> = emptySet(),
        val currentEnding: EndingType? = null,
        val activeNotification: InAppNotification? = null,
        val activeCall: SimulatedCall? = null,
        val activeCallOngoing: Boolean = false,
        val isSoundEnabled: Boolean = true,
        val isHapticsEnabled: Boolean = true,
        val currentThemeIndex: Int = 0,
        val wallpaperIndex: Int = 0,
        val bonusPacksUnlocked: Set<String> = emptySet(),
        val lastChoiceImpactText: String? = null,
        val dailyEvents: List<DailyEventItem> = createDefaultDailyEvents()
    )

    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    init {
        loadSavedGame()
    }

    private fun loadSavedGame() {
        val onboarded = prefs.getBoolean("is_onboarded", false)
        val name = prefs.getString("player_name", "Aarav") ?: "Aarav"
        val genderStr = prefs.getString("player_gender", Gender.MALE.name) ?: Gender.MALE.name
        val age = prefs.getInt("player_age", 24)
        val avatarId = prefs.getInt("player_avatar", 1)
        val styleStr = prefs.getString("player_style", PersonalityStyle.DIPLOMAT.name) ?: PersonalityStyle.DIPLOMAT.name
        val reputation = prefs.getInt("stat_reputation", 50)
        val trust = prefs.getInt("stat_trust", 50)
        val money = prefs.getInt("stat_money", 10000)
        val drama = prefs.getInt("stat_drama", 10)
        val scenarioId = prefs.getInt("current_scenario_id", 1).coerceIn(1, 50)
        val maxChapter = prefs.getInt("max_chapter", 1).coerceIn(1, 10)
        val soundOn = prefs.getBoolean("sound_on", true)
        val hapticsOn = prefs.getBoolean("haptics_on", true)

        soundPlayer.isSoundEnabled = soundOn
        hapticHelper.isHapticsEnabled = hapticsOn

        val gender = try { Gender.valueOf(genderStr) } catch (_: Exception) { Gender.MALE }
        val style = try { PersonalityStyle.valueOf(styleStr) } catch (_: Exception) { PersonalityStyle.DIPLOMAT }

        val profile = PlayerProfile(
            name = name,
            gender = gender,
            age = age,
            avatarId = avatarId,
            personalityStyle = style,
            reputation = reputation,
            trust = trust,
            money = money,
            drama = drama
        ).clampStats()

        // Load relationships
        val relationshipsMap = CharacterId.entries.associateWith { character ->
            prefs.getInt("rel_${character.name}", 50)
        }

        // Load discovered secrets
        val discoveredSecretIds = prefs.getStringSet("discovered_secrets", emptySet()) ?: emptySet()
        val secretsList = SecretRepository.getInitialSecrets().map { secret ->
            if (discoveredSecretIds.contains(secret.id)) {
                secret.copy(isDiscovered = true)
            } else {
                secret
            }
        }

        // Load endings
        val unlockedEndingNames = prefs.getStringSet("unlocked_endings", emptySet()) ?: emptySet()
        val endingsSet = unlockedEndingNames.mapNotNull { nameStr ->
            try { EndingType.valueOf(nameStr) } catch (_: Exception) { null }
        }.toSet()

        val scenario = ScenarioProvider.getById(scenarioId) ?: ScenarioProvider.getById(1)

        _gameState.update {
            it.copy(
                isOnboarded = onboarded,
                playerProfile = profile.copy(secretsCount = secretsList.count { s -> s.isDiscovered }),
                currentScenarioId = scenarioId,
                maxChapterUnlocked = maxChapter,
                relationships = relationshipsMap,
                currentScenario = scenario,
                currentMessages = scenario?.introDialogues ?: emptyList(),
                secrets = secretsList,
                unlockedEndings = endingsSet,
                isSoundEnabled = soundOn,
                isHapticsEnabled = hapticsOn
            )
        }
        checkAchievements()
    }

    private fun persistGame() {
        val state = _gameState.value
        prefs.edit().apply {
            putBoolean("is_onboarded", state.isOnboarded)
            putString("player_name", state.playerProfile.name)
            putString("player_gender", state.playerProfile.gender.name)
            putInt("player_age", state.playerProfile.age)
            putInt("player_avatar", state.playerProfile.avatarId)
            putString("player_style", state.playerProfile.personalityStyle.name)
            putInt("stat_reputation", state.playerProfile.reputation)
            putInt("stat_trust", state.playerProfile.trust)
            putInt("stat_money", state.playerProfile.money)
            putInt("stat_drama", state.playerProfile.drama)
            putInt("current_scenario_id", state.currentScenarioId)
            putInt("max_chapter", state.maxChapterUnlocked)
            putBoolean("sound_on", state.isSoundEnabled)
            putBoolean("haptics_on", state.isHapticsEnabled)

            state.relationships.forEach { (char, score) ->
                putInt("rel_${char.name}", score)
            }

            putStringSet(
                "discovered_secrets",
                state.secrets.filter { it.isDiscovered }.map { it.id }.toSet()
            )

            putStringSet(
                "unlocked_endings",
                state.unlockedEndings.map { it.name }.toSet()
            )
            apply()
        }
    }

    fun completeOnboarding(profile: PlayerProfile) {
        val initialTrust = if (profile.personalityStyle == PersonalityStyle.DIPLOMAT) 60 else 50
        val initialDrama = if (profile.personalityStyle == PersonalityStyle.MEME_LORD) 5 else 10
        val initialMoney = if (profile.personalityStyle == PersonalityStyle.INNOCENT_ONE) 12000 else 10000

        val adjustedProfile = profile.copy(
            trust = initialTrust,
            drama = initialDrama,
            money = initialMoney,
            reputation = 50,
            secretsCount = 0
        )

        val scenario = ScenarioProvider.getById(1)
        _gameState.update {
            it.copy(
                isOnboarded = true,
                playerProfile = adjustedProfile,
                currentScenarioId = 1,
                maxChapterUnlocked = 1,
                currentScenario = scenario,
                currentMessages = scenario?.introDialogues ?: emptyList(),
                currentEnding = null
            )
        }
        soundPlayer.playSuccessChime()
        hapticHelper.vibrateNotification()
        persistGame()
    }

    fun chooseOption(choice: ChatChoice) {
        val current = _gameState.value
        soundPlayer.playMessageSent()
        hapticHelper.vibrateNotification()

        // 1. Add player's message bubble
        val playerMsg = ChatMessage(
            id = "choice_msg_${System.currentTimeMillis()}",
            sender = CharacterId.PLAYER,
            text = choice.text,
            isFromPlayer = true,
            reactions = listOf("👍")
        )

        val updatedMessages = current.currentMessages + playerMsg + choice.followupMessages

        // 2. Adjust stats
        val newReputation = (current.playerProfile.reputation + choice.deltaReputation).coerceIn(0, 100)
        val newTrust = (current.playerProfile.trust + choice.deltaTrust).coerceIn(0, 100)
        val newMoney = (current.playerProfile.money + choice.deltaMoney).coerceAtLeast(0)
        val newDrama = (current.playerProfile.drama + choice.deltaDrama).coerceIn(0, 100)

        // 3. Adjust relationships
        val updatedRels = current.relationships.toMutableMap()
        choice.relationshipImpact.forEach { (char, delta) ->
            val prev = updatedRels[char] ?: 50
            updatedRels[char] = (prev + delta).coerceIn(0, 100)
        }

        // 4. Check secret unlocks
        var updatedSecrets = current.secrets
        if (choice.unlocksSecretId != null) {
            updatedSecrets = updatedSecrets.map { sec ->
                if (sec.id == choice.unlocksSecretId && !sec.isDiscovered) {
                    soundPlayer.playDramaticHit()
                    hapticHelper.vibrateDramatic()
                    sec.copy(isDiscovered = true, discoveryChapter = current.currentScenario?.chapter ?: 1)
                } else {
                    sec
                }
            }
        }

        // 5. Ending check
        var endingToSet = choice.setsEnding
        var newUnlockedEndings = current.unlockedEndings
        if (endingToSet != null) {
            newUnlockedEndings = newUnlockedEndings + endingToSet
            soundPlayer.playDramaticHit()
            hapticHelper.vibrateDramatic()
        }

        val updatedProfile = current.playerProfile.copy(
            reputation = newReputation,
            trust = newTrust,
            money = newMoney,
            drama = newDrama,
            secretsCount = updatedSecrets.count { it.isDiscovered }
        )

        _gameState.update {
            it.copy(
                playerProfile = updatedProfile,
                relationships = updatedRels,
                currentMessages = updatedMessages,
                secrets = updatedSecrets,
                unlockedEndings = newUnlockedEndings,
                currentEnding = endingToSet,
                activeNotification = choice.triggersNotification,
                activeCall = choice.triggersCall,
                lastChoiceImpactText = choice.consequenceBadge
            )
        }

        checkAchievements()
        persistGame()
    }

    fun proceedToNextScenario() {
        val current = _gameState.value
        val nextId = current.currentScenarioId + 1
        if (nextId <= ScenarioProvider.totalCount) {
            val nextScenario = ScenarioProvider.getById(nextId)
            val newMaxChapter = maxOf(current.maxChapterUnlocked, nextScenario?.chapter ?: 1)
            soundPlayer.playNotification()
            hapticHelper.vibrateNotification()

            _gameState.update {
                it.copy(
                    currentScenarioId = nextId,
                    maxChapterUnlocked = newMaxChapter,
                    currentScenario = nextScenario,
                    currentMessages = nextScenario?.introDialogues ?: emptyList(),
                    lastChoiceImpactText = null
                )
            }
            persistGame()
        }
    }

    fun jumpToScenario(scenarioId: Int) {
        val scenario = ScenarioProvider.getById(scenarioId) ?: return
        _gameState.update {
            it.copy(
                currentScenarioId = scenarioId,
                currentScenario = scenario,
                currentMessages = scenario.introDialogues,
                currentEnding = null,
                lastChoiceImpactText = null
            )
        }
        persistGame()
    }

    fun dismissNotification() {
        _gameState.update { it.copy(activeNotification = null) }
    }

    fun answerCall() {
        soundPlayer.playNotification()
        _gameState.update { it.copy(activeCallOngoing = true) }
    }

    fun endCall() {
        _gameState.update { it.copy(activeCall = null, activeCallOngoing = false) }
    }

    fun toggleSound(enabled: Boolean) {
        soundPlayer.isSoundEnabled = enabled
        _gameState.update { it.copy(isSoundEnabled = enabled) }
        persistGame()
    }

    fun toggleHaptics(enabled: Boolean) {
        hapticHelper.isHapticsEnabled = enabled
        _gameState.update { it.copy(isHapticsEnabled = enabled) }
        persistGame()
    }

    fun setWallpaper(index: Int) {
        _gameState.update { it.copy(wallpaperIndex = index) }
    }

    fun unlockBonusPack(packId: String) {
        _gameState.update {
            it.copy(bonusPacksUnlocked = it.bonusPacksUnlocked + packId)
        }
        soundPlayer.playSuccessChime()
    }

    fun completeDailyEvent(eventId: String, option: DailyEventOption) {
        val current = _gameState.value
        val newMoney = current.playerProfile.money + option.moneyReward
        val newTrust = (current.playerProfile.trust + option.trustDelta).coerceIn(0, 100)
        val newDrama = (current.playerProfile.drama + option.dramaDelta).coerceIn(0, 100)

        val updatedEvents = current.dailyEvents.map { ev ->
            if (ev.id == eventId) ev.copy(completed = true) else ev
        }

        soundPlayer.playSuccessChime()
        hapticHelper.vibrateNotification()

        _gameState.update {
            it.copy(
                playerProfile = it.playerProfile.copy(
                    money = newMoney,
                    trust = newTrust,
                    drama = newDrama
                ),
                dailyEvents = updatedEvents
            )
        }
        persistGame()
    }

    fun resetGame() {
        prefs.edit().clear().apply()
        _gameState.value = GameState(
            secrets = SecretRepository.getInitialSecrets(),
            achievements = AchievementRepository.getInitialAchievements()
        )
    }

    private fun checkAchievements() {
        val state = _gameState.value
        val updated = state.achievements.map { ach ->
            when (ach.id) {
                "ach_therapist" -> {
                    val resolved = (state.currentScenarioId / 5).coerceAtMost(10)
                    ach.copy(currentProgress = resolved, isUnlocked = resolved >= 10)
                }
                "ach_gossip_king" -> {
                    val count = state.secrets.count { it.isDiscovered }
                    ach.copy(currentProgress = count, isUnlocked = count >= 10)
                }
                "ach_screenshot" -> {
                    val survived = if (state.currentScenarioId >= 21) 5 else (state.currentScenarioId / 4)
                    ach.copy(currentProgress = survived, isUnlocked = survived >= 5)
                }
                "ach_perfect_family" -> {
                    ach.copy(currentProgress = state.playerProfile.trust, isUnlocked = state.playerProfile.trust >= 90)
                }
                "ach_chaos_creator" -> {
                    ach.copy(currentProgress = state.playerProfile.drama, isUnlocked = state.playerProfile.drama >= 85)
                }
                "ach_detective" -> {
                    val unkSecret = state.secrets.find { it.id == "sec_10" }?.isDiscovered == true
                    ach.copy(currentProgress = if (unkSecret) 1 else 0, isUnlocked = unkSecret)
                }
                "ach_legend" -> {
                    val count = state.unlockedEndings.size
                    ach.copy(currentProgress = count, isUnlocked = count >= 5)
                }
                else -> ach
            }
        }
        _gameState.update { it.copy(achievements = updated) }
    }

    companion object {
        fun createDefaultDailyEvents(): List<DailyEventItem> = listOf(
            DailyEventItem(
                id = "daily_1",
                title = "Morning Temple Sweet Distribution",
                emoji = "🛕",
                description = "Dadi wants someone to distribute hot Peda prasad to neighbours. Chacha says it's too early.",
                options = listOf(
                    DailyEventOption("Wake up at 6 AM and distribute with Dadi", trustDelta = 10, dramaDelta = -5, moneyReward = 500, responseDialogue = "Dadi gives you ₹500 blessing money and whole society praises your sanskaar!"),
                    DailyEventOption("Pretend to be asleep", trustDelta = -5, dramaDelta = 5, responseDialogue = "Dadi rings the pooja bell right outside your bedroom door.")
                )
            ),
            DailyEventItem(
                id = "daily_2",
                title = "Cricket Match Snack Controversy",
                emoji = "🏏",
                description = "India vs Australia final. Suresh Mama wants Samosas, Rajesh Dad says Pakoras are healthier.",
                options = listOf(
                    DailyEventOption("Fry crispy hot onion pakoras with mint chutney at home", trustDelta = 8, dramaDelta = -4, moneyReward = 300, responseDialogue = "Dad loves the crispiness! You saved restaurant money."),
                    DailyEventOption("Order 20 samosas and double jalebi on Swiggy", trustDelta = 5, dramaDelta = 8, responseDialogue = "Cousins cheer wildly! Dad grumbles about acidity.")
                )
            ),
            DailyEventItem(
                id = "daily_3",
                title = "Festival Cleaning (Diwali Safari)",
                emoji = "🧹",
                description = "Mom Sunita has declared an all-hands-on-deck deep cleaning of ceiling fans and brass utensils.",
                options = listOf(
                    DailyEventOption("Take charge of ceiling fans without complaining", trustDelta = 12, dramaDelta = -8, moneyReward = 1000, responseDialogue = "Mom is overjoyed! Gives you festival pocket money!"),
                    DailyEventOption("Hide in the washroom with your phone for 45 minutes", trustDelta = -8, dramaDelta = 12, responseDialogue = "Kabir exposes your hiding spot. Mom knocks with a broom.")
                )
            )
        )
    }
}
