package com.example.model

enum class PhoneApp(
    val title: String,
    val iconEmoji: String,
    val unreadBadge: Int = 0,
    val subtitle: String
) {
    FAMILY_CHAT("Apna Parivaar", "❤️", 0, "Active Group (21 members)"),
    PRIVATE_CHATS("Direct Messages", "💬", 0, "1-on-1 confidential chats"),
    CALLS("Phone & Audio", "📞", 0, "Voice notes & call history"),
    GALLERY("Family Gallery", "📸", 0, "Memes, Cards & Evidence"),
    SECRET_BOOK("Secret Book", "📖", 0, "Confidential family dossier"),
    ACHIEVEMENTS("Trophy Cabinet", "🏆", 0, "Badges & accomplishments"),
    DAILY_EVENTS("Daily Drama", "📅", 0, "Mini-events & bonus ₹"),
    SHOP("Bazaar & Themes", "🛍️", 0, "Wallpapers, Avatars, Packs"),
    SETTINGS("Settings & Replay", "⚙️", 0, "Audio, Chapters, Reset")
}

data class InAppNotification(
    val id: String,
    val title: String,
    val sender: CharacterId,
    val body: String,
    val isDeletedAlert: Boolean = false,
    val targetApp: PhoneApp = PhoneApp.FAMILY_CHAT,
    val timestamp: String = "Just now"
)

data class SimulatedCall(
    val character: CharacterId,
    val isIncoming: Boolean,
    val durationText: String = "0:45",
    val monologueScript: String
)

data class DailyEventItem(
    val id: String,
    val title: String,
    val emoji: String,
    val description: String,
    val options: List<DailyEventOption>,
    val completed: Boolean = false
)

data class DailyEventOption(
    val text: String,
    val trustDelta: Int = 0,
    val dramaDelta: Int = 0,
    val moneyReward: Int = 0,
    val responseDialogue: String
)
