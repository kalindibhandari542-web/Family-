package com.example.model

data class ChatMessage(
    val id: String,
    val sender: CharacterId,
    val text: String,
    val timestamp: String = "10:14 AM",
    val isFromPlayer: Boolean = false,
    val isForwarded: Boolean = false,
    val isDeleted: Boolean = false,
    val isVoiceNote: Boolean = false,
    val voiceDuration: String = "0:18",
    val voiceTranscript: String? = null,
    val imageResName: String? = null, // "img_good_morning", "img_wedding_card", "img_secret_deed"
    val reactions: List<String> = emptyList(), // "❤️", "😂", "🙏"
    val isSystemAnnouncement: Boolean = false,
    val isPrivateChat: Boolean = false
)

data class ChatChoice(
    val id: String,
    val text: String,
    val isSecretUnlock: Boolean = false,
    val requiredSecretId: String? = null,
    val requiredPersonality: PersonalityStyle? = null,
    val consequenceBadge: String, // e.g. "+5 Trust • -5 Drama"
    val deltaTrust: Int = 0,
    val deltaDrama: Int = 0,
    val deltaMoney: Int = 0,
    val deltaReputation: Int = 0,
    val relationshipImpact: Map<CharacterId, Int> = emptyMap(),
    val unlocksSecretId: String? = null,
    val triggersCall: SimulatedCall? = null,
    val triggersNotification: InAppNotification? = null,
    val followupMessages: List<ChatMessage> = emptyList(),
    val setsEnding: EndingType? = null
)

data class ScenarioData(
    val scenarioId: Int, // 1..50
    val chapter: Int,    // 1..10
    val title: String,
    val category: String,
    val isPrivateChatWith: CharacterId? = null, // null = Apna Parivaar group
    val introDialogues: List<ChatMessage>,
    val choices: List<ChatChoice>
)
