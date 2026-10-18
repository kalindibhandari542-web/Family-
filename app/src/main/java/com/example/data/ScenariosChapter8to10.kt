package com.example.data

import com.example.model.CharacterId
import com.example.model.ChatMessage
import com.example.model.ChatChoice
import com.example.model.ScenarioData
import com.example.model.EndingType
import com.example.model.SimulatedCall
import com.example.model.InAppNotification

object ScenariosChapter8to10 {
    fun getList(): List<ScenarioData> = listOf(
        // Chat 36: Viral Video
        ScenarioData(
            scenarioId = 36,
            chapter = 8,
            title = "Chat 36 — Viral Video",
            category = "CHAOTIC EVENTS",
            introDialogues = listOf(
                ChatMessage("c36_m1", CharacterId.ROHAN, "GUYS PRAKASH FUFA'S SANGEET BREAKDANCE HAS 1.8 MILLION VIEWS ON INSTA REELS 🔥🕺", timestamp = "11:00 AM"),
                ChatMessage("c36_m2", CharacterId.FUFA, "REMOVE IT IMMEDIATELY! My government department colleagues are sharing it in the officers' WhatsApp group! My dignity is ruined!! 😡", timestamp = "11:05 AM"),
                ChatMessage("c36_m3", CharacterId.KABIR, "Fufa ji, the comments are calling you 'India's Grooviest Uncle'! You have fan pages! 😂", timestamp = "11:08 AM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c36_opt1",
                    text = "“Fufa ji, even Bollywood celebrities commented saying your energy and joy brought a smile to millions! You brought true happiness to everyone!”",
                    consequenceBadge = "+20 Fufa Trust • Turns rage into pride",
                    deltaTrust = 14,
                    deltaReputation = 15,
                    deltaDrama = -10,
                    relationshipImpact = mapOf(CharacterId.FUFA to 22, CharacterId.BUA to 15, CharacterId.ROHAN to 8),
                    followupMessages = listOf(
                        ChatMessage("c36_r1", CharacterId.FUFA, "Wait... a Bollywood director liked it? Well... I was always musically inclined in college.", reactions = listOf("🕺", "✨")),
                        ChatMessage("c36_r2", CharacterId.BUA, "See Prakash, I always told you to dance more!")
                    )
                ),
                ChatChoice(
                    id = "c36_opt2",
                    text = "“Rohan, take it down right now out of respect for Fufa ji.”",
                    consequenceBadge = "+12 Fufa • Rohan pouts",
                    deltaTrust = 6,
                    deltaDrama = -6,
                    relationshipImpact = mapOf(CharacterId.FUFA to 14, CharacterId.ROHAN to -4),
                    followupMessages = listOf(
                        ChatMessage("c36_r3", CharacterId.ROHAN, "Archived the reel. Boring elders win again.")
                    )
                )
            )
        ),

        // Chat 37: Wrong Photo
        ScenarioData(
            scenarioId = 37,
            chapter = 8,
            title = "Chat 37 — Wrong Photo",
            category = "CHAOTIC EVENTS",
            introDialogues = listOf(
                ChatMessage("c37_m1", CharacterId.CHACHI, "Look what I found in the old photo album! @You at age 4 sitting in a steel tub of water eating a raw mango with no clothes on!! 😂👶", timestamp = "3:30 PM"),
                ChatMessage("c37_m2", CharacterId.CHACHI, "[Image: Cute chubby toddler in galvanized tub]", timestamp = "3:31 PM"),
                ChatMessage("c37_m3", CharacterId.ROHAN, "SAVING THIS FOREVER HAHAHAHA 💀📸", timestamp = "3:33 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c37_opt1",
                    text = "“Hey, at least I had my priorities straight since age 4: pure Alphonso mangoes and zero stress! 🥭😂”",
                    consequenceBadge = "+15 Family Vibe • Self-deprecating Champ",
                    deltaTrust = 10,
                    deltaReputation = 12,
                    deltaDrama = -5,
                    relationshipImpact = mapOf(CharacterId.DADI to 14, CharacterId.MOM to 16, CharacterId.ROHAN to 12),
                    followupMessages = listOf(
                        ChatMessage("c37_r1", CharacterId.MOM, "My cutest little prince! Time flies so fast.", reactions = listOf("❤️", "🥺")),
                        ChatMessage("c37_r2", CharacterId.DADI, "You finished two whole mangoes by yourself that day.")
                    )
                ),
                ChatChoice(
                    id = "c37_opt2",
                    text = "“Chachi ji, please delete this from the group! This is a complete violation of privacy!”",
                    consequenceBadge = "Awkward tension • Drama +8",
                    deltaDrama = 8,
                    relationshipImpact = mapOf(CharacterId.CHACHI to -10),
                    followupMessages = listOf(
                        ChatMessage("c37_r3", CharacterId.CHACHI, "Arey, it was just childhood love... no sense of humor left.")
                    )
                )
            )
        ),

        // Chat 38: Family Business
        ScenarioData(
            scenarioId = 38,
            chapter = 8,
            title = "Chat 38 — Family Business",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c38_m1", CharacterId.MAMA, "I have a proposal. Instead of keeping the ancestral Jaipur property idle, let us start 'Parivaar Organic Spices & Pickles' using Dadi's authentic secret recipes! Export to USA and UK!", timestamp = "6:00 PM"),
                ChatMessage("c38_m2", CharacterId.DAD, "Suresh, business requires strict accounting, inventory control, and licenses. Not just enthusiasm.", timestamp = "6:05 PM"),
                ChatMessage("c38_m3", CharacterId.DADI, "My mango and lemon pickle recipe has won 3 district awards in 1982. It is worth more than gold.", timestamp = "6:10 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c38_opt1",
                    text = "“What if we combine Suresh Mama's supply chain knowledge with Dad's strict financial oversight, and register 'Parivaar Spices' as an official family LLP with equal equity for all branches?”",
                    consequenceBadge = "+20 Family Trust • Generational Venture",
                    deltaTrust = 18,
                    deltaReputation = 20,
                    deltaDrama = -10,
                    relationshipImpact = mapOf(CharacterId.MAMA to 20, CharacterId.DAD to 18, CharacterId.DADI to 18),
                    followupMessages = listOf(
                        ChatMessage("c38_r1", CharacterId.DAD, "Structured with professional accounting... I would be willing to review that pitch deck.", reactions = listOf("📈", "👍")),
                        ChatMessage("c38_r2", CharacterId.MAMA, "YES! Partnering with Rajesh bhai under formal rules! We will conquer the market!")
                    )
                ),
                ChatChoice(
                    id = "c38_opt2",
                    text = "“Doing business with relatives always destroys families. Better to avoid.”",
                    consequenceBadge = "Safe cynicism • Mama deflated",
                    deltaTrust = 2,
                    relationshipImpact = mapOf(CharacterId.MAMA to -12, CharacterId.DAD to 6),
                    followupMessages = listOf(
                        ChatMessage("c38_r3", CharacterId.MAMA, "Always so negative... nobody dreams big.")
                    )
                )
            )
        ),

