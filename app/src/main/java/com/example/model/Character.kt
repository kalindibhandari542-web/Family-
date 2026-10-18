package com.example.model

import androidx.compose.ui.graphics.Color

enum class CharacterId(
    val displayName: String,
    val relationshipTitle: String,
    val bio: String,
    val emoji: String,
    val avatarColor: Color,
    val catchphrase: String
) {
    ROHAN(
        displayName = "Rohan",
        relationshipTitle = "Cousin • Meme Lord",
        bio = "Funny, sarcastic, sends memes at 3 AM. Knows everyone's internet history.",
        emoji = "😎",
        avatarColor = Color(0xFF1E88E5),
        catchphrase = "Bro, wait till you see this meme 😂"
    ),
    MOM(
        displayName = "Sunita (Mom)",
        relationshipTitle = "Mother • Chief Feeder",
        bio = "Loving, fiercely protective, intensely curious. Monitors your every meal.",
        emoji = "🧕",
        avatarColor = Color(0xFFD81B60),
        catchphrase = "Beta, खाना खाया? Have you eaten?"
    ),
    DAD(
        displayName = "Rajesh (Dad)",
        relationshipTitle = "Father • The Practical Boss",
        bio = "Strict, career-focused, obsessed with mutual funds and turning off fans.",
        emoji = "👨‍💼",
        avatarColor = Color(0xFF3949AB),
        catchphrase = "Save money, invest in FD, and switch off the geyser!"
    ),
    DADI(
        displayName = "Dadi",
        relationshipTitle = "Grandmother • Savage Matriarch",
        bio = "Traditional but surprisingly savage. Drops legendary roasts with a sweet smile.",
        emoji = "👵",
        avatarColor = Color(0xFF8E24AA),
        catchphrase = "In my time, children had respect and better hair!"
    ),
    DADAJI(
        displayName = "Dadaji",
        relationshipTitle = "Grandfather • Wise Chronicler",
        bio = "Old-school patriarch. Tells 45-minute stories about walking 12 km to school.",
        emoji = "👴",
        avatarColor = Color(0xFF5D4037),
        catchphrase = "Back in 1968, when a cup of tea was 10 paise..."
    ),
    CHACHA(
        displayName = "Mahesh Chacha",
        relationshipTitle = "Uncle • WhatsApp University Dean",
        bio = "Believes NASA proved NASA chants. Forwards unverified claims with 100% confidence.",
        emoji = "🧔",
        avatarColor = Color(0xFF00897B),
        catchphrase = "FORWARDED AS RECEIVED: Shocking truth!"
    ),
    CHACHI(
        displayName = "Pooja Chachi",
        relationshipTitle = "Aunt • Gossip Intelligence Agency",
        bio = "Knows about your cousin's wedding before the bride does. Feeds on family drama.",
        emoji = "💁‍♀️",
        avatarColor = Color(0xFFF4511E),
        catchphrase = "Did you hear what happened at the society club?!"
    ),
    MAMA(
        displayName = "Suresh Mama",
        relationshipTitle = "Uncle • Agent of Chaos",
        bio = "Lives in Pune. Shows up unexpectedly at family events and starts 3 arguments.",
        emoji = "🕺",
        avatarColor = Color(0xFFFB8C00),
        catchphrase = "Relax yaaro! Life is too short to follow rules."
    ),
    MAMI(
        displayName = "Rekha Mami",
        relationshipTitle = "Aunt • Passive-Aggressive Competitor",
        bio = "Extremely sweet voice, but her compliments come with subtle titanium daggers.",
        emoji = "💅",
        avatarColor = Color(0xFF00ACC1),
        catchphrase = "Oh your new flat is lovely! So compact and manageable."
    ),
    BUA(
        displayName = "Anita Bua",
        relationshipTitle = "Aunt • Emotional Siren",
        bio = "Gets offended in 0.4 seconds if not greeted first. Tears on demand.",
        emoji = "🥻",
        avatarColor = Color(0xFFE91E63),
        catchphrase = "No one in this family cares about my feelings anymore! 😭"
    ),
    FUFA(
        displayName = "Prakash Fufa",
        relationshipTitle = "Uncle • Unsolicited Consultant",
        bio = "Has an opinion on your career, your diet, and how you park your scooter.",
        emoji = "🧐",
        avatarColor = Color(0xFF43A047),
        catchphrase = "If you listened to my advice in 2018, you'd be a director now."
    ),
    NEHA(
        displayName = "Neha",
        relationshipTitle = "Older Cousin • Master Manipulator",
        bio = "Recently married. Diplomatic on group chats, ruthlessly strategic behind the scenes.",
        emoji = "👰",
        avatarColor = Color(0xFF7CB342),
        catchphrase = "I'm just saying this for everyone's happiness, obviously."
    ),
    ARJUN(
        displayName = "Arjun",
        relationshipTitle = "Cousin • Professional Gamer",
        bio = "Claims he is studying while grinding ranked Valorant. Clever when money is involved.",
        emoji = "🎮",
        avatarColor = Color(0xFF5E35B1),
        catchphrase = "Can someone lend me ₹500? Will return on Monday pakka."
    ),
    SIMRAN(
        displayName = "Simran",
        relationshipTitle = "Cousin • College Intellectual",
        bio = "Sarcastic, politically opinionated, secretly exhausted by family gatherings.",
        emoji = "🎧",
        avatarColor = Color(0xFF009688),
        catchphrase = "Please tell me this family is an elaborate social experiment."
    ),
    KABIR(
        displayName = "Kabir",
        relationshipTitle = "Cousin • Chief Prankster",
        bio = "Creates chaos for fun. Accidentally deleted Dad's tax folder and screenshots private chats.",
        emoji = "🤪",
        avatarColor = Color(0xFFE64A19),
        catchphrase = "Oops... nobody was supposed to see that! 💀"
    ),
    MEENA(
        displayName = "Meena Auntie",
        relationshipTitle = "Neighbourhood Auntie • Rishta Matchmaker",
        bio = "Carries biodatas in her purse. Looks at unmarried youth like an unsolved crime.",
        emoji = "💍",
        avatarColor = Color(0xFFC2185B),
        catchphrase = "Beta, your age is running away! शादी कब कर रहे हो?"
    ),
    VIKRAM(
        displayName = "Vikram Uncle",
        relationshipTitle = "Uncle • The Flexer",
        bio = "Extremely wealthy. Cannot speak without mentioning his son Ankit in Silicon Valley.",
        emoji = "🏆",
        avatarColor = Color(0xFF388E3C),
        catchphrase = "My son Ankit got a 40% hike at Google, by the way."
    ),
    AASHI(
        displayName = "Aashi",
        relationshipTitle = "Younger Cousin • Unfiltered Whistleblower",
        bio = "16 years old. Accidentally spills confidential tea in the main group chat.",
        emoji = "🫢",
        avatarColor = Color(0xFFFFB300),
        catchphrase = "Wait, wasn't that supposed to be a secret? Oops!"
    ),
    SHARMA_UNCLE(
        displayName = "Sharma Uncle",
        relationshipTitle = "Family Friend • The Omnipresent",
        bio = "Appears in every discussion, wedding, and doctor's waiting room. Knows all secrets.",
        emoji = "☕",
        avatarColor = Color(0xFF795548),
        catchphrase = "I told your father back in 2005, but nobody listens to Sharma."
    ),
    UNKNOWN_NUMBER(
        displayName = "Unknown Number",
        relationshipTitle = "+91 98765-XXXXX • The Informant",
        bio = "Mysterious caller sending encrypted whispers, stolen screenshots, and family truths.",
        emoji = "🕵️‍♂️",
        avatarColor = Color(0xFF263238),
        catchphrase = "Check the old almirah file before tonight. Trust no one."
    ),
    PLAYER(
        displayName = "You",
        relationshipTitle = "Family Member",
        bio = "Surviving the wildest Indian family WhatsApp group one text at a time.",
        emoji = "🙋‍♂️",
        avatarColor = Color(0xFF0F5A47),
        catchphrase = "Let me think before sending this..."
    );

    fun relationshipTier(score: Int): RelationshipStatus {
        return when {
            score <= 20 -> RelationshipStatus.ENEMY
            score <= 40 -> RelationshipStatus.UNFRIENDLY
            score <= 60 -> RelationshipStatus.NEUTRAL
            score <= 80 -> RelationshipStatus.FRIENDLY
            else -> RelationshipStatus.TRUSTED
        }
    }
}

enum class RelationshipStatus(val label: String, val badgeColor: Color) {
    ENEMY("Enemy 😠", Color(0xFFD32F2F)),
    UNFRIENDLY("Unfriendly 😒", Color(0xFFF57C00)),
    NEUTRAL("Neutral 😐", Color(0xFF757575)),
    FRIENDLY("Friendly 😊", Color(0xFF388E3C)),
    TRUSTED("Trusted ❤️", Color(0xFFC2185B))
}
