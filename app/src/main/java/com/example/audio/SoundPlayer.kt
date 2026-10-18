package com.example.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.sin

class SoundPlayer(private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)) {

    var isSoundEnabled: Boolean = true

    fun playNotification() {
        if (!isSoundEnabled) return
        scope.launch {
            playToneSeries(
                listOf(
                    Tone(frequency = 784.0, durationMs = 80, volume = 0.5f), // G5
                    Tone(frequency = 1046.5, durationMs = 120, volume = 0.6f) // C6
                )
            )
        }
    }

    fun playMessageSent() {
        if (!isSoundEnabled) return
        scope.launch {
            playToneSeries(
                listOf(
                    Tone(frequency = 1200.0, durationMs = 40, volume = 0.35f),
                    Tone(frequency = 1600.0, durationMs = 60, volume = 0.4f)
                )
            )
        }
    }

    fun playDramaticHit() {
        if (!isSoundEnabled) return
        scope.launch {
            playToneSeries(
                listOf(
                    Tone(frequency = 130.81, durationMs = 400, volume = 0.8f), // C3
                    Tone(frequency = 155.56, durationMs = 450, volume = 0.75f) // D#3
                )
            )
        }
    }

    fun playSuccessChime() {
        if (!isSoundEnabled) return
        scope.launch {
            playToneSeries(
                listOf(
                    Tone(frequency = 523.25, durationMs = 70, volume = 0.4f), // C5
                    Tone(frequency = 659.25, durationMs = 70, volume = 0.5f), // E5
                    Tone(frequency = 783.99, durationMs = 70, volume = 0.6f), // G5
                    Tone(frequency = 1046.50, durationMs = 150, volume = 0.7f) // C6
                )
            )
        }
    }

    fun playRingtone() {
        if (!isSoundEnabled) return
        scope.launch {
            repeat(2) {
                playToneSeries(
                    listOf(
                        Tone(frequency = 440.0, durationMs = 150, volume = 0.5f),
                        Tone(frequency = 480.0, durationMs = 150, volume = 0.5f),
                        Tone(frequency = 440.0, durationMs = 150, volume = 0.5f),
                        Tone(frequency = 0.0, durationMs = 100, volume = 0f)
                    )
                )
            }
        }
    }

    private data class Tone(val frequency: Double, val durationMs: Int, val volume: Float)

    private fun playToneSeries(tones: List<Tone>) {
        try {
            val sampleRate = 44100
            val totalSamples = tones.sumOf { (it.durationMs * sampleRate) / 1000 }
            val buffer = ShortArray(totalSamples)

            var offset = 0
            for (tone in tones) {
                val numSamples = (tone.durationMs * sampleRate) / 1000
                for (i in 0 until numSamples) {
                    val angle = 2.0 * Math.PI * i / (sampleRate / if (tone.frequency > 0) tone.frequency else 1.0)
                    val rawSample = if (tone.frequency > 0) sin(angle) else 0.0
                    // Apply smooth envelope to prevent clicking
                    val envelope = when {
                        i < 200 -> i / 200.0
                        i > numSamples - 200 -> (numSamples - i) / 200.0
                        else -> 1.0
                    }
                    val sample = (rawSample * envelope * tone.volume * Short.MAX_VALUE).toInt()
                    buffer[offset + i] = sample.coerceIn(Short.MIN_VALUE.toInt(), Short.MAX_VALUE.toInt()).toShort()
                }
                offset += numSamples
            }

            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            val audioFormat = AudioFormat.Builder()
                .setSampleRate(sampleRate)
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                .build()

            val bufferSize = buffer.size * 2
            val track = AudioTrack.Builder()
                .setAudioAttributes(audioAttributes)
                .setAudioFormat(audioFormat)
                .setBufferSizeInBytes(bufferSize)
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build()

            track.write(buffer, 0, buffer.size)
            track.play()
            Thread.sleep(tones.sumOf { it.durationMs }.toLong() + 50)
            track.stop()
            track.release()
        } catch (_: Exception) {
            // Graceful fallback if device audio is restricted
        }
    }
}
