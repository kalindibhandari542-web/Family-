package com.example.data

import com.example.model.CharacterId
import com.example.model.ChatMessage
import com.example.model.ChatChoice
import com.example.model.ScenarioData
import com.example.model.SimulatedCall
import com.example.model.InAppNotification

object ScenariosChapter5to7 {
    fun getList(): List<ScenarioData> = listOf(
        // Chat 21: Screenshot Accident
        ScenarioData(
            scenarioId = 21,
            chapter = 5,
            title = "Chat 21 — Screenshot Accident",
            category = "GOSSIP AND SECRETS",
            introDialogues = listOf(
                ChatMessage("c21_m1", CharacterId.KABIR, "LOOK WHAT POOJA CHACHI SAID ABOUT NEHA'S IN-LAWS 😱😱😱", timestamp = "3:15 PM"),
                ChatMessage("c21_m2", CharacterId.KABIR, "[Image Screenshot: Chachi writing 'Neha's in-laws are so greedy asking for silver gifts']", timestamp = "3:16 PM"),
                ChatMessage("c21_m3", CharacterId.KABIR, "WAIT WRONG CHAT WRONG CHAT DELETE DELETE 🛑", timestamp = "3:17 PM"),
                ChatMessage("c21_m4", CharacterId.NEHA, "EXCUSE ME?! POOJA CHACHI WHAT IS THIS?! 😡🔥", timestamp = "3:18 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c21_opt1",
                    text = "“*Immediately call Kabir to delete for everyone and calm Neha down privately*”",
                    consequenceBadge = "🤫 Unlocks Secret #02: Neha's Exit • +15 Trust",
                    deltaTrust = 12,
                    deltaDrama = -8,
                    relationshipImpact = mapOf(CharacterId.NEHA to 15, CharacterId.KABIR to 12, CharacterId.CHACHI to 10),
                    unlocksSecretId = "sec_02",
                    followupMessages = listOf(
                        ChatMessage("c21_r1", CharacterId.NEHA, "Thank you for explaining @You. But I am keeping my distance from Chachi."),
                        ChatMessage("c21_r2", CharacterId.CHACHI, "Beta @You, you saved my dignity today. Thank you.")
                    )
                ),
                ChatChoice(
                    id = "c21_opt2",
                    text = "“🍿 *Sits back with popcorn* Family drama is peaking today!”",
                    consequenceBadge = "+15 Rohan • Group on Fire 🔥 • Drama +20",
                    deltaDrama = 20,
                    relationshipImpact = mapOf(CharacterId.ROHAN to 18, CharacterId.NEHA to -12, CharacterId.CHACHI to -15),
                    followupMessages = listOf(
                        ChatMessage("c21_r3", CharacterId.ROHAN, "Best episode of Apna Parivaar yet 😂💀", reactions = listOf("🍿", "💀")),
                        ChatMessage("c21_r4", CharacterId.MOM, "Stop making fun! Everyone put phones down!")
                    )
                )
            )
        ),

        // Chat 22: Wrong Group
        ScenarioData(
            scenarioId = 22,
            chapter = 5,
            title = "Chat 22 — Wrong Group",
            category = "GOSSIP AND SECRETS",
            introDialogues = listOf(
                ChatMessage("c22_m1", CharacterId.MAMA, "Bhai Sharma ji, I mortgaged the ancestral registry papers for ₹2.5 Lakhs loan. Don't tell Rajesh bhai or Dadi! Meeting you at station.", timestamp = "6:30 PM"),
                ChatMessage("c22_m2", CharacterId.DAD, "Suresh. Which ancestral papers did you mortgage for ₹2.5 Lakhs?! ANSWER ME RIGHT NOW.", timestamp = "6:35 PM"),
                ChatMessage("c22_m3", CharacterId.MAMA, "Oh no... wrong group... Rajesh bhai listen, it was an autocorrect joke...", timestamp = "6:37 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c22_opt1",
                    text = "“Dad, Suresh Mama, please don't shout in the group. Let's do a conference call right now to understand what happened.”",
                    consequenceBadge = "🤫 Unlocks Secret #04: Mama's Debt • Diplomat",
                    deltaTrust = 10,
                    deltaDrama = -5,
                    relationshipImpact = mapOf(CharacterId.DAD to 10, CharacterId.MAMA to 14),
                    unlocksSecretId = "sec_04",
                    triggersCall = SimulatedCall(
                        character = CharacterId.MAMA,
                        isIncoming = true,
                        durationText = "1:15",
                        monologueScript = "Beta @You, save me! My cloud kitchen in Pune had an electrical short circuit. I took ₹2.5 Lakhs loan to pay staff. I didn't steal anything! Please tell Rajesh bhai!"
                    ),
                    followupMessages = listOf(
                        ChatMessage("c22_r1", CharacterId.MAMA, "Thank you for hearing me out beta... I was desperate.")
                    )
                ),
                ChatChoice(
                    id = "c22_opt2",
                    text = "“Mama, Dad has every right to be furious if ancestral property is involved!”",
                    consequenceBadge = "+15 Dad • Mama feels isolated",
                    deltaTrust = 5,
                    deltaDrama = 12,
                    relationshipImpact = mapOf(CharacterId.DAD to 16, CharacterId.MAMA to -18),
                    followupMessages = listOf(
                        ChatMessage("c22_r2", CharacterId.DAD, "Correct! Responsibility and honesty come first!")
                    )
                )
            )
        ),

        // Chat 23: Secret Wedding Plan
        ScenarioData(
            scenarioId = 23,
            chapter = 5,
            title = "Chat 23 — Secret Wedding Plan",
            category = "GOSSIP AND SECRETS",
            introDialogues = listOf(
                ChatMessage("c23_m1", CharacterId.NEHA, "Sharing the royal wedding invitation proof! Look at the gold border and palace motif ✨", timestamp = "1:00 PM", imageResName = "img_wedding_card"),
                ChatMessage("c23_m2", CharacterId.MAMI, "Very royal card Neha... but is this Udaipur palace booked with Rajesh jiju's savings or did in-laws contribute half?", timestamp = "1:05 PM"),
                ChatMessage("c23_m3", CharacterId.BUA, "And why was Anita Bua's name printed below Sharma uncle's name on page 2?! 😤", timestamp = "1:08 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c23_opt1",
                    text = "“Mami and Bua ji, this is just a preliminary printing draft! Neha specifically asked printer to put Bua ji at the top with golden foil! And all expenses are shared transparently ❤️”",
                    consequenceBadge = "+18 Family Peace • Neha's eternal gratitude",
                    deltaTrust = 12,
                    deltaReputation = 15,
                    deltaDrama = -14,
                    relationshipImpact = mapOf(CharacterId.NEHA to 20, CharacterId.BUA to 12, CharacterId.MAMI to 6),
                    followupMessages = listOf(
                        ChatMessage("c23_r1", CharacterId.NEHA, "You are an angel @You! Truly my favourite cousin.", reactions = listOf("❤️", "✨")),
                        ChatMessage("c23_r2", CharacterId.BUA, "Good. Proper protocol must be respected.")
                    )
                ),
                ChatChoice(
                    id = "c23_opt2",
                    text = "“Honestly, spending ₹25 Lakhs on a 2-day wedding palace is crazy in this economy.”",
                    consequenceBadge = "+15 Dad • Neha gets angry",
                    deltaTrust = 5,
                    relationshipImpact = mapOf(CharacterId.DAD to 14, CharacterId.NEHA to -16),
                    followupMessages = listOf(
                        ChatMessage("c23_r3", CharacterId.NEHA, "It's once in a lifetime! Not everyone wants a boring temple wedding!"),
                        ChatMessage("c23_r4", CharacterId.DAD, "The child is right. Simplicity is true wealth.")
                    )
                )
            )
        ),

        // Chat 24: Hidden Debt
        ScenarioData(
            scenarioId = 24,
            chapter = 5,
            title = "Chat 24 — Hidden Debt",
            category = "GOSSIP AND SECRETS",
            introDialogues = listOf(
                ChatMessage("c24_m1", CharacterId.MAMA, "Beta @You, can you lend Mama ₹3,000 for just 5 days? I need to clear the courier and supplier bill.", timestamp = "8:20 PM", isPrivateChat = true),
                ChatMessage("c24_m2", CharacterId.MAMA, "Don't tell your Dad, I will return with 10% bonus.", timestamp = "8:21 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c24_opt1",
                    text = "“Mama, I can send you ₹3,000 to help you through this, but on one condition: you have to be honest with Dad this weekend. We are family, we will help you fix the business together.”",
                    consequenceBadge = "-₹3,000 • +20 Mama Trust • True Family Hero",
                    deltaMoney = -3000,
                    deltaTrust = 15,
                    deltaDrama = -5,
                    relationshipImpact = mapOf(CharacterId.MAMA to 22, CharacterId.DAD to 8),
                    followupMessages = listOf(
                        ChatMessage("c24_r1", CharacterId.MAMA, "You have a heart of pure gold baccha... I will never forget this.", reactions = listOf("🙏", "🥺"))
                    )
                ),
                ChatChoice(
                    id = "c24_opt2",
                    text = "“Mama, I don't have liquid cash right now, invested in fixed deposit. Sorry!”",
                    consequenceBadge = "Saves money • Mama disappointed",
                    deltaTrust = -2,
                    relationshipImpact = mapOf(CharacterId.MAMA to -8),
                    followupMessages = listOf(
                        ChatMessage("c24_r2", CharacterId.MAMA, "Koi baat nahi beta... I will ask someone else.")
                    )
                )
            )
        ),

        // Chat 25: Missing Money
        ScenarioData(
            scenarioId = 25,
            chapter = 5,
            title = "Chat 25 — Missing Money",
            category = "CHAOTIC EVENTS",
            introDialogues = listOf(
                ChatMessage("c25_m1", CharacterId.DAD, "The ₹15,000 temple shagun envelope kept in the mandir drawer is MISSING! Who went near the mandir after lunch?!", timestamp = "4:00 PM"),
                ChatMessage("c25_m2", CharacterId.CHACHI, "I saw Arjun counting cash near the sofa... just saying 👀", timestamp = "4:03 PM"),
                ChatMessage("c25_m3", CharacterId.ARJUN, "THAT WAS MY OWN BIRTHDAY MONEY FROM NANI! Stop accusing me!", timestamp = "4:05 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c25_opt1",
                    text = "“Wait! Dad, check inside Dadi's Ramayana book! She always places the shagun envelope inside page 108 for blessings!”",
                    consequenceBadge = "+20 Family Reputation • Detective genius!",
                    deltaTrust = 15,
                    deltaReputation = 20,
                    deltaDrama = -15,
                    relationshipImpact = mapOf(CharacterId.DAD to 18, CharacterId.DADI to 20, CharacterId.ARJUN to 15),
                    followupMessages = listOf(
                        ChatMessage("c25_r1", CharacterId.DADI, "YES! Here it is! Between Sundarkand pages! What a brilliant memory this child has!", reactions = listOf("🙏", "❤️", "🌟")),
                        ChatMessage("c25_r2", CharacterId.DAD, "Crisis averted. Good job child.")
                    )
                ),
                ChatChoice(
                    id = "c25_opt2",
                    text = "“Whoever took it should silently put it back before we check everyone's pockets!”",
                    consequenceBadge = "Paranoia rises • Drama +15",
                    deltaDrama = 15,
                    relationshipImpact = mapOf(CharacterId.ARJUN to -10, CharacterId.KABIR to -10),
                    followupMessages = listOf(
                        ChatMessage("c25_r3", CharacterId.KABIR, "Why are all the cousins treated like suspects in this house?!")
                    )
                )
            )
        ),

        // Chat 26: Old Family Argument
        ScenarioData(
            scenarioId = 26,
            chapter = 6,
            title = "Chat 26 — Old Family Argument",
            category = "GOSSIP AND SECRETS",
            introDialogues = listOf(
                ChatMessage("c26_m1", CharacterId.DADI, "Voice message received from Dadi", timestamp = "11:20 AM", isVoiceNote = true, voiceDuration = "0:32", voiceTranscript = "Listen carefully children. 30 years ago on Diwali 1994, Sunita didn't insult Anita on purpose. Sunita's eyesight was poor and she mistook the rock salt jar for sugar while rolling the besan laddoos! Stop holding grudges for three decades!"),
                ChatMessage("c26_m2", CharacterId.MOM, "Mataji... you remembered? 🥺 I cried for 3 days in 1994 because Anita thought I insulted her in-laws.", timestamp = "11:25 AM"),
                ChatMessage("c26_m3", CharacterId.BUA, "Is this true Sunita...? You never told me it was salt by mistake...", timestamp = "11:28 AM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c26_opt1",
                    text = "“Mom, Bua ji, let's make fresh besan laddoos together this Diwali! 30 years of love is bigger than one pinch of salt ❤️”",
                    consequenceBadge = "🤫 Unlocks Secret #08 • Tears of joy • Drama -20",
                    deltaTrust = 20,
                    deltaDrama = -20,
                    deltaReputation = 15,
                    relationshipImpact = mapOf(CharacterId.MOM to 22, CharacterId.BUA to 22, CharacterId.DADI to 18),
                    unlocksSecretId = "sec_08",
                    followupMessages = listOf(
                        ChatMessage("c26_r1", CharacterId.BUA, "Sunita bhabhi... forgive my stubborn anger all these years.", reactions = listOf("🥺", "❤️", "🙏")),
                        ChatMessage("c26_r2", CharacterId.MOM, "Anita... you are my own sister. All forgotten!")
                    )
                ),
                ChatChoice(
                    id = "c26_opt2",
                    text = "“Dadi dropping historical truth bombs like a CBI investigator 😂”",
                    consequenceBadge = "+10 Rohan • Comic relief",
                    deltaTrust = 5,
                    relationshipImpact = mapOf(CharacterId.ROHAN to 12, CharacterId.DADI to 10),
                    followupMessages = listOf(
                        ChatMessage("c26_r3", CharacterId.DADI, "Old people don't forget, we just wait for the right moment.")
                    )
                )
            )
        ),

        // Chat 27: Secret Property
        ScenarioData(
            scenarioId = 27,
            chapter = 6,
            title = "Chat 27 — Secret Property",
            category = "MAJOR STORY EVENTS",
            introDialogues = listOf(
                ChatMessage("c27_m1", CharacterId.DADAJI, "Cleaning old trunk today. Found 1974 Registry Deed for 2 acres near Jaipur Highway. Does anyone remember what happened to that land?", timestamp = "3:40 PM", imageResName = "img_secret_deed"),
                ChatMessage("c27_m2", CharacterId.CHACHA, "Uh... pitaji, that barren land was sold for ₹40,000 in 1989! No need to dig up useless old paperwork now!", timestamp = "3:45 PM"),
                ChatMessage("c27_m3", CharacterId.DAD, "Wait Mahesh. It was never sold. The registry is still under Dadaji's name. Why are you sweating?", timestamp = "3:48 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c27_opt1",
                    text = "“Dadaji, let me take that registry document to a verified land revenue lawyer to check the current government portal records.”",
                    consequenceBadge = "🤫 Unlocks Secret #03: Jaipur Plot • Chacha nervous",
                    deltaTrust = 10,
                    deltaReputation = 15,
                    relationshipImpact = mapOf(CharacterId.DAD to 15, CharacterId.DADAJI to 18, CharacterId.CHACHA to -10),
                    unlocksSecretId = "sec_03",
                    followupMessages = listOf(
                        ChatMessage("c27_r1", CharacterId.DADAJI, "Smart thinking child. Take high resolution photos of the stamps.", reactions = listOf("📜", "👍")),
                        ChatMessage("c27_r2", CharacterId.CHACHA, "There is no need for outside lawyers in family matters...")
                    )
                ),
                ChatChoice(
                    id = "c27_opt2",
                    text = "“If Mahesh Chacha says it was sold in 1989, maybe we shouldn't waste time on old papers.”",
                    consequenceBadge = "+10 Chacha • Misses clue",
                    deltaTrust = 2,
                    relationshipImpact = mapOf(CharacterId.CHACHA to 14, CharacterId.DAD to -6),
                    followupMessages = listOf(
                        ChatMessage("c27_r3", CharacterId.CHACHA, "See! The child understands practical reality.")
                    )
                )
            )
        ),

        // Chat 28: Unknown Number
        ScenarioData(
            scenarioId = 28,
            chapter = 6,
            title = "Chat 28 — Unknown Number",
            category = "GOSSIP AND SECRETS",
            introDialogues = listOf(
                ChatMessage("c28_m1", CharacterId.UNKNOWN_NUMBER, "Greetings. Do not block this number. I have been watching Apna Parivaar.", timestamp = "9:05 PM", isPrivateChat = true),
                ChatMessage("c28_m2", CharacterId.UNKNOWN_NUMBER, "Mahesh Chacha is meeting builder Singhania tonight at Royal Orchid hotel to sign an unauthorized power of attorney for the Jaipur highway plot. It is worth ₹4.5 Crore.", timestamp = "9:07 PM", isPrivateChat = true),
                ChatMessage("c28_m3", CharacterId.UNKNOWN_NUMBER, "Will you confront him, or let your family lose its rightful future?", timestamp = "9:08 PM", isPrivateChat = true)
            ),
            choices = listOf(
                ChatChoice(
                    id = "c28_opt1",
                    text = "“Who are you?! Why are you sending me this instead of telling Rajesh Dad directly?”",
                    consequenceBadge = "Sharp Inquiry • Unknown respects you",
                    deltaTrust = 8,
                    deltaReputation = 10,
                    triggersNotification = InAppNotification(
                        id = "notif_unk_reply",
                        title = "Unknown Number",
                        sender = CharacterId.UNKNOWN_NUMBER,
                        body = "“Because your father is too emotional to act strategically. You have the calm head.”"
                    ),
                    followupMessages = listOf(
                        ChatMessage("c28_r1", CharacterId.UNKNOWN_NUMBER, "Rajesh will confront Mahesh in anger and destroy the evidence. Check Mahesh's car dashboard for the draft agreement.")
                    )
                ),
                ChatChoice(
                    id = "c28_opt2",
                    text = "“I am blocking you right now. Stop spying on my family!”",
                    consequenceBadge = "Blocks Unknown • Misses Intel",
                    deltaTrust = 2,
                    deltaDrama = 5,
                    relationshipImpact = mapOf(CharacterId.UNKNOWN_NUMBER to -15),
                    followupMessages = listOf(
                        ChatMessage("c28_r2", CharacterId.UNKNOWN_NUMBER, "You can block the number, but you cannot block the truth.")
                    )
                )
            )
        ),

        // Chat 29: Fake Forward
        ScenarioData(
            scenarioId = 29,
            chapter = 6,
            title = "Chat 29 — Fake Forward",
            category = "FUNNY / NORMAL",
            introDialogues = listOf(
                ChatMessage("c29_m1", CharacterId.CHACHA, "FORWARDED: UNESCO and WHO have confirmed that drinking raw neem leaves boiled with 4 cloves at 4:32 AM destroys all bacteria and amplifies mobile 5G signals into healing aura! Forward to 20 people!", timestamp = "7:15 AM", isForwarded = true),
                ChatMessage("c29_m2", CharacterId.SIMRAN, "Chacha ji... WHO never said this. This is 100% WhatsApp University fake news 🤦‍♀️", timestamp = "7:20 AM"),
                ChatMessage("c29_m3", CharacterId.CHACHA, "Simran, don't lecture your uncle! My college batchmate forwarded this from Ministry!", timestamp = "7:24 AM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c29_opt1",
                    text = "“Chacha ji, neem and cloves are great Ayurvedic remedies! But the 5G and UNESCO part was added by prankster bots online to get clicks. Here is the official PIB Fact Check link confirming it! 🙏”",
                    consequenceBadge = "🤫 Unlocks Secret #09 • Respectful Fact Check",
                    deltaTrust = 10,
                    deltaReputation = 12,
                    deltaDrama = -6,
                    relationshipImpact = mapOf(CharacterId.CHACHA to 10, CharacterId.SIMRAN to 12, CharacterId.DAD to 8),
                    unlocksSecretId = "sec_09",
                    followupMessages = listOf(
                        ChatMessage("c29_r1", CharacterId.CHACHA, "Oh... PIB official link. Okay, good that you checked. These internet miscreants fool anyone.", reactions = listOf("👍")),
                        ChatMessage("c29_r2", CharacterId.SIMRAN, "How do you always handle Chacha so diplomatically?! 😭")
                    )
                ),
                ChatChoice(
                    id = "c29_opt2",
                    text = "“Instructions unclear Chacha ji, drank neem juice and now my phone is downloading 6G! 😂”",
                    consequenceBadge = "+12 Rohan • Family laughs",
                    deltaDrama = -4,
                    relationshipImpact = mapOf(CharacterId.ROHAN to 15, CharacterId.KABIR to 12),
                    followupMessages = listOf(
                        ChatMessage("c29_r3", CharacterId.ROHAN, "BROOO 💀😭😂", reactions = listOf("💀", "😂"))
                    )
                )
            )
        ),

        // Chat 30: Secret Relationship
        ScenarioData(
            scenarioId = 30,
            chapter = 6,
            title = "Chat 30 — Secret Relationship",
            category = "GOSSIP AND SECRETS",
            introDialogues = listOf(
                ChatMessage("c30_m1", CharacterId.SIMRAN, "Aryan (the filmmaker) wants to come over and touch Dadaji and Dadi's feet properly. He says hiding behind cafe cups feels disrespectful to our family.", timestamp = "5:15 PM", isPrivateChat = true),
                ChatMessage("c30_m2", CharacterId.SIMRAN, "Should I introduce him during Sunday chai, or will Anita Bua cause World War 3?", timestamp = "5:16 PM", isPrivateChat = true)
            ),
            choices = listOf(
                ChatChoice(
                    id = "c30_opt1",
                    text = "“Tell him to come at 4 PM when Dadaji is having ginger tea! Dadaji loves polite youngsters who know history. I'll be right beside you to support!”",
                    consequenceBadge = "+20 Simran Trust • Best Cousin Ever",
                    deltaTrust = 15,
                    deltaReputation = 10,
                    relationshipImpact = mapOf(CharacterId.SIMRAN to 25, CharacterId.DADAJI to 10),
                    followupMessages = listOf(
                        ChatMessage("c30_r1", CharacterId.SIMRAN, "You are literally the best human being in this entire family tree ❤️🥺", reactions = listOf("❤️", "🙏"))
                    )
                ),
                ChatChoice(
                    id = "c30_opt2",
                    text = "“Wait until Neha's wedding functions are over Simran, right now everyone's stress levels are at 100%.”",
                    consequenceBadge = "Safe & Prudent • Defers Drama",
                    deltaTrust = 6,
                    deltaDrama = -5,
                    relationshipImpact = mapOf(CharacterId.SIMRAN to 8),
                    followupMessages = listOf(
                        ChatMessage("c30_r2", CharacterId.SIMRAN, "Fair point. We will wait for the right moment.")
                    )
                )
            )
        ),

        // Chat 31: Wedding Disaster
        ScenarioData(
            scenarioId = 31,
            chapter = 7,
            title = "Chat 31 — Wedding Disaster",
            category = "CHAOTIC EVENTS",
            introDialogues = listOf(
                ChatMessage("c31_m1", CharacterId.NEHA, "EMERGENCY! The Udaipur heritage resort double-booked our block! 12 guest rooms have been cancelled 3 days before the Sangeet!! My in-laws will cancel the wedding!! 😭😭😭", timestamp = "10:00 AM"),
                ChatMessage("c31_m2", CharacterId.DAD, "HOW CAN THEY CANCEL AFTER TAKING ADVANCE DEPOSIT?! I am filing a police complaint!", timestamp = "10:05 AM"),
                ChatMessage("c31_m3", CharacterId.MAMA, "Wait, my Pune college senior owns a heritage boutique Haveli 500 meters from that resort!", timestamp = "10:08 AM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c31_opt1",
                    text = "“Mama, call your senior immediately! Let's book the private Haveli for all the cousins and youngsters so the elders get luxury suites in the main hotel! Best of both worlds!”",
                    consequenceBadge = "+25 Neha • +20 Mama • Crisis Solved!",
                    deltaTrust = 18,
                    deltaReputation = 20,
                    deltaDrama = -15,
                    relationshipImpact = mapOf(CharacterId.NEHA to 25, CharacterId.MAMA to 22, CharacterId.DAD to 15),
                    followupMessages = listOf(
                        ChatMessage("c31_r1", CharacterId.NEHA, "HAVELI HAS A ROOFTOP POOL?! You guys saved my wedding! 😭❤️", reactions = listOf("❤️", "🎉")),
                        ChatMessage("c31_r2", CharacterId.MAMA, "Suresh Mama always comes through in style!")
                    )
                ),
                ChatChoice(
                    id = "c31_opt2",
                    text = "“Let Dad call the resort General Manager with Consumer Court legal notice.”",
                    consequenceBadge = "+10 Dad • Stress stays high",
                    deltaTrust = 5,
                    relationshipImpact = mapOf(CharacterId.DAD to 12, CharacterId.NEHA to -4),
                    followupMessages = listOf(
                        ChatMessage("c31_r3", CharacterId.DAD, "Legal notice dispatched. They are looking into it.")
                    )
                )
            )
        ),

        // Chat 32: Caterer Problem
        ScenarioData(
            scenarioId = 32,
            chapter = 7,
            title = "Chat 32 — Caterer Problem",
            category = "CHAOTIC EVENTS",
            introDialogues = listOf(
                ChatMessage("c32_m1", CharacterId.DADI, "I just inspected the kitchen tasting menu. There is garlic and onion in the Dal Makhani and Paneer Tikka! I will not attend dinner!", timestamp = "1:30 PM"),
                ChatMessage("c32_m2", CharacterId.CHACHI, "Yes, Mataji cannot eat this! Who approved the caterer contract?!", timestamp = "1:35 PM"),
                ChatMessage("c32_m3", CharacterId.ROHAN, "Caterer says pure Jain prep will cost ₹400 extra per plate.", timestamp = "1:38 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c32_opt1",
                    text = "“I will personally pay the ₹2,000 extra kitchen surcharge to set up a dedicated sattvic Jain buffet counter for Dadi and our senior elders! Dadi's comfort is paramount ❤️”",
                    consequenceBadge = "-₹2,000 • +25 Dadi Trust • Royal Grandchild",
                    deltaMoney = -2000,
                    deltaTrust = 15,
                    deltaReputation = 20,
                    deltaDrama = -15,
                    relationshipImpact = mapOf(CharacterId.DADI to 25, CharacterId.MOM to 18, CharacterId.DAD to 14),
                    followupMessages = listOf(
                        ChatMessage("c32_r1", CharacterId.DADI, "Jeete raho mere laal! May all your wishes come true!", reactions = listOf("🙏", "🌸", "❤️")),
                        ChatMessage("c32_r2", CharacterId.MOM, "My child has such respect for elders.")
                    )
                ),
                ChatChoice(
                    id = "c32_opt2",
                    text = "“Can't Dadi just eat curd rice and fruits for one meal? ₹400 per plate is too high.”",
                    consequenceBadge = "Dadi furious • Family scandal • Drama +20",
                    deltaDrama = 20,
                    relationshipImpact = mapOf(CharacterId.DADI to -25, CharacterId.MOM to -15, CharacterId.BUA to -15),
                    followupMessages = listOf(
                        ChatMessage("c32_r3", CharacterId.DADI, "Curd rice?! After 75 years of service to this house, I am told to eat fruits like a monkey?!"),
                        ChatMessage("c32_r4", CharacterId.MOM, "Beta, apologize to Dadi immediately!")
                    )
                )
            )
        ),

        // Chat 33: Missing Jewellery
        ScenarioData(
            scenarioId = 33,
            chapter = 7,
            title = "Chat 33 — Missing Jewellery",
            category = "CHAOTIC EVENTS",
            introDialogues = listOf(
                ChatMessage("c33_m1", CharacterId.BUA, "Dadi's 22-karat Navratna heritage choker is not in the locker velvet box!! CALL THE HOTEL SECURITY!!", timestamp = "4:40 PM"),
                ChatMessage("c33_m2", CharacterId.DADI, "Voice message received from Dadi", timestamp = "4:45 PM", isVoiceNote = true, voiceDuration = "0:25", voiceTranscript = "Stop screeching like a peacock Anita! Nobody stole anything! I moved all the real gold coins and heritage jewelry into the vintage Horlicks jar inside my beige vanity bag so thieves wouldn't suspect!"),
                ChatMessage("c33_m3", CharacterId.KABIR, "The vintage Horlicks jar... Dadi you are a cybersecurity mastermind 💀", timestamp = "4:48 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c33_opt1",
                    text = "“Dadi's offline Horlicks encryption beats Swiss bank security any day! 😂 Let me keep the bag in the central room locker safely Dadi.”",
                    consequenceBadge = "🤫 Unlocks Secret #07: Horlicks Stash • +15 Dadi",
                    deltaTrust = 12,
                    deltaDrama = -10,
                    relationshipImpact = mapOf(CharacterId.DADI to 18, CharacterId.BUA to 8, CharacterId.KABIR to 10),
                    unlocksSecretId = "sec_07",
                    followupMessages = listOf(
                        ChatMessage("c33_r1", CharacterId.DADI, "Only you have permission to touch that bag my child.", reactions = listOf("👵", "💎")),
                        ChatMessage("c33_r2", CharacterId.BUA, "Mataji you almost gave me a cardiac arrest!")
                    )
                ),
                ChatChoice(
                    id = "c33_opt2",
                    text = "“Keeping ₹20 Lakhs of gold in a Horlicks tin is wildly unsafe Dadi! 🤦‍♂️”",
                    consequenceBadge = "Lecture backfires • Dadi rolls eyes",
                    deltaDrama = 4,
                    relationshipImpact = mapOf(CharacterId.DADI to -6),
                    followupMessages = listOf(
                        ChatMessage("c33_r3", CharacterId.DADI, "Thieves look for velvet boxes, not Horlicks. Experience speaks, child.")
                    )
                )
            )
        ),

        // Chat 34: Family Argument
        ScenarioData(
            scenarioId = 34,
            chapter = 7,
            title = "Chat 34 — Family Argument",
            category = "CHAOTIC EVENTS",
            introDialogues = listOf(
                ChatMessage("c34_m1", CharacterId.DAD, "Mahesh, I saw the Singhania builder token receipt in your briefcase! You were selling Dadaji's Jaipur land behind our backs for your son's share market trading losses!", timestamp = "8:30 PM"),
                ChatMessage("c34_m2", CharacterId.CHACHA, "Rajesh! You always treated me like your subordinate! I have equal blood right to Dadaji's inheritance!", timestamp = "8:35 PM"),
                ChatMessage("c34_m3", CharacterId.DADAJI, "BOTH OF YOU STOP. As long as I am breathing, nobody sells a single inch of ancestral earth.", timestamp = "8:40 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c34_opt1",
                    text = "“Dad, Mahesh Chacha, look at Dadaji's face. We are all his children. Instead of selling to greedy builders, why don't we turn that highway plot into a family trust commercial plaza where all brothers share monthly rental income forever?”",
                    consequenceBadge = "+25 All Elders • Master Visionary Plan",
                    deltaTrust = 20,
                    deltaReputation = 25,
                    deltaDrama = -15,
                    relationshipImpact = mapOf(CharacterId.DADAJI to 25, CharacterId.DAD to 20, CharacterId.CHACHA to 15),
                    followupMessages = listOf(
                        ChatMessage("c34_r1", CharacterId.DADAJI, "Brilliant! Generation wealth, not quick cash! My grandchild has true wisdom.", reactions = listOf("👏", "🌟", "❤️")),
                        ChatMessage("c34_r2", CharacterId.DAD, "A commercial plaza... that actually makes immense financial sense.")
                    )
                ),
                ChatChoice(
                    id = "c34_opt2",
                    text = "“Mahesh Chacha should be taken off all family bank accounts immediately!”",
                    consequenceBadge = "Takes sides • Chacha alienated • War looms",
                    deltaDrama = 20,
                    relationshipImpact = mapOf(CharacterId.CHACHA to -25, CharacterId.DAD to 10),
                    followupMessages = listOf(
                        ChatMessage("c34_r3", CharacterId.CHACHA, "So this is how my own family treats me. Very well.")
                    )
                )
            )
        ),

        // Chat 35: Police Confusion
        ScenarioData(
            scenarioId = 35,
            chapter = 7,
            title = "Chat 35 — Police Confusion",
            category = "CHAOTIC EVENTS",
            introDialogues = listOf(
                ChatMessage("c35_m1", CharacterId.KABIR, "SOS GUYS! Me and Arjun are stuck at Udaipur city police barricade! We were transporting 40kg of wedding kaju katli boxes in auto and cops think it's contraband because Kabir was wearing dark sunglasses inside auto! 😭🚔", timestamp = "11:15 PM"),
                ChatMessage("c35_m2", CharacterId.ARJUN, "They are asking for mithai invoice and auto permit! Uncle please call someone!", timestamp = "11:17 PM"),
                ChatMessage("c35_m3", CharacterId.CHACHI, "POLICE STATION?! My poor children!!", timestamp = "11:20 PM")
            ),
            choices = listOf(
                ChatChoice(
                    id = "c35_opt1",
                    text = "“*Sends digital GST invoice of sweets shop to Police Officer on WhatsApp and tells Kabir to offer a fresh box of Kaju Katli for the night duty officers*”",
                    consequenceBadge = "+20 Kabir & Arjun • Legend move • Crisis solved",
                    deltaTrust = 15,
                    deltaReputation = 18,
                    deltaDrama = -12,
                    relationshipImpact = mapOf(CharacterId.KABIR to 22, CharacterId.ARJUN to 22, CharacterId.CHACHI to 15),
                    followupMessages = listOf(
                        ChatMessage("c35_r1", CharacterId.KABIR, "BRO THE INSPECTOR SAID THE SWEETS ARE DELICIOUS AND GAVE US A POLICE ESCORT BACK TO HOTEL! 🚔🚨 Mithai diplomacy works!", reactions = listOf("🎉", "😂", "🙏")),
                        ChatMessage("c35_r2", CharacterId.ARJUN, "You are a real-life superhero @You!")
                    )
                ),
                ChatChoice(
                    id = "c35_opt2",
                    text = "“Tell the cops that Prakash Fufa is an ex-officer and will sue them!”",
                    consequenceBadge = "Makes cops angry • Fufa stammers",
                    deltaDrama = 12,
                    relationshipImpact = mapOf(CharacterId.FUFA to -10),
                    followupMessages = listOf(
                        ChatMessage("c35_r3", CharacterId.FUFA, "Wait wait don't use my name in traffic matters!!")
                    )
                )
            )
        )
    )
}
