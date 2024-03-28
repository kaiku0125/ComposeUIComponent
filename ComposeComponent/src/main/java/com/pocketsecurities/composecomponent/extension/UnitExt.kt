package com.pocketsecurities.composecomponent.extension

import android.content.Context
import android.media.AudioManager
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType

fun (() -> Unit).withEffect(
    context: Context,
    haptic: HapticFeedback,
    needSound: Boolean,
    needHaptic: Boolean,
): () -> Unit = {
    if (needSound) {
        (context.getSystemService(Context.AUDIO_SERVICE) as AudioManager)
            .playSoundEffect(AudioManager.FX_KEY_CLICK)
    }

    if (needHaptic) {
        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
    }

    this()
}