package com.example.model

enum class Gender(val label: String) {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Non-Binary")
}

enum class PersonalityStyle(val title: String, val description: String, val bonus: String) {
    DIPLOMAT("The Diplomat", "Calm mediator who diffuses fights and keeps peace.", "+10 Starting Trust"),
    MEME_LORD("Meme Lord", "Deflects awkward questions with savage humor.", "-5 Starting Drama"),
    INNOCENT_ONE("The Innocent One", "Acts completely naive to extract secrets easily.", "+₹2,000 Starting Shagun"),
    THE_REBEL("The Rebel", "Unfiltered truth-teller who fears no relative.", "Unlocked Savage Options")
}

data class PlayerProfile(
    val name: String = "Aarav",
    val gender: Gender = Gender.MALE,
    val age: Int = 24,
    val avatarId: Int = 1,
    val personalityStyle: PersonalityStyle = PersonalityStyle.DIPLOMAT,
    val reputation: Int = 50,
    val trust: Int = 50,
    val money: Int = 10000,
    val drama: Int = 10,
    val secretsCount: Int = 0
) {
    fun clampStats(): PlayerProfile {
        return copy(
            reputation = reputation.coerceIn(0, 100),
            trust = trust.coerceIn(0, 100),
            drama = drama.coerceIn(0, 100),
            money = money.coerceAtLeast(0)
        )
    }
}
