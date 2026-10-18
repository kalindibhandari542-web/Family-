package com.example.model

data class AchievementItem(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val isUnlocked: Boolean = false,
    val currentProgress: Int = 0,
    val maxProgress: Int = 1
)

object AchievementRepository {
    fun getInitialAchievements(): List<AchievementItem> = listOf(
        AchievementItem(
            id = "ach_therapist",
            title = "Family Therapist",
            description = "De-escalated heated family arguments in the group chat.",
            icon = "🕊️",
            maxProgress = 10
        ),
        AchievementItem(
            id = "ach_gossip_king",
            title = "Gossip King",
            description = "Uncovered juicy hidden secrets in the family vault.",
            icon = "🤫",
            maxProgress = 10
        ),
        AchievementItem(
            id = "ach_screenshot",
            title = "Screenshot Survivor",
            description = "Survive dangerous forwarded screenshots and wrong-group blunders.",
            icon = "📸",
            maxProgress = 5
        ),
        AchievementItem(
            id = "ach_perfect_family",
            title = "Sanskaari Child",
            description = "Attained 90+ Family Trust and gained Dadi's highest blessing.",
            icon = "🙏",
            maxProgress = 90
        ),
        AchievementItem(
            id = "ach_chaos_creator",
            title = "Kalesh Maestro",
            description = "Allowed Family Drama to skyrocket past 85+ and watched the fireworks.",
            icon = "🔥",
            maxProgress = 85
        ),
        AchievementItem(
            id = "ach_detective",
            title = "Master Detective",
            description = "Unmasked the true identity behind the Unknown Number.",
            icon = "🕵️‍♂️",
            maxProgress = 1
        ),
        AchievementItem(
            id = "ach_legend",
            title = "Family Legend",
            description = "Experienced all 5 dramatic story endings of Apna Parivaar.",
            icon = "🏆",
            maxProgress = 5
        )
    )
}
