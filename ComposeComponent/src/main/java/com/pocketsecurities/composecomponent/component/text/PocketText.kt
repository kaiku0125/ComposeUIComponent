package com.pocketsecurities.composecomponent.component.text

import androidx.annotation.ColorRes
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.pocketsecurities.composecomponent.color_717071
import com.pocketsecurities.composecomponent.extension.ClickableConfig
import com.pocketsecurities.composecomponent.extension.clickableEffectConfig

/**
 * @sample PocketTextConfig 文字設定
 *
 * @property value 文字內容
 * @property valueAnnotated annotated 文字內容
 * @property textColor 文字顏色
 * @property style 文字樣式(粗體大小)
 * @property isEnable 是否為可點擊狀態
 * @property alignment 對其方式
 * @property letterSpacing 文字間隔
 * @property overflow 文字溢出樣式(預設超出為...)
 * @property maxLines 最大文字行數(預設一行)
 */
data class PocketTextConfig(
    val value: String = "",
    val valueAnnotated: AnnotatedString? = null,
    @ColorRes val textColor: Color = Color.White,
    val style: TextStyle = TextStyle.Default,
    val isEnable: Boolean = true,
    val alignment: Alignment = Alignment.Center,
    val letterSpacing: TextUnit = TextUnit.Unspecified,
    val overflow: TextOverflow = TextOverflow.Ellipsis,
    val maxLines: Int = 1
)

/**
 * @sample PocketText 用Box包裝Text來達成佈局上面的便捷性
 *
 * @param config BaseTextConfig
 */
@Composable
fun PocketText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    config: PocketTextConfig = PocketTextConfig()
) {
    Box(
        modifier = modifier,
        contentAlignment = config.alignment
    ) {
        if (config.valueAnnotated != null) {
            Text(
                text = config.valueAnnotated,
                style = config.style,
                maxLines = config.maxLines
            )
        } else {
            Text(
                modifier = textModifier,
                text = config.value,
                style = config.style,
                color = if (config.isEnable) {
                    config.textColor
                } else {
                    color_717071
                },
                maxLines = config.maxLines,
                overflow = config.overflow,
                letterSpacing = config.letterSpacing
            )
        }
    }
}

@Composable
fun SimpleText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    text: String,
) {
    PocketText(
        modifier = modifier,
        textModifier = textModifier,
        config = PocketTextConfig(value = text)
    )
}

/**
 * @sample AnimatedPocketText 帶有動畫效果的 SimpleText
 *
 * @param config PocketTextConfig設定
 */
@Composable
fun AnimatedPocketText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    config: PocketTextConfig = PocketTextConfig()
) {
    // 文字 color 改變動畫目前先這樣比較不突兀
    val aniColor = remember { Animatable(Color.White) }

    LaunchedEffect(config.value) {
        aniColor.animateTo(
            targetValue = config.textColor,
            animationSpec = tween(500)
        )
    }

    AnimatedContent(
        targetState = config.value,
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 500)) togetherWith
                    fadeOut(animationSpec = tween(durationMillis = 500))
        },
        contentAlignment = Alignment.Center,
        label = ""
    ) { targetCount ->
        PocketText(
            modifier = modifier,
            textModifier = textModifier,
            config = config.copy(
                value = targetCount,
                textColor = aniColor.value
            )
        )
    }
}

/**
 * @sample PocketTextWithClickEffect 擁有點擊事件的 SimpleText
 *
 * @param config PocketTextConfig
 * @param clickableConfig clickable設定
 * @param onClick export 點擊事件
 */
@Composable
fun PocketTextWithClickEffect(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    config: PocketTextConfig = PocketTextConfig(),
    clickableConfig: ClickableConfig = ClickableConfig(),
    onClick: () -> Unit
) {
    PocketText(
        modifier = modifier.clickableEffectConfig(
            config = clickableConfig,
            onClick = {
                onClick.invoke()
            }
        ),
        textModifier = textModifier,
        config = config
    )
}
