package com.example.data

import com.example.model.CharacterId
import com.example.model.ChatMessage
import com.example.model.ChatChoice
import com.example.model.ScenarioData
import com.example.model.SimulatedCall
import com.example.model.InAppNotification
import com.example.model.PhoneApp

object ScenariosChapter1to4 {
    fun getList(): List<ScenarioData> = listOf(
        // Chat 01: Good Morning Spam
        ScenarioData(
            scenarioId = 1,
            chapter = 1,
            title = "Chat 01 — Good Morning Spam",
            category = "FUNNY / NORMAL",
            introDialogues = listOf(
                ChatMessage(
                    id = "c1_m1",
                    sender = CharacterId.CHACHA,
                    text = "🌹✨ SHUBH PRABHAT TO THE ENTIRE FAMILY! ✨🌹 May your day be blessed with pure happiness and positive vibrations! Forward this to 11 groups for good luck!",
                    timestamp = "6:30 AM",
                    isForwarded = true,
                    imageResName = "img_good_morning",
                    reactions = listOf("🙏", "🌸")
                ),
                ChatMessage(
                    id = "c1_m2",
                    sender = CharacterId.MOM,
                    text = "Pranam Mahesh bhai sahib 🙏. Beta @You wake up, it's 6:45! Nashta is ready on the table.",
                    timestamp = "6:35 AM"
                ),
                ChatMessage(
                    id = "c1_m3",
                    sender = CharacterId.DADI,
                    text = "Mahesh, instead of sending 50MB rose images every dawn, send your son to buy milk. The cat is waiting.",
                    timestamp = "6:42 AM",
                    reactions = listOf("😂", "🔥")
                ),
                ChatMessage(
                    id = "c1_m4",
                    sender = CharacterId.ROHAN,
                    text = "Dadi savage as always 💀. Good morning everyone!",
                    timestamp = "6:45 AM"
                )
            ),
            choices = listOf(
                ChatChoice(
                    id = "c1_opt1",
                    text = "“Good morning Mom, Chacha ji, Dadi! Have a blessed day 🙏”",
                    consequenceBadge = "+5 Trust • Sanskaari Choice",
                    deltaTrust = 5,
                    deltaReputation = 5,
                    relationshipImpact = mapOf(CharacterId.MOM to 5, CharacterId.CHACHA to 5, CharacterId.DADI to 2),
                    followupMessages = listOf(
                        ChatMessage("c1_r1", CharacterId.MOM, "God bless you beta! Such good sanskaars.", reactions = listOf("❤️")),
                        ChatMessage("c1_r2", CharacterId.CHACHA, "Beta always respects elders. Unlike today's youth.")
                    )
                ),
                ChatChoice(
                    id = "c1_opt2",
                    text = "“Dadi dropping fire before breakfast 😂 Mahesh Chacha please send less data heavy images!”",
                    consequenceBadge = "-5 Drama • +8 Rohan Vibe",
                    deltaDrama = -5,
                    deltaTrust = 2,
                    relationshipImpact = mapOf(CharacterId.DADI to 8, CharacterId.ROHAN to 10, CharacterId.CHACHA to -3),
                    followupMessages = listOf(
                        ChatMessage("c1_r3", CharacterId.DADI, "Truth hurts, my child! Haha.", reactions = listOf("😂")),
                        ChatMessage("c1_r4", CharacterId.CHACHA, "Hmph. Nobody values cultural warmth nowadays.")
                    )
                ),
                ChatChoice(
                    id = "c1_opt3",
                    text = "“*Leaves on read and goes back to sleep*”",
                    consequenceBadge = "-5 Trust • Mom gets worried",
                    deltaTrust = -5,
                    deltaDrama = 5,
                    relationshipImpact = mapOf(CharacterId.MOM to -5),
                    followupMessages = listOf(
                        ChatMessage("c1_r5", CharacterId.MOM, "Blue tick seen! Why are you not replying? Are you sick?!")
                    )
                )
            )
        ),

        // Chat 02: Food Fight
        ScenarioData(
            scenarioId = 2,
            chapter = 1,
            title = "Chat 02 — Food Fight",
            category = "FUNNY / NORMAL",
            introDialogues = listOf(
                ChatMessage("c2_m1", CharacterId.MOM, "Today evening what should I cook? Shahi Paneer or healthy Bhindi?", timestamp = "11:15 AM"),
                ChatMessage("c2_m2", CharacterId.DAD, "Make simple Moong Dal and Lauki. Easy for digestion and saves money.", timestamp = "11:18 AM"),
                ChatMessage("c2_m3", CharacterId.KABIR, "Lauki?! No way! Order double cheese pizza or I am protesting outside.", timestamp = "11:20 AM"),
                ChatMessage("c2_m4", CharacterId.ROHAN, "Biryani or we riot. What do you say @You?", timestamp = "11:22 AM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c2_opt1",
                    text = "“Mom, your Shahi Paneer is world-famous. Let's make that! ❤️”",
                    consequenceBadge = "+10 Mom Trust • Peaceful",
                    deltaTrust = 8,
                    deltaDrama = -5,
                    relationshipImpact = mapOf(CharacterId.MOM to 12, CharacterId.DAD to 2, CharacterId.KABIR to -2),
                    followupMessages = listOf(
                        ChatMessage("c2_r1", CharacterId.MOM, "My sweetest child! Adding extra malai just for you.", reactions = listOf("❤️"))
                    )
                ),
                ChatChoice(
                    id = "c2_opt2",
                    text = "“I'm ordering Swiggy Biryani for the cousins. My treat! 🍗”",
                    consequenceBadge = "-₹1,200 • +15 Cousin Rep • Dad frowns",
                    deltaMoney = -1200,
                    deltaDrama = 5,
                    relationshipImpact = mapOf(CharacterId.ROHAN to 12, CharacterId.KABIR to 15, CharacterId.DAD to -6),
                    followupMessages = listOf(
                        ChatMessage("c2_r2", CharacterId.DAD, "Money doesn't grow on trees! Cooking at home is always best."),
                        ChatMessage("c2_r3", CharacterId.KABIR, "BHAI YOU ARE THE GOAT! 🔥", reactions = listOf("🔥", "🙏"))
                    )
                ),
                ChatChoice(
                    id = "c2_opt3",
                    text = "“Dad is right, Lauki is good for health and budget.”",
                    consequenceBadge = "+10 Dad Approval • Cousins roasted",
                    deltaTrust = 5,
                    deltaReputation = 5,
                    relationshipImpact = mapOf(CharacterId.DAD to 10, CharacterId.KABIR to -8, CharacterId.ROHAN to -5),
                    followupMessages = listOf(
                        ChatMessage("c2_r4", CharacterId.DAD, "Finally, someone with practical common sense.", reactions = listOf("👍")),
                        ChatMessage("c2_r5", CharacterId.ROHAN, "Traitor! Pure betrayal.")
                    )
                )
            )
        ),

        // Chat 03: Birthday Reminder
        ScenarioData(
            scenarioId = 3,
            chapter = 1,
            title = "Chat 03 — Birthday Reminder",
            category = "FUNNY / NORMAL",
            introDialogues = listOf(
                ChatMessage("c3_m1", CharacterId.CHACHA, "Urgent: Someone said Dadi's birthday is on 22nd. But my calendar says today! Who was responsible for the cake?", timestamp = "2:30 PM"),
                ChatMessage("c3_m2", CharacterId.DADAJI, "In 1972 her birthday was on Ashtami. The modern Gregorian calendar is completely flawed.", timestamp = "2:35 PM"),
                ChatMessage("c3_m3", CharacterId.BUA, "Don't tell me everyone forgot Mataji's 75th milestone?! I knew this family has no emotions left! 😭", timestamp = "2:38 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c3_opt1",
                    text = "“Relax Bua ji! I already pre-ordered Dadi's favourite Kaju Katli and fruit cake! 🎂”",
                    consequenceBadge = "-₹800 • +15 Family Reputation • Hero!",
                    deltaMoney = -800,
                    deltaTrust = 10,
                    deltaDrama = -10,
                    deltaReputation = 15,
                    relationshipImpact = mapOf(CharacterId.DADI to 15, CharacterId.BUA to 10, CharacterId.CHACHA to 5),
                    followupMessages = listOf(
                        ChatMessage("c3_r1", CharacterId.DADI, "My darling child! You are the only smart one in this whole clan.", reactions = listOf("❤️", "👵")),
                        ChatMessage("c3_r2", CharacterId.BUA, "Bhagwan kare tumhari lambi umar ho beta!")
                    )
                ),
                ChatChoice(
                    id = "c3_opt2",
                    text = "“Wait, wasn't Kabir assigned to order the cake last week?”",
                    consequenceBadge = "Throws Kabir under bus • Drama +12",
                    deltaDrama = 12,
                    relationshipImpact = mapOf(CharacterId.KABIR to -15, CharacterId.MOM to -2),
                    followupMessages = listOf(
                        ChatMessage("c3_r3", CharacterId.KABIR, "Bro why are you snitching?! I was doing college assignments! 😭"),
                        ChatMessage("c3_r4", CharacterId.DAD, "Kabir! Get off video games and come to living room right now!")
                    )
                )
            )
        ),

        // Chat 04: Family Selfie
        ScenarioData(
            scenarioId = 4,
            chapter = 1,
            title = "Chat 04 — Family Selfie",
            category = "FUNNY / NORMAL",
            introDialogues = listOf(
                ChatMessage("c4_m1", CharacterId.CHACHI, "Look at this group photo from last Sunday's pooja! But why is Anita didi looking away? 🤔", timestamp = "4:15 PM"),
                ChatMessage("c4_m2", CharacterId.BUA, "Because Prakash was pushed to the extreme edge! People with bigger cars took front and center! 😤", timestamp = "4:20 PM"),
                ChatMessage("c4_m3", CharacterId.VIKRAM, "Front row is for senior elders Anita, nothing about cars. Don't create issues.", timestamp = "4:24 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c4_opt1",
                    text = "“Bua ji, honestly your saree was shining brightest in the whole photo! Pure grace ✨”",
                    consequenceBadge = "+12 Bua Trust • Drama defused",
                    deltaTrust = 6,
                    deltaDrama = -8,
                    relationshipImpact = mapOf(CharacterId.BUA to 15, CharacterId.FUFA to 8),
                    followupMessages = listOf(
                        ChatMessage("c4_r1", CharacterId.BUA, "Thank you beta. At least someone has eyes and respect.", reactions = listOf("🙏", "✨")),
                        ChatMessage("c4_r2", CharacterId.FUFA, "Very observant child. Good upbringing.")
                    )
                ),
                ChatChoice(
                    id = "c4_opt2",
                    text = "“Can we not turn a 2-second family photo into a diplomatic summit please? 🤦‍♂️”",
                    consequenceBadge = "+10 Simran & Rohan • Elders shocked",
                    deltaDrama = 10,
                    relationshipImpact = mapOf(CharacterId.SIMRAN to 10, CharacterId.ROHAN to 10, CharacterId.BUA to -10),
                    followupMessages = listOf(
                        ChatMessage("c4_r3", CharacterId.SIMRAN, "Lmao thank you for saying it 😭", reactions = listOf("💀")),
                        ChatMessage("c4_r4", CharacterId.BUA, "Nowadays children talk back like this?! Sunita, see your child!")
                    )
                )
            )
        ),

        // Chat 05: Missing Charger
        ScenarioData(
            scenarioId = 5,
            chapter = 1,
            title = "Chat 05 — Missing Charger",
            category = "FUNNY / NORMAL",
            introDialogues = listOf(
                ChatMessage("c5_m1", CharacterId.DAD, "WHO TOOK MY 65-WATT FAST CHARGER FROM MY STUDY TABLE?! My phone battery is at 4%! Urgent bank OTP is coming!", timestamp = "7:00 PM"),
                ChatMessage("c5_m2", CharacterId.KABIR, "I saw Arjun taking a black cable towards his room 5 minutes ago 👀", timestamp = "7:02 PM"),
                ChatMessage("c5_m3", CharacterId.ARJUN, "LIAR! I was on Discord! Kabir was the one searching Dad's drawer!", timestamp = "7:04 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c5_opt1",
                    text = "“Dad, don't worry, you can take my spare charger right away! Plug it in.”",
                    consequenceBadge = "+10 Dad Approval • Peace restored",
                    deltaTrust = 8,
                    deltaReputation = 8,
                    deltaDrama = -6,
                    relationshipImpact = mapOf(CharacterId.DAD to 14, CharacterId.MOM to 6),
                    followupMessages = listOf(
                        ChatMessage("c5_r1", CharacterId.DAD, "Good. At least one responsible soul in this house. Saved my OTP.", reactions = listOf("👍")),
                        ChatMessage("c5_r2", CharacterId.MOM, "God bless my child. Always so helpful.")
                    )
                ),
                ChatChoice(
                    id = "c5_opt2",
                    text = "“Whoever took it, confess now before Dad checks the hallway CCTV! 📹”",
                    consequenceBadge = "Kabir panics • Drama +8",
                    deltaDrama = 8,
                    relationshipImpact = mapOf(CharacterId.KABIR to -5, CharacterId.ARJUN to 8),
                    followupMessages = listOf(
                        ChatMessage("c5_r3", CharacterId.KABIR, "Wait... we have hallway CCTV?! 🏃‍♂️💨"),
                        ChatMessage("c5_r4", CharacterId.DAD, "Kabir! Return it right now!")
                    )
                )
            )
        ),

        // Chat 06: TV Remote War
        ScenarioData(
            scenarioId = 6,
            chapter = 2,
            title = "Chat 06 — TV Remote War",
            category = "FUNNY / NORMAL",
            introDialogues = listOf(
                ChatMessage("c6_m1", CharacterId.DAD, "India needs 24 runs in 18 balls. Nobody touch the TV set.", timestamp = "8:45 PM"),
                ChatMessage("c6_m2", CharacterId.MOM, "Rajesh ji, today is the maha-episode of Anupama! Vanraj is confessing in court! Cricket comes everyday!", timestamp = "8:47 PM"),
                ChatMessage("c6_m3", CharacterId.DADI, "Turn on devotional Bhajans or I will disconnect the main power fuse.", timestamp = "8:50 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c6_opt1",
                    text = "“Mom, let's stream Anupama on my tablet in the bedroom with high volume! Dad can watch cricket on TV.”",
                    consequenceBadge = "+10 Mom • +10 Dad • Master Mediator",
                    deltaTrust = 10,
                    deltaDrama = -10,
                    deltaReputation = 10,
                    relationshipImpact = mapOf(CharacterId.MOM to 12, CharacterId.DAD to 10, CharacterId.DADI to 5),
                    followupMessages = listOf(
                        ChatMessage("c6_r1", CharacterId.MOM, "Brilliant solution beta! Bringing dry fruits for you.", reactions = listOf("❤️")),
                        ChatMessage("c6_r2", CharacterId.DAD, "SIX! Kohli hit a six! Good thinking child.")
                    )
                ),
                ChatChoice(
                    id = "c6_opt2",
                    text = "“Dadi is the house queen. Dadi's Bhajans on TV, match on radio!”",
                    consequenceBadge = "+15 Dadi • Dad sighs",
                    deltaTrust = 6,
                    relationshipImpact = mapOf(CharacterId.DADI to 18, CharacterId.DAD to -4),
                    followupMessages = listOf(
                        ChatMessage("c6_r3", CharacterId.DADI, "Radhe Radhe! May God grant you wisdom and a government job.", reactions = listOf("🙏", "🌸"))
                    )
                )
            )
        ),

        // Chat 07: Grocery List
        ScenarioData(
            scenarioId = 7,
            chapter = 2,
            title = "Chat 07 — Grocery List",
            category = "FUNNY / NORMAL",
            introDialogues = listOf(
                ChatMessage("c7_m1", CharacterId.MOM, "@You when returning from office/market, buy: 2kg potatoes, 500g ginger, garam masala, and tell bhaiya to give free kadi patta and extra dhaniya! Don't pay extra!", timestamp = "5:10 PM"),
                ChatMessage("c7_m2", CharacterId.CHACHI, "Sunita didi, vegetable prices are touching the sky today! Did you check BigBasket discounts?", timestamp = "5:15 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c7_opt1",
                    text = "“Got everything Mom! Bargained hard and took extra green chillies and free coriander too! 🥦”",
                    consequenceBadge = "-₹350 • +12 Mom Trust • Certified Indian Shopper",
                    deltaMoney = -350,
                    deltaTrust = 8,
                    deltaReputation = 10,
                    relationshipImpact = mapOf(CharacterId.MOM to 15, CharacterId.CHACHI to 5),
                    followupMessages = listOf(
                        ChatMessage("c7_r1", CharacterId.MOM, "Shabash! Truly my child. You will manage home budget very well.", reactions = listOf("👏", "❤️"))
                    )
                ),
                ChatChoice(
                    id = "c7_opt2",
                    text = "“Ordered online on Zepto in 8 minutes flat. Done!”",
                    consequenceBadge = "-₹600 • Mom lectures about fresh mandi",
                    deltaMoney = -600,
                    deltaTrust = -2,
                    relationshipImpact = mapOf(CharacterId.MOM to -4, CharacterId.DAD to -5),
                    followupMessages = listOf(
                        ChatMessage("c7_r2", CharacterId.MOM, "Online vegetables are stored in cold storage for 6 months! Why waste delivery fee?")
                    )
                )
            )
        ),

        // Chat 08: Late Night Message
        ScenarioData(
            scenarioId = 8,
            chapter = 2,
            title = "Chat 08 — Late Night Message",
            category = "GOSSIP AND SECRETS",
            introDialogues = listOf(
                ChatMessage("c8_m1", CharacterId.KABIR, "SOS BRO PLEASE WAKE UP EMERGENCY EMERGENCY 🚨🚨🚨", timestamp = "2:14 AM"),
                ChatMessage("c8_m2", CharacterId.DAD, "Why is my phone buzzing at 2:15 AM?! Who is dying?! Kabir, why are you awake?!", timestamp = "2:18 AM"),
                ChatMessage("c8_m3", CharacterId.KABIR, "Nothing Uncle ji! Just accidentally typed while dreaming! Sry!", timestamp = "2:20 AM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c8_opt1",
                    text = "“*Open Private DM with Kabir to investigate*”",
                    consequenceBadge = "🤫 Unlocks Secret #01: Wiped Tax Files!",
                    deltaTrust = 5,
                    deltaReputation = 5,
                    relationshipImpact = mapOf(CharacterId.KABIR to 12),
                    unlocksSecretId = "sec_01",
                    triggersNotification = InAppNotification(
                        id = "notif_kabir_secret",
                        title = "Kabir (Direct Message)",
                        sender = CharacterId.KABIR,
                        body = "Bro I wiped Dad's desktop tax folder trying to install games! Save me!"
                    ),
                    followupMessages = listOf(
                        ChatMessage("c8_r1", CharacterId.KABIR, "Check your DM bro... please don't tell Dad 😭")
                    )
                ),
                ChatChoice(
                    id = "c8_opt2",
                    text = "“Go to sleep Kabir, stop gaming before Dad confiscates your router.”",
                    consequenceBadge = "+5 Dad Approval • No Secret",
                    deltaTrust = 4,
                    relationshipImpact = mapOf(CharacterId.DAD to 8, CharacterId.KABIR to -4),
                    followupMessages = listOf(
                        ChatMessage("c8_r2", CharacterId.DAD, "Sensible advice. Everyone switch off phones now.")
                    )
                )
            )
        ),

        // Chat 09: Family Meme
        ScenarioData(
            scenarioId = 9,
            chapter = 2,
            title = "Chat 09 — Family Meme",
            category = "FUNNY / NORMAL",
            introDialogues = listOf(
                ChatMessage("c9_m1", CharacterId.ROHAN, "When your relatives ask about your salary and marriage in the same breath: *insert cat screaming into void meme*", timestamp = "1:05 PM", reactions = listOf("😂", "💀")),
                ChatMessage("c9_m2", CharacterId.MEENA, "Is this meme targeted at me, Rohan? I ask because I care about your future! 😠", timestamp = "1:10 PM"),
                ChatMessage("c9_m3", CharacterId.BUA, "Such disrespect to elders. Today's youth think everything is a joke.", timestamp = "1:12 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c9_opt1",
                    text = "“Meena Auntie, Rohan was just sharing a general Instagram joke! We all love and respect your blessings 🙏”",
                    consequenceBadge = "+10 Auntie • -8 Drama • Smooth Talker",
                    deltaTrust = 8,
                    deltaDrama = -8,
                    relationshipImpact = mapOf(CharacterId.MEENA to 14, CharacterId.BUA to 8, CharacterId.ROHAN to 4),
                    followupMessages = listOf(
                        ChatMessage("c9_r1", CharacterId.MEENA, "See, @You has such polite manners. Rohan should learn.", reactions = listOf("🌸"))
                    )
                ),
                ChatChoice(
                    id = "c9_opt2",
                    text = "“Honestly Auntie, it IS a bit stressful when every gathering feels like a job interview 😅”",
                    consequenceBadge = "+15 Simran & Rohan • Drama +15",
                    deltaDrama = 15,
                    relationshipImpact = mapOf(CharacterId.SIMRAN to 16, CharacterId.ROHAN to 15, CharacterId.MEENA to -14),
                    followupMessages = listOf(
                        ChatMessage("c9_r2", CharacterId.SIMRAN, "SPEAK YOUR TRUTH BESTIE 👏🔥", reactions = listOf("🔥")),
                        ChatMessage("c9_r3", CharacterId.MEENA, "Fine! I won't bring any good marriage alliances for you both!")
                    )
                )
            )
        ),

        // Chat 10: Vacation Planning
        ScenarioData(
            scenarioId = 10,
            chapter = 2,
            title = "Chat 10 — Vacation Planning",
            category = "FUNNY / NORMAL",
            introDialogues = listOf(
                ChatMessage("c10_m1", CharacterId.MAMA, "Holiday season is coming! All 21 of us should go to Goa! Beaches, seafood, full party! 🏖️💃", timestamp = "4:30 PM"),
                ChatMessage("c10_m2", CharacterId.DAD, "Goa?! Waste of money and too commercial. We should go to Haridwar and Rishikesh for Ganga Aarti.", timestamp = "4:35 PM"),
                ChatMessage("c10_m3", CharacterId.DADI, "I get knee pain in cold hills. Wherever we go, there must be pure vegetarian South Indian food.", timestamp = "4:40 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c10_opt1",
                    text = "“How about Udaipur or Jaipur? Heritage palaces for Dadi & Dad, rooftop cafes for cousins, within budget! 🏰”",
                    consequenceBadge = "+15 Family Reputation • Master Diplomat",
                    deltaTrust = 10,
                    deltaReputation = 15,
                    deltaDrama = -8,
                    relationshipImpact = mapOf(CharacterId.DAD to 10, CharacterId.DADI to 12, CharacterId.MAMA to 8, CharacterId.ROHAN to 8),
                    followupMessages = listOf(
                        ChatMessage("c10_r1", CharacterId.DADI, "Superb idea! I have always wanted to see City Palace.", reactions = listOf("🙏", "❤️")),
                        ChatMessage("c10_r2", CharacterId.DAD, "Economical train tickets available. I approve.")
                    )
                ),
                ChatChoice(
                    id = "c10_opt2",
                    text = "“Goa sounds epic! Mama is right, life is short, let's live a little!”",
                    consequenceBadge = "+15 Mama Suresh • Dad scolds",
                    deltaTrust = 4,
                    relationshipImpact = mapOf(CharacterId.MAMA to 18, CharacterId.DAD to -8),
                    followupMessages = listOf(
                        ChatMessage("c10_r3", CharacterId.MAMA, "My favourite nephew/niece! Suresh Mama always rocks!", reactions = listOf("🕺", "🔥")),
                        ChatMessage("c10_r4", CharacterId.DAD, "You pay for your own tickets then!")
                    )
                )
            )
        ),

        // Chat 11: Cousin Fight
        ScenarioData(
            scenarioId = 11,
            chapter = 3,
            title = "Chat 11 — Cousin Fight",
            category = "RELATIONSHIP DRAMA",
            introDialogues = listOf(
                ChatMessage("c11_m1", CharacterId.NEHA, "Simran, did you return my designer peach lehenga with a dry-clean stain on the border?! It cost ₹45,000! 😡", timestamp = "12:10 PM"),
                ChatMessage("c11_m2", CharacterId.SIMRAN, "Excuse me Neha Di? That faint spot was ALREADY there when you wore it for your sangeet! Don't blame me!", timestamp = "12:15 PM"),
                ChatMessage("c11_m3", CharacterId.CHACHI, "Oho... sisters fighting over clothes. Pooja chachi is watching 👀", timestamp = "12:18 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c11_opt1",
                    text = "“Girls, don't worry! I know the best dry cleaner in Connaught Place, let me get it restored tomorrow. It will look brand new!”",
                    consequenceBadge = "-₹600 • Defused Cousin War • +15 Trust",
                    deltaMoney = -600,
                    deltaTrust = 12,
                    deltaDrama = -12,
                    relationshipImpact = mapOf(CharacterId.NEHA to 12, CharacterId.SIMRAN to 12),
                    followupMessages = listOf(
                        ChatMessage("c11_r1", CharacterId.NEHA, "You are so mature @You. Thank you ❤️", reactions = listOf("❤️")),
                        ChatMessage("c11_r2", CharacterId.SIMRAN, "Thanks @You... you saved my life from endless lectures.")
                    )
                ),
                ChatChoice(
                    id = "c11_opt2",
                    text = "“Neha Di is right Simran, when you borrow an expensive outfit you should take extra care.”",
                    consequenceBadge = "+12 Neha • -12 Simran",
                    deltaDrama = 6,
                    relationshipImpact = mapOf(CharacterId.NEHA to 14, CharacterId.SIMRAN to -14),
                    followupMessages = listOf(
                        ChatMessage("c11_r3", CharacterId.SIMRAN, "Unbelievable. Siding with the married cousin. Noted.")
                    )
                )
            )
        ),

        // Chat 12: Secret Crush
        ScenarioData(
            scenarioId = 12,
            chapter = 3,
            title = "Chat 12 — Secret Crush",
            category = "GOSSIP AND SECRETS",
            introDialogues = listOf(
                ChatMessage("c12_m1", CharacterId.AASHI, "Di, the guy from the cafe who gave you his phone number on the tissue paper just posted on Insta!! 😍", timestamp = "6:40 PM"),
                ChatMessage("c12_m2", CharacterId.AASHI, "🚫 Aashi deleted this message", timestamp = "6:41 PM", isDeleted = true),
                ChatMessage("c12_m3", CharacterId.BUA, "WHAT TISSUE PAPER?! WHO GAVE A NUMBER?! Aashi Sunita, explain this right now!!", timestamp = "6:43 PM"),
                ChatMessage("c12_m4", CharacterId.MOM, "Aashi beta, who were you talking to?!", timestamp = "6:45 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c12_opt1",
                    text = "“Bua ji, relax! Aashi was talking about a scene from a Korean drama we were discussing earlier! 😂”",
                    consequenceBadge = "+15 Aashi Trust • Saved from Scandal",
                    deltaTrust = 8,
                    deltaDrama = -10,
                    relationshipImpact = mapOf(CharacterId.AASHI to 18, CharacterId.BUA to 4),
                    followupMessages = listOf(
                        ChatMessage("c12_r1", CharacterId.AASHI, "YES exactly Bua ji! Korean drama on Netflix! Haha phew!", reactions = listOf("🙏", "❤️")),
                        ChatMessage("c12_r2", CharacterId.BUA, "These foreign serials are rotting your brains.")
                    )
                ),
                ChatChoice(
                    id = "c12_opt2",
                    text = "“Aashi was with Simran at the cafe yesterday, ask them!”",
                    consequenceBadge = "Exposes Simran • Drama +15",
                    deltaDrama = 15,
                    relationshipImpact = mapOf(CharacterId.SIMRAN to -15, CharacterId.AASHI to -12, CharacterId.CHACHI to 10),
                    followupMessages = listOf(
                        ChatMessage("c12_r3", CharacterId.SIMRAN, "Why do you open your mouth when nobody asked you?! 😡"),
                        ChatMessage("c12_r4", CharacterId.CHACHI, "Aha! I knew something fishy was going on!")
                    )
                )
            )
        ),

        // Chat 13: Marriage Question
        ScenarioData(
            scenarioId = 13,
            chapter = 3,
            title = "Chat 13 — Marriage Question",
            category = "RELATIONSHIP DRAMA",
            introDialogues = listOf(
                ChatMessage("c13_m1", CharacterId.MEENA, "Beta @You, I found a boy/girl from good family in Indore. Father has cloth wholesale business. Should I share the horoscope with Sunita? Beta, शादी कब कर रहे हो?", timestamp = "3:30 PM"),
                ChatMessage("c13_m2", CharacterId.MOM, "Send send Meena ji! Let us see. Age is ticking.", timestamp = "3:32 PM"),
                ChatMessage("c13_m3", CharacterId.DAD, "First make sure career is settled. Marriage is a major expense.", timestamp = "3:35 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c13_opt1",
                    text = "“Auntie ji, my boss just told me I'm in line for a promotion! Let me focus on building my savings first so Dad doesn't have to spend a rupee 🙏”",
                    consequenceBadge = "+15 Dad Reputation • Auntie silenced",
                    deltaTrust = 10,
                    deltaReputation = 15,
                    deltaDrama = -5,
                    relationshipImpact = mapOf(CharacterId.DAD to 16, CharacterId.MOM to 8, CharacterId.MEENA to 4),
                    followupMessages = listOf(
                        ChatMessage("c13_r1", CharacterId.DAD, "Pride of our family. That's the mindset of a winner.", reactions = listOf("👍", "🌟")),
                        ChatMessage("c13_r2", CharacterId.MEENA, "Wah, very ambitious. But keep bio-data ready.")
                    )
                ),
                ChatChoice(
                    id = "c13_opt2",
                    text = "“Dadi hasn't even found a match for Kabir and Arjun yet, why target me? 😂”",
                    consequenceBadge = "+10 Dadi laughs • Cousins groan",
                    deltaTrust = 4,
                    relationshipImpact = mapOf(CharacterId.DADI to 12, CharacterId.KABIR to -6),
                    followupMessages = listOf(
                        ChatMessage("c13_r3", CharacterId.DADI, "Kabir will get married to his PlayStation.", reactions = listOf("😂", "🎮")),
                        ChatMessage("c13_r4", CharacterId.KABIR, "Dadi please stop roasting me in public 😭")
                    )
                )
            )
        ),

        // Chat 14: Comparison
        ScenarioData(
            scenarioId = 14,
            chapter = 3,
            title = "Chat 14 — Comparison",
            category = "RELATIONSHIP DRAMA",
            introDialogues = listOf(
                ChatMessage("c14_m1", CharacterId.VIKRAM, "Sharing Ankit's new photos from San Francisco! Company gave him a 45% bonus and brand new Tesla! How is work going for @You?", timestamp = "7:15 PM"),
                ChatMessage("c14_m2", CharacterId.FUFA, "US companies treat talented people with real respect. In India salaries are so stagnant.", timestamp = "7:20 PM"),
                ChatMessage("c14_m3", CharacterId.DAD, "Hard work in India also brings stability. Rajesh family believes in self-reliance.", timestamp = "7:25 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c14_opt1",
                    text = "“Congratulations to Ankit bhaiya! But honestly, living close to my parents and eating Mom's hot rotis every day is a bonus money can't buy ❤️”",
                    consequenceBadge = "+20 Mom & Dad Trust • Cultural Victory",
                    deltaTrust = 15,
                    deltaReputation = 18,
                    deltaDrama = -8,
                    relationshipImpact = mapOf(CharacterId.MOM to 20, CharacterId.DAD to 18, CharacterId.VIKRAM to -2),
                    followupMessages = listOf(
                        ChatMessage("c14_r1", CharacterId.MOM, "Aww my sweet baccha! Made my eyes moist.", reactions = listOf("❤️", "🥺")),
                        ChatMessage("c14_r2", CharacterId.DADI, "Sanskaar wins over Dollars any day.")
                    )
                ),
                ChatChoice(
                    id = "c14_opt2",
                    text = "“*Drop Secret clue about Ankit's burnout* Actually Vikram Uncle, Ankit bhaiya told me last week he's super stressed working 85-hour weeks.”",
                    isSecretUnlock = true,
                    requiredSecretId = "sec_06",
                    consequenceBadge = "🔓 Secret #06 Clue • Vikram Uncle stunned",
                    deltaReputation = 10,
                    deltaDrama = 12,
                    relationshipImpact = mapOf(CharacterId.VIKRAM to -10, CharacterId.DAD to 10),
                    unlocksSecretId = "sec_06",
                    followupMessages = listOf(
                        ChatMessage("c14_r3", CharacterId.VIKRAM, "What?! Ankit never tells me this... I will call him right now.")
                    )
                )
            )
        ),

        // Chat 15: Career Pressure
        ScenarioData(
            scenarioId = 15,
            chapter = 3,
            title = "Chat 15 — Career Pressure",
            category = "RELATIONSHIP DRAMA",
            introDialogues = listOf(
                ChatMessage("c15_m1", CharacterId.FUFA, "I am reviewing civil services exam forms. If @You prepares for 2 years with disciplined 14 hours study, Government job is guaranteed.", timestamp = "9:10 AM"),
                ChatMessage("c15_m2", CharacterId.DAD, "Prakash is right. Private companies can lay off anytime. Government job gives pension and security.", timestamp = "9:15 AM"),
                ChatMessage("c15_m3", CharacterId.SIMRAN, "Why does everyone want us to study until 30 for exams with 0.01% selection rate? 🙄", timestamp = "9:18 AM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c15_opt1",
                    text = "“Thank you Fufa ji, but I have a concrete 3-year growth roadmap in my industry. I'm hitting all my milestones!”",
                    consequenceBadge = "+12 Reputation • Confident & Respectful",
                    deltaReputation = 12,
                    deltaTrust = 8,
                    deltaDrama = -5,
                    relationshipImpact = mapOf(CharacterId.DAD to 10, CharacterId.FUFA to 6),
                    followupMessages = listOf(
                        ChatMessage("c15_r1", CharacterId.DAD, "Good to see you have a solid plan. Keep working hard.", reactions = listOf("👍"))
                    )
                ),
                ChatChoice(
                    id = "c15_opt2",
                    text = "“Fufa ji, with due respect, if civil services was so easy, why didn't your son clear it? 🤐”",
                    consequenceBadge = "Savage Roast • Drama +20 • Fufa outraged",
                    deltaDrama = 20,
                    relationshipImpact = mapOf(CharacterId.FUFA to -25, CharacterId.ROHAN to 15, CharacterId.SIMRAN to 18),
                    followupMessages = listOf(
                        ChatMessage("c15_r2", CharacterId.FUFA, "DISRESPECTFUL! Sunita, your child has crossed all limits! I am muting this group!"),
                        ChatMessage("c15_r3", CharacterId.ROHAN, "HOLY MOLY CALL THE FIRE BRIGADE 🚒🔥", reactions = listOf("💀", "🔥"))
                    )
                )
            )
        ),

        // Chat 16: Exam Results
        ScenarioData(
            scenarioId = 16,
            chapter = 4,
            title = "Chat 16 — Exam Results",
            category = "RELATIONSHIP DRAMA",
            introDialogues = listOf(
                ChatMessage("c16_m1", CharacterId.CHACHI, "Arjun's semester results came out today! Mahesh said everyone in college got above 80%! Arjun where is your scorecard? 📜", timestamp = "11:00 AM"),
                ChatMessage("c16_m2", CharacterId.ARJUN, "Uh... website server is down, error 502...", timestamp = "11:02 AM"),
                ChatMessage("c16_m3", CharacterId.ARJUN, "🚫 Arjun deleted this message", timestamp = "11:03 AM", isDeleted = true)
            ),
            choices = listOf(
                ChatChoice(
                    id = "c16_opt1",
                    text = "“Chachi ji, university portal really was crashed earlier today. Let him check peacefully in the evening.”",
                    consequenceBadge = "+15 Arjun Trust • Protects cousin",
                    deltaTrust = 8,
                    deltaDrama = -6,
                    relationshipImpact = mapOf(CharacterId.ARJUN to 18, CharacterId.CHACHI to 2),
                    followupMessages = listOf(
                        ChatMessage("c16_r1", CharacterId.ARJUN, "Bro thank you so much! I got 42 in Maths, I need to apply for re-eval today 😭🙏", reactions = listOf("🙏", "❤️"))
                    )
                ),
                ChatChoice(
                    id = "c16_opt2",
                    text = "“Server is fine, my friends checked theirs at 9 AM Arjun 👀”",
                    consequenceBadge = "Busts Arjun • Drama +12",
                    deltaDrama = 12,
                    relationshipImpact = mapOf(CharacterId.ARJUN to -18, CharacterId.CHACHA to 8),
                    followupMessages = listOf(
                        ChatMessage("c16_r2", CharacterId.CHACHA, "Arjun! Open the laptop right now!")
                    )
                )
            )
        ),

        // Chat 17: Salary Question
        ScenarioData(
            scenarioId = 17,
            chapter = 4,
            title = "Chat 17 — Salary Question",
            category = "RELATIONSHIP DRAMA",
            introDialogues = listOf(
                ChatMessage("c17_m1", CharacterId.SHARMA_UNCLE, "Namaste Rajesh ji and all! Was meeting my CA today. Beta @You, what CTC package did your company give in this new appraisal cycle? In-hand kitna aata hai?", timestamp = "6:00 PM"),
                ChatMessage("c17_m2", CharacterId.CHACHI, "Yes yes, tell us! Even we want to know!", timestamp = "6:02 PM"),
                ChatMessage("c17_m3", CharacterId.DAD, "Rajesh family does not discuss finances publicly, Sharma ji.", timestamp = "6:05 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c17_opt1",
                    text = "“Sharma Uncle, as Dad said, God gives enough for dal-roti, savings, and helping elders. The blessings matter more than numbers 🙏”",
                    consequenceBadge = "+15 Dad • +10 Dadi • Perfect Sanskaari Reply",
                    deltaTrust = 10,
                    deltaReputation = 15,
                    deltaDrama = -8,
                    relationshipImpact = mapOf(CharacterId.DAD to 16, CharacterId.DADI to 14, CharacterId.SHARMA_UNCLE to 8),
                    followupMessages = listOf(
                        ChatMessage("c17_r1", CharacterId.DAD, "Well said. That is the dignity of our family.", reactions = listOf("👏", "👍")),
                        ChatMessage("c17_r2", CharacterId.SHARMA_UNCLE, "Bahut sanskari baccha hai Rajesh bhai.")
                    )
                ),
                ChatChoice(
                    id = "c17_opt2",
                    text = "“Enough to pay income tax on time and buy my own gadgets Uncle ji 😉”",
                    consequenceBadge = "+10 Rohan • Sassy Deflection",
                    deltaReputation = 8,
                    relationshipImpact = mapOf(CharacterId.ROHAN to 10, CharacterId.CHACHI to -4),
                    followupMessages = listOf(
                        ChatMessage("c17_r3", CharacterId.ROHAN, "Boss move haha 🔥", reactions = listOf("🔥"))
                    )
                )
            )
        ),

        // Chat 18: New Relationship
        ScenarioData(
            scenarioId = 18,
            chapter = 4,
            title = "Chat 18 — New Relationship",
            category = "GOSSIP AND SECRETS",
            introDialogues = listOf(
                ChatMessage("c18_m1", CharacterId.AASHI, "Look at this aesthetic cafe picture Simran di posted! Who is that holding the second coffee cup with matching bracelet? 👀☕", timestamp = "4:50 PM"),
                ChatMessage("c18_m2", CharacterId.BUA, "Second cup?! Simran, you said you were at the college library studying Constitutional Law!!", timestamp = "4:55 PM"),
                ChatMessage("c18_m3", CharacterId.SIMRAN, "Aashi delete your comment right now!! It was just a classmate group project!", timestamp = "4:58 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c18_opt1",
                    text = "“Bua ji, that's her college study partner Aryan. They're working on a university documentary project together. I know him, very decent guy!”",
                    consequenceBadge = "🤫 Unlocks Secret #05 • +18 Simran Trust",
                    deltaTrust = 10,
                    deltaDrama = -6,
                    relationshipImpact = mapOf(CharacterId.SIMRAN to 20, CharacterId.BUA to 8),
                    unlocksSecretId = "sec_05",
                    followupMessages = listOf(
                        ChatMessage("c18_r1", CharacterId.SIMRAN, "Thank you @You... I owe you big time for this.", reactions = listOf("❤️", "🥺")),
                        ChatMessage("c18_r2", CharacterId.BUA, "Hmm. If @You knows him, then alright. But studies first!")
                    )
                ),
                ChatChoice(
                    id = "c18_opt2",
                    text = "“Ask Simran who paid for the ₹450 artisanal hazelnut cappuccino ☕😂”",
                    consequenceBadge = "Stirs the Pot • Drama +14",
                    deltaDrama = 14,
                    relationshipImpact = mapOf(CharacterId.SIMRAN to -15, CharacterId.BUA to 8, CharacterId.CHACHI to 10),
                    followupMessages = listOf(
                        ChatMessage("c18_r3", CharacterId.SIMRAN, "I hate you @You!"),
                        ChatMessage("c18_r4", CharacterId.BUA, "FOUR HUNDRED AND FIFTY RUPEES FOR COFFEE?!")
                    )
                )
            )
        ),

        // Chat 19: Wedding Proposal
        ScenarioData(
            scenarioId = 19,
            chapter = 4,
            title = "Chat 19 — Wedding Proposal",
            category = "RELATIONSHIP DRAMA",
            introDialogues = listOf(
                ChatMessage("c19_m1", CharacterId.MEENA, "Grand rishta received for Kabir! Girl's family has 3 jewelry showrooms in Chandni Chowk! Here is the biodata!", timestamp = "7:40 PM"),
                ChatMessage("c19_m2", CharacterId.KABIR, "AUNTIE I AM 21 YEARS OLD! I haven't even figured out how to file my ITR! Please spare me!", timestamp = "7:42 PM"),
                ChatMessage("c19_m3", CharacterId.DADI, "Jewelry showrooms? Tell them to send 2 gold bangles for inspection first.", timestamp = "7:45 PM", reactions = listOf("😂", "👵"))
            ),
            choices = listOf(
                ChatChoice(
                    id = "c19_opt1",
                    text = "“Dadi's inspection protocol is mandatory! 😂 But Auntie, let Kabir finish his degree first!”",
                    consequenceBadge = "+12 Kabir • +10 Dadi • Hilarious save",
                    deltaTrust = 8,
                    deltaDrama = -5,
                    relationshipImpact = mapOf(CharacterId.KABIR to 15, CharacterId.DADI to 12, CharacterId.MEENA to 4),
                    followupMessages = listOf(
                        ChatMessage("c19_r1", CharacterId.KABIR, "Bro thank you! Dadi savage as always haha", reactions = listOf("🙏", "❤️"))
                    )
                ),
                ChatChoice(
                    id = "c19_opt2",
                    text = "“Kabir, jewelry showroom means free gold for the whole parivaar! Say yes bro! 💍”",
                    consequenceBadge = "+8 Family laughs • Kabir sweats",
                    deltaDrama = 5,
                    relationshipImpact = mapOf(CharacterId.ROHAN to 10, CharacterId.KABIR to -4),
                    followupMessages = listOf(
                        ChatMessage("c19_r2", CharacterId.KABIR, "Bro you are selling me for gold bangles?! 😭")
                    )
                )
            )
        ),

        // Chat 20: Jealous Cousin
        ScenarioData(
            scenarioId = 20,
            chapter = 4,
            title = "Chat 20 — Jealous Cousin",
            category = "RELATIONSHIP DRAMA",
            introDialogues = listOf(
                ChatMessage("c20_m1", CharacterId.MAMI, "Sunita didi, I saw @You bought a brand new premium laptop and smartwatch! In our times, children saved money for marriage instead of showing off gadgets.", timestamp = "8:10 PM"),
                ChatMessage("c20_m2", CharacterId.MOM, "My child works day and night Rekha. It's for office work and coding.", timestamp = "8:14 PM"),
                ChatMessage("c20_m3", CharacterId.ROHAN, "Mami, even your son plays candy crush on iPad all day 🙃", timestamp = "8:16 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c20_opt1",
                    text = "“Rekha Mami, you're so right about saving! In fact, this laptop actually helped me do freelance projects that added ₹25,000 to my savings account! Always learning from your wisdom 🙏”",
                    consequenceBadge = "+15 Reputation • Ultimate Polite Defusal",
                    deltaReputation = 15,
                    deltaTrust = 10,
                    deltaDrama = -8,
                    relationshipImpact = mapOf(CharacterId.MAMI to 10, CharacterId.MOM to 15, CharacterId.DAD to 12),
                    followupMessages = listOf(
                        ChatMessage("c20_r1", CharacterId.MAMI, "Oh... freelance earnings. Well, good for you beta.", reactions = listOf("👍")),
                        ChatMessage("c20_r2", CharacterId.MOM, "So proud of my hardworking child!", reactions = listOf("❤️"))
                    )
                ),
                ChatChoice(
                    id = "c20_opt2",
                    text = "“Mami, my money, my hard work. I don't need anyone's audit on my gadgets.”",
                    consequenceBadge = "Savage confrontation • Drama +18",
                    deltaDrama = 18,
                    relationshipImpact = mapOf(CharacterId.MAMI to -20, CharacterId.ROHAN to 14, CharacterId.MOM to -6),
                    followupMessages = listOf(
                        ChatMessage("c20_r3", CharacterId.MAMI, "Sunita, your child's ego has reached the clouds. I am shocked!"),
                        ChatMessage("c20_r4", CharacterId.MOM, "Beta, talk with more respect to elders please!")
                    )
                )
            )
        )
    )
}