        // Chat 39: Investment Argument
        ScenarioData(
            scenarioId = 39,
            chapter = 8,
            title = "Chat 39 — Investment Argument",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c39_m1", CharacterId.VIKRAM, "Listen to me Rajesh. I have an NRI real estate syndicate in Silicon Valley. Hand over the Jaipur land power of attorney to me. I will guarantee 18% annualized returns in US Dollars.", timestamp = "8:30 PM"),
                ChatMessage("c39_m2", CharacterId.DAD, "Our land has emotional soul Vikram. We are not treating Dadaji's legacy as a Wall Street hedge fund.", timestamp = "8:34 PM"),
                ChatMessage("c39_m3", CharacterId.VIKRAM, "Emotions don't pay college fees or retirement bills Rajesh! Ask the new generation!", timestamp = "8:36 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c39_opt1",
                    text = "“Vikram Uncle, Dad is right. A 100% self-owned family asset with steady rental income gives more true security than speculative cross-border funds. We control our own destiny.”",
                    consequenceBadge = "+25 Dad Approval • Family Sovereignty",
                    deltaTrust = 15,
                    deltaReputation = 20,
                    deltaDrama = -8,
                    relationshipImpact = mapOf(CharacterId.DAD to 22, CharacterId.DADAJI to 22, CharacterId.VIKRAM to -8),
                    followupMessages = listOf(
                        ChatMessage("c39_r1", CharacterId.DAD, "Spot on. Family soil is not for sale to foreign syndicates.", reactions = listOf("🇮🇳", "👏")),
                        ChatMessage("c39_r2", CharacterId.DADAJI, "Well said my child. Blood and soil remain.")
                    )
                ),
                ChatChoice(
                    id = "c39_opt2",
                    text = "“18% USD return is pretty high though... maybe we should look at the prospectus?”",
                    consequenceBadge = "+15 Vikram • Dad disappointed",
                    deltaTrust = 2,
                    relationshipImpact = mapOf(CharacterId.VIKRAM to 16, CharacterId.DAD to -12),
                    followupMessages = listOf(
                        ChatMessage("c39_r3", CharacterId.DAD, "Disappointing to see greed eclipse heritage.")
                    )
                )
            )
        ),

        // Chat 40: Unexpected Guest
        ScenarioData(
            scenarioId = 40,
            chapter = 8,
            title = "Chat 40 — Unexpected Guest",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c40_m1", CharacterId.MOM, "Rajesh ji, an elderly gentleman named Adv. Harish Mathur has arrived at our house in a black ambassador car with a sealed leather briefcase. He says he was summoned by Dadaji!", timestamp = "9:15 PM"),
                ChatMessage("c40_m2", CharacterId.CHACHA, "Mathur sahib?! But he was Dadaji's personal lawyer who retired 10 years ago! Why is he here?!", timestamp = "9:18 PM"),
                ChatMessage("c40_m3", CharacterId.DADAJI, "Let him in Sunita. It is time everyone heard the truth.", timestamp = "9:20 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c40_opt1",
                    text = "“Welcome Mathur Uncle! Let me serve you fresh ginger chai and warm snacks while everyone gathers respectfully in the living room 🙏”",
                    consequenceBadge = "+20 Reputation • Sanskaari Hospitality",
                    deltaTrust = 15,
                    deltaReputation = 22,
                    deltaDrama = -10,
                    relationshipImpact = mapOf(CharacterId.DADAJI to 22, CharacterId.MOM to 18, CharacterId.DAD to 16),
                    followupMessages = listOf(
                        ChatMessage("c40_r1", CharacterId.DADAJI, "This is the child I was telling you about, Mathur. Pure heart.", reactions = listOf("❤️", "☕")),
                        ChatMessage("c40_r2", CharacterId.MOM, "Always gracious to our guests.")
                    )
                ),
                ChatChoice(
                    id = "c40_opt2",
                    text = "“Advocate? Is someone getting sued?! What is going on?!”",
                    consequenceBadge = "Paranoia • Drama +12",
                    deltaDrama = 12,
                    relationshipImpact = mapOf(CharacterId.CHACHA to -5),
                    followupMessages = listOf(
                        ChatMessage("c40_r3", CharacterId.DAD, "Keep your composure! Elders are handling this.")
                    )
                )
            )
        ),

        // Chat 41: The Hidden Secret
        ScenarioData(
            scenarioId = 41,
            chapter = 9,
            title = "Chat 41 — The Hidden Secret",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c41_m1", CharacterId.UNKNOWN_NUMBER, "The time has come to reveal myself. I am Advocate Harish Mathur.", timestamp = "9:45 PM", isPrivateChat = true),
                ChatMessage("c41_m2", CharacterId.UNKNOWN_NUMBER, "Three months ago, your grandfather gave me strict instructions: 'Mathur, my family is fighting over property and prestige. Test my grandchildren from behind the curtain. Find who is greedy, who is hot-headed, and who possesses the wisdom to hold this family together.'", timestamp = "9:47 PM", isPrivateChat = true),
                ChatMessage("c41_m3", CharacterId.UNKNOWN_NUMBER, "You have navigated every crisis with a remarkable heart. But the final trial is now before you.", timestamp = "9:48 PM", isPrivateChat = true)
            ),
            choices = listOf(
                ChatChoice(
                    id = "c41_opt1",
                    text = "“Mathur Uncle... Dadaji did all this to test us? Thank God! I was only trying to protect our family from breaking apart.”",
                    consequenceBadge = "🤫 Unlocks Secret #10: Unknown Identity! • 🏆 Master Detective",
                    deltaTrust = 20,
                    deltaReputation = 25,
                    unlocksSecretId = "sec_10",
                    followupMessages = listOf(
                        ChatMessage("c41_r1", CharacterId.UNKNOWN_NUMBER, "And you succeeded beyond Dadaji's expectations. Prepare for the family meeting.")
                    )
                ),
                ChatChoice(
                    id = "c41_opt2",
                    text = "“Testing your own family with secret texts? That's quite extreme Uncle!”",
                    consequenceBadge = "Bold Candor • Adv. Mathur smiles",
                    deltaReputation = 15,
                    unlocksSecretId = "sec_10",
                    followupMessages = listOf(
                        ChatMessage("c41_r2", CharacterId.UNKNOWN_NUMBER, "Desperate times require creative tests child. Now step into the living room.")
                    )
                )
            )
        ),

        // Chat 42: Family Meeting
        ScenarioData(
            scenarioId = 42,
            chapter = 9,
            title = "Chat 42 — Family Meeting",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c42_m1", CharacterId.DADAJI, "Listen everyone. The Jaipur highway land was never meant for one brother or one family. It was bought with my father's sweat in 1974.", timestamp = "10:15 PM"),
                ChatMessage("c42_m2", CharacterId.DAD, "Mahesh, Suresh... you both made secret deals behind my back because you thought I wouldn't understand your struggles.", timestamp = "10:20 PM"),
                ChatMessage("c42_m3", CharacterId.MAMA, "Rajesh bhai... I was ashamed of my failed business. I am so sorry 😭", timestamp = "10:23 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c42_opt1",
                    text = "“Dad, Dadaji... mistakes were made out of fear and insecurity, not malice. Today, nobody walks away alone. We stand as one Apna Parivaar.”",
                    consequenceBadge = "+25 Trust All Around • Healing Hearts",
                    deltaTrust = 22,
                    deltaReputation = 25,
                    deltaDrama = -18,
                    relationshipImpact = mapOf(CharacterId.DAD to 25, CharacterId.MAMA to 25, CharacterId.CHACHA to 25, CharacterId.DADAJI to 25),
                    followupMessages = listOf(
                        ChatMessage("c42_r1", CharacterId.DADI, "Tears of joy in my old eyes. This child is the real pillar of our house.", reactions = listOf("❤️", "🙏", "🥺")),
                        ChatMessage("c42_r2", CharacterId.CHACHA, "Forgive me Rajesh bhai... forgive me everyone.")
                    )
                ),
                ChatChoice(
                    id = "c42_opt2",
                    text = "“Mahesh Chacha and Suresh Mama should sign a written apology before anything else.”",
                    consequenceBadge = "Strict Accountability • Tension lingers",
                    deltaReputation = 10,
                    deltaDrama = 8,
                    relationshipImpact = mapOf(CharacterId.DAD to 12, CharacterId.CHACHA to -10, CharacterId.MAMA to -10),
                    followupMessages = listOf(
                        ChatMessage("c42_r3", CharacterId.CHACHA, "We will sign whatever is needed... we have suffered enough guilt.")
                    )
                )
            )
        ),

        // Chat 43: The Missing Document
        ScenarioData(
            scenarioId = 43,
            chapter = 9,
            title = "Chat 43 — The Missing Document",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c43_m1", CharacterId.DAD, "Wait. The original royal seal stamped certificate from 1974 is not inside the deed envelope! Who had access to the master locker?", timestamp = "10:40 PM"),
                ChatMessage("c43_m2", CharacterId.MAMA, "I... I deposited it with the moneylender as security for the ₹2.5 Lakhs loan! But I need ₹50,000 to retrieve it right now before midnight or he sells the collateral!", timestamp = "10:44 PM"),
                ChatMessage("c43_m3", CharacterId.DAD, "MIDNIGHT TODAY?! IT'S ALREADY 10:45 PM!!", timestamp = "10:45 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c43_opt1",
                    text = "“Don't panic! I have ₹10,000 in my digital savings, and Dadi's Horlicks stash has emergency funds! Let's wire the clearance money right now and retrieve our heritage seal!”",
                    consequenceBadge = "-₹5,000 • Saves the Family Heritage • Golden Hero",
                    deltaMoney = -5000,
                    deltaTrust = 25,
                    deltaReputation = 30,
                    deltaDrama = -20,
                    relationshipImpact = mapOf(CharacterId.DAD to 25, CharacterId.MAMA to 25, CharacterId.DADI to 25),
                    followupMessages = listOf(
                        ChatMessage("c43_r1", CharacterId.DAD, "Collateral cleared! Certificate retrieved! You saved our family's future child!", reactions = listOf("🏆", "🎉", "🙏")),
                        ChatMessage("c43_r2", CharacterId.MAMA, "I will repay every single rupee to you beta, I promise on my mother.")
                    )
                ),
                ChatChoice(
                    id = "c43_opt2",
                    text = "“Let Suresh Mama face the moneylender himself. He created this mess.”",
                    consequenceBadge = "Cold detachment • Drama spikes • War imminent",
                    deltaDrama = 25,
                    relationshipImpact = mapOf(CharacterId.MAMA to -25, CharacterId.DAD to -10),
                    followupMessages = listOf(
                        ChatMessage("c43_r3", CharacterId.MAMA, "I am ruined... completely ruined.")
                    )
                )
            )
        ),

        // Chat 44: The Confession
        ScenarioData(
            scenarioId = 44,
            chapter = 9,
            title = "Chat 44 — The Confession",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c44_m1", CharacterId.CHACHI, "I want to confess too... I was the one who forwarded Neha's sangeet lehenga photos to the society aunties. I was jealous that Neha's wedding looked so much grander than my daughter's... forgive me Neha.", timestamp = "11:05 PM"),
                ChatMessage("c44_m2", CharacterId.NEHA, "Chachi... you have no idea how much your words hurt me. But seeing the whole family vulnerable tonight... I forgive you.", timestamp = "11:10 PM"),
                ChatMessage("c44_m3", CharacterId.MOM, "No more secrets in Apna Parivaar. Only love and open hearts.", timestamp = "11:12 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c44_opt1",
                    text = "“Group hug emoji time! ❤️🤗 We survived the biggest storm this family has ever faced!”",
                    consequenceBadge = "+20 Emotional Resonance • Peace reigns",
                    deltaTrust = 18,
                    deltaDrama = -15,
                    relationshipImpact = mapOf(CharacterId.CHACHI to 20, CharacterId.NEHA to 20, CharacterId.MOM to 20),
                    followupMessages = listOf(
                        ChatMessage("c44_r1", CharacterId.NEHA, "Group hug! 🤗❤️", reactions = listOf("❤️", "🤗")),
                        ChatMessage("c44_r2", CharacterId.ROHAN, "Never thought I'd see Apna Parivaar wholesome for once 🥺")
                    )
                ),
                ChatChoice(
                    id = "c44_opt2",
                    text = "“Finally! The truth is out. Let's see if this honesty lasts more than a week.”",
                    consequenceBadge = "+10 Simran • Skeptic humor",
                    deltaTrust = 4,
                    relationshipImpact = mapOf(CharacterId.SIMRAN to 12),
                    followupMessages = listOf(
                        ChatMessage("c44_r3", CharacterId.SIMRAN, "Accurate prediction lmao 😭")
                    )
                )
            )
        ),

        // Chat 45: The Betrayal Attempt
        ScenarioData(
            scenarioId = 45,
            chapter = 9,
            title = "Chat 45 — The Betrayal Attempt",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c45_m1", CharacterId.UNKNOWN_NUMBER, "Incoming transmission from external broker: 'To the player @You: We will pay you ₹50,00,000 cash in private bank transfer tonight if you quietly delay Dadaji from signing the trust deed until our builder takes possession. Think about your own future.'", timestamp = "11:30 PM", isPrivateChat = true),
                ChatMessage("c45_m2", CharacterId.UNKNOWN_NUMBER, "This is the final test of integrity. What is your choice?", timestamp = "11:31 PM", isPrivateChat = true)
            ),
            choices = listOf(
                ChatChoice(
                    id = "c45_opt1",
                    text = "“Tell your corrupt builder to get lost! My family's pride and grandfather's honor are not for sale for fifty crore, let alone fifty lakhs! 🇮🇳”",
                    consequenceBadge = "+30 Trust • Unbreakable Moral Core",
                    deltaTrust = 25,
                    deltaReputation = 30,
                    deltaDrama = -10,
                    relationshipImpact = mapOf(CharacterId.DADAJI to 30, CharacterId.DAD to 30),
                    followupMessages = listOf(
                        ChatMessage("c45_r1", CharacterId.UNKNOWN_NUMBER, "Spoken like a true son/daughter of this soil. Dadaji will be immensely proud.")
                    )
                ),
                ChatChoice(
                    id = "c45_opt2",
                    text = "“Fifty lakhs...? That is a lot of money... let me consider it.”",
                    consequenceBadge = "Corrupt Temptation • Sets up Betrayal Ending",
                    deltaTrust = -25,
                    deltaDrama = 20,
                    relationshipImpact = mapOf(CharacterId.DAD to -20, CharacterId.DADAJI to -25),
                    followupMessages = listOf(
                        ChatMessage("c45_r2", CharacterId.UNKNOWN_NUMBER, "Disappointing. Greed always leaves a trail of ashes.")
                    )
                )
            )
        ),

        // Chat 46: The Evidence
        ScenarioData(
            scenarioId = 46,
            chapter = 10,
            title = "Chat 46 — The Evidence",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c46_m1", CharacterId.DAD, "Adv. Mathur has placed the official valuation report and the Parivaar Trust bylaws on the table. The land is officially surveyed at ₹4.5 Crore.", timestamp = "11:45 PM"),
                ChatMessage("c46_m2", CharacterId.DADAJI, "@You, as the grandchild who navigated every crisis and held our hands through the storm, you have the decisive vote.", timestamp = "11:48 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c46_opt1",
                    text = "“I am ready Dadaji. I have reviewed every fact and listened to every family member.”",
                    consequenceBadge = "+15 Reputation • Decisive Moment",
                    deltaTrust = 10,
                    deltaReputation = 15,
                    followupMessages = listOf(
                        ChatMessage("c46_r1", CharacterId.DADAJI, "Speak your mind my child. The family listens.")
                    )
                )
            )
        ),

        // Chat 47: Final Family Argument
        ScenarioData(
            scenarioId = 47,
            chapter = 10,
            title = "Chat 47 — Final Family Argument",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c47_m1", CharacterId.VIKRAM, "Whatever we decide, do it fast! My flight to San Francisco is tomorrow morning!", timestamp = "11:50 PM"),
                ChatMessage("c47_m2", CharacterId.MOM, "Beta @You, whatever you decide, we know your intentions are for everyone's happiness.", timestamp = "11:52 PM"),
                ChatMessage("c47_m3", CharacterId.DADI, "Follow your dharma, my child.", timestamp = "11:53 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c47_opt1",
                    text = "“Thank you Mom, Dadi, and Dad. We are ready for the finale.”",
                    consequenceBadge = "Final Threshold • All Eyes on You",
                    deltaTrust = 10,
                    followupMessages = listOf(
                        ChatMessage("c47_r1", CharacterId.DAD, "Make your choice.")
                    )
                )
            )
        ),

        // Chat 48: The Choice
        ScenarioData(
            scenarioId = 48,
            chapter = 10,
            title = "Chat 48 — The Choice",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c48_m1", CharacterId.UNKNOWN_NUMBER, "Adv. Mathur holds up the legal fountain pen. Five paths lie before you.", timestamp = "11:55 PM"),
                ChatMessage("c48_m2", CharacterId.DADAJI, "How shall the story of Apna Parivaar be written in the golden registry?", timestamp = "11:56 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c48_opt1",
                    text = "“We keep the family together through unity and forgiveness! The land becomes our shared legacy!”",
                    consequenceBadge = "Towards Ending 1 or 3",
                    deltaTrust = 20,
                    deltaReputation = 20,
                    followupMessages = listOf(
                        ChatMessage("c48_r1", CharacterId.DADAJI, "A noble heart!")
                    )
                ),
                ChatChoice(
                    id = "c48_opt2",
                    text = "“We need total independence. Cut the toxic ties and let everyone face their own consequences!”",
                    consequenceBadge = "Towards Ending 2 or 5",
                    deltaDrama = 20,
                    followupMessages = listOf(
                        ChatMessage("c48_r2", CharacterId.DAD, "A harsh path...")
                    )
                )
            )
        ),

        // Chat 49: Final Message
        ScenarioData(
            scenarioId = 49,
            chapter = 10,
            title = "Chat 49 — Final Message",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c49_m1", CharacterId.UNKNOWN_NUMBER, "Advocate Mathur signs the witness page. 'The ink is ready for the heir's decisive verdict.'", timestamp = "11:58 PM"),
                ChatMessage("c49_m2", CharacterId.ROHAN, "Bro... whatever happens, you were the MVP of this group chat.", timestamp = "11:59 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c49_opt1",
                    text = "“*Take the fountain pen and write the final decree*”",
                    consequenceBadge = "⚡ THE CLIMAX HAS ARRIVED",
                    deltaTrust = 5,
                    followupMessages = listOf(
                        ChatMessage("c49_r1", CharacterId.DADAJI, "It is done.")
                    )
                )
            )
        ),

        // Chat 50: Final Decision (Branches to all 5 endings!)
        ScenarioData(
            scenarioId = 50,
            chapter = 10,
            title = "Chat 50 — Final Decision",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c50_m1", CharacterId.DADAJI, "@You, give the final order. Apna Parivaar awaits your word.", timestamp = "12:00 Midnight"),
                ChatMessage("c50_m2", CharacterId.MOM, "We trust you completely, beta.", timestamp = "12:00 Midnight")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c50_ending_1",
                    text = "❤️ “Establish the Apna Parivaar Trust! Forgive all debts, keep everyone united under one roof!”",
                    consequenceBadge = "🏆 ENDING 1: Happy Family ❤️",
                    deltaTrust = 30,
                    deltaReputation = 30,
                    deltaDrama = -30,
                    setsEnding = EndingType.HAPPY_FAMILY
                ),
                ChatChoice(
                    id = "c50_ending_2",
                    text = "💥 “Expose every single secret, shame the deceitful uncles publicly, and shut down this toxic group forever!”",
                    consequenceBadge = "💥 ENDING 2: Family War",
                    deltaDrama = 50,
                    deltaTrust = -40,
                    setsEnding = EndingType.FAMILY_WAR
                ),
                ChatChoice(
                    id = "c50_ending_3",
                    text = "💰 “Launch the Parivaar Commercial Plaza & Spice Export venture! Monetize the ₹4.5 Crore asset for generational wealth!”",
                    consequenceBadge = "💰 ENDING 3: Secret Millionaire",
                    deltaMoney = 50000,
                    deltaReputation = 35,
                    setsEnding = EndingType.SECRET_MILLIONAIRE
                ),
                ChatChoice(
                    id = "c50_ending_4",
                    text = "😈 “Take the secret ₹50 Lakhs builder payout and sign the deed over to the shadowy consortium.”",
                    consequenceBadge = "😈 ENDING 4: The Betrayal",
                    deltaTrust = -50,
                    deltaDrama = 40,
                    setsEnding = EndingType.THE_BETRAYAL
                ),
                ChatChoice(
                    id = "c50_ending_5",
                    text = "🌅 “Resolve the 30-year feud between Dadi and Suresh Mama peacefully, set healthy adult boundaries, and step forward into a new chapter!”",
                    consequenceBadge = "🌅 ENDING 5: New Beginning",
                    deltaTrust = 25,
                    deltaDrama = -25,
                    deltaReputation = 25,
                    setsEnding = EndingType.NEW_BEGINNING
                )
            )
        )
    )
}
