package com.pocketsecurities.pocketcomposecomponent

import androidx.annotation.ColorRes
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
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

data class SimpleTextConfig(
    val value: String = "",
    val isEnable: Boolean = true,
    val alignment: Alignment = Alignment.Center,
    val style: TextStyle = TextStyle.Default,
    val letterSpacing: TextUnit = TextUnit.Unspecified,
    val overflow: TextOverflow = TextOverflow.Ellipsis,
    @ColorRes val textColor: Color = Color.White,
    val maxLines: Int = 1
)

/**
 * @sample SimpleText 用Box包裝Text來達成佈局上面的便捷性
 *
 * @param alignment 文字在區域中的位置
 * @param text 文字
 * @param style 文字樣式
 * @param textColor 文字顏色
 */

@Composable
fun SimpleText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    text: String,
    style: TextStyle = LocalTextStyle.current,
    isEnabled: Boolean = true,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    @ColorRes textColor: Color = LocalContentColor.current
) {
    Box(
        modifier = modifier,
        contentAlignment = alignment
    ) {
        Text(
            modifier = textModifier,
            text = text,
            style = style,
            color = if (isEnabled) {
                textColor
            } else {
                color_717071
            },
            maxLines = 1,
            overflow = overflow,
            letterSpacing = letterSpacing
        )
    }
}

/**
 * @sample SimpleText 用Box包裝Text來達成佈局上面的便捷性
 *
 * @param config SimpleTextConfig設定
 */

@Composable
fun SimpleText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    config: SimpleTextConfig = SimpleTextConfig()
) {
    Box(
        modifier = modifier,
        contentAlignment = config.alignment
    ) {
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

/**
 * @sample AnimatedSimpleText 帶有動畫效果的 SimpleText
 *
 * @param config SimpleTextConfig設定
 */
@Composable
fun AnimatedSimpleText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    config: SimpleTextConfig = SimpleTextConfig()
) {
    // 文字 color 改變動畫目前先這樣比較不突兀
    val aniColor = remember { Animatable(Color.White) }

    LaunchedEffect(config.value) {
        aniColor.animateTo(config.textColor, animationSpec = tween(500))
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
        SimpleText(
            modifier = modifier,
            textModifier = textModifier,
            config = config.copy(
                value = targetCount,
                textColor = aniColor.value
            )
        )
    }
}


@Composable
fun SimpleAnnotatedText(
    modifier: Modifier = Modifier,
    text: AnnotatedString,
    style: TextStyle = LocalTextStyle.current
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = style,
            maxLines = 1
        )
    }
}


///**
// * @sample SimpleTextWithClickEffect 擁有點擊事件的 SimpleText
// *
// * @param config SimpleTextConfig設定
// * @param clickableConfig clickable設定
// * @param onClick export 點擊事件
// */
//@Composable
//fun SimpleTextWithClickEffect(
//    modifier: Modifier = Modifier,
//    textModifier: Modifier = Modifier,
//    config: SimpleTextConfig = SimpleTextConfig(),
//    clickableConfig: ClickableConfig = ClickableConfig(),
//    onClick: () -> Unit,
//) {
//    Box(
//        modifier = modifier.clickableEffectConfig(
//            config = clickableConfig,
//            onClick = {
//                onClick.invoke()
//            }
//        ),
//        contentAlignment = config.alignment
//    ) {
//        Text(
//            modifier = textModifier,
//            text = config.value,
//            style = config.style,
//            color = config.textColor,
//            maxLines = 1,
//            letterSpacing = config.letterSpacing,
//            overflow = config.overflow
//        )
//    }
//}

///**
// * @sample SimpleTextWithClickEffect 擁有點擊相關特效的 SimpleText
// *
// * @param alignment 文字在區域中的位置
// * @param text 文字
// * @param style 文字樣式
// * @param textColor 文字顏色
// * @param onClick export點擊事件
// */
//@Composable
//fun SimpleTextWithClickEffect(
//    modifier: Modifier = Modifier,
//    alignment: Alignment = Alignment.Center,
//    text: String,
//    style: TextStyle = MaterialTheme.typography.text_19sp_500weight,
//    @ColorRes textColor: Color = Color.White,
//    config: ClickableConfig = ClickableConfig(),
//    onClick: () -> Unit,
//) {
//    Box(
//        modifier = modifier.clickableEffectConfig(
//            config = config,
//            onClick = {
//                onClick.invoke()
//            }
//        ),
//        contentAlignment = alignment
//    ) {
//        Text(
//            text = text,
//            style = style,
//            color = textColor,
//            maxLines = 1
//        )
//    }
//}

