package com.example.model

enum class EndingType(
    val title: String,
    val subtitle: String,
    val badge: String,
    val epilogueQuote: String,
    val storyConclusion: String,
    val shareableTitle: String
) {
    HAPPY_FAMILY(
        title = "Ending 1: Happy Family ❤️",
        subtitle = "Unity Through Empathy & Laughter",
        badge = "❤️",
        epilogueQuote = "“Sometimes family is complicated… but that's what makes it family.”",
        storyConclusion = "You chose compassion over ego. You protected your cousins, defused the uncles' arguments, and showed Mom that dinner always matters. At Neha's wedding anniversary party, the whole parivaar gathers for one giant selfie, laughter echoing through the house.",
        shareableTitle = "I survived Indian Family WhatsApp and kept the peace! ❤️👨‍👩‍👧‍👦"
    ),
    FAMILY_WAR(
        title = "Ending 2: Family War 💥",
        subtitle = "The Great Group Chat Silence",
        badge = "💥",
        epilogueQuote = "“You wanted the truth. You got it.”",
        storyConclusion = "Every hidden screenshot was forwarded. Every financial grievance was laid bare in Apna Parivaar. Within 24 hours, Anita Bua left the group. Mahesh Chacha blocked Rajesh Dad. The notifications stopped completely. The quietest WhatsApp has ever been.",
        shareableTitle = "I leaked every family secret and destroyed the WhatsApp group 💥💀"
    ),
    SECRET_MILLIONAIRE(
        title = "Ending 3: The Secret Millionaire 💰",
        subtitle = "The ₹4.5 Crore Ancestral Breakthrough",
        badge = "💰",
        epilogueQuote = "“Wisdom sees through the chaos to find true generational legacy.”",
        storyConclusion = "By decoding the old registry deed and connecting the clues from Dadaji and Adv. Mathur, you saved the ancestral Jaipur land from being sold for pennies! The family trust now funds Suresh Mama's legal startup and education for all cousins. Even Vikram Uncle is asking for your investment tips!",
        shareableTitle = "I found our family's ₹4.5 Cr hidden land deed and became the hero! 💰🏆"
    ),
    THE_BETRAYAL(
        title = "Ending 4: The Betrayal 😈",
        subtitle = "The Puppet of the Shadows",
        badge = "😈",
        epilogueQuote = "“You trusted the wrong person.”",
        storyConclusion = "You blindly passed the confidential documents and private chat logs to the Unknown Number, believing you were saving the family. Instead, the rivals used it to embarrass Dad at the society meeting. The group turned on you, leaving you wondering: What if you had stood with your own blood?",
        shareableTitle = "I trusted the Unknown Caller and got framed by the family! 😈😭"
    ),
    NEW_BEGINNING(
        title = "Ending 5: New Beginning 🌅",
        subtitle = "Healing the 30-Year Misunderstanding",
        badge = "🌅",
        epilogueQuote = "“Every family has a story. This one is just beginning.”",
        storyConclusion = "You didn't just suppress the drama; you addressed the root cause. You brought Dadi and Suresh Mama together, revealed the innocent 1994 mishap, and set healthy adult boundaries with love. You step out into your own life with peace, clarity, and genuine blessings.",
        shareableTitle = "I resolved a 30-year family cold war and started fresh! 🌅🕊️"
    )
}

data class EndingProgress(
    val unlockedEndings: Set<EndingType> = emptySet(),
    val latestEnding: EndingType? = null
)
