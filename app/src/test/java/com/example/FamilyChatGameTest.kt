package com.example

import com.example.data.ScenarioProvider
import com.example.model.*
import org.junit.Assert.*
import org.junit.Test

class FamilyChatGameTest {

    @Test
    fun testAllFiftyScenariosLoaded() {
        val scenarios = ScenarioProvider.getAll()
        assertEquals(50, scenarios.size)
        // Verify chapters 1 through 10
        val chapters = scenarios.map { it.chapter }.distinct()
        assertEquals(10, chapters.size)
        for (ch in 1..10) {
            val count = scenarios.count { it.chapter == ch }
            assertEquals("Chapter $ch should have 5 scenarios", 5, count)
        }
    }

    @Test
    fun testTwentyCharactersDefined() {
        // Player is separate, 20 family characters
        val familyMembers = CharacterId.entries.filter { it != CharacterId.PLAYER }
        assertEquals(20, familyMembers.size)
        familyMembers.forEach { char ->
            assertFalse(char.displayName.isBlank())
            assertFalse(char.catchphrase.isBlank())
            assertNotNull(char.emoji)
        }
    }

    @Test
    fun testFiveEndingsConfigured() {
        assertEquals(5, EndingType.entries.size)
        val ch50 = ScenarioProvider.getById(50)
        assertNotNull(ch50)
        val endingChoices = ch50!!.choices.mapNotNull { it.setsEnding }
        assertEquals(5, endingChoices.size)
    }

    @Test
    fun testTenSecretsConfigured() {
        val secrets = SecretRepository.getInitialSecrets()
        assertEquals(10, secrets.size)
    }

    @Test
    fun testSevenAchievementsConfigured() {
        val achievements = AchievementRepository.getInitialAchievements()
        assertEquals(7, achievements.size)
    }

    @Test
    fun testPlayerProfileStatsClamping() {
        val profile = PlayerProfile(
            reputation = 120,
            trust = -20,
            drama = 150,
            money = -500
        ).clampStats()

        assertEquals(100, profile.reputation)
        assertEquals(0, profile.trust)
        assertEquals(100, profile.drama)
        assertEquals(0, profile.money)
    }
}
