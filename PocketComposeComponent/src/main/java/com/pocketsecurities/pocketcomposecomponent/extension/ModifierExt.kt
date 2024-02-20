package com.pocketsecurities.pocketcomposecomponent.extension

import android.annotation.SuppressLint
import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * @sample ClickableConfig 點擊compose設定
 *
 * @param needRipple 是否需要點擊漣漪效果
 * @param needSound 是否需要音效
 * @param needHaptic 是否需要震動 (預設關閉)
 */

data class ClickableConfig(
    val needRipple: Boolean = true,
    val needSound: Boolean = true,
    val needHaptic: Boolean = false
)


/**
 * @sample clickableEffectConfig onClick的點擊extension
 *
 * @param config 點擊設定
 * @param onClick export點擊事件
 */

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.clickableEffectConfig(
    config: ClickableConfig = ClickableConfig(),
    onClick: () -> Unit
) = composed(

    factory = {
        val context = LocalContext.current
        val haptic = LocalHapticFeedback.current

        this.then(
            if (config.needRipple.not()) {
                Modifier.clickable(
                    interactionSource = remember{ MutableInteractionSource() } ,
                    indication = null,
                    onClick = {
                        onClick.withEffect(
                            context = context,
                            haptic = haptic,
                            needSound = config.needSound,
                            needHaptic = config.needHaptic
                        ).invoke()
                    }
                )
            } else {
                Modifier.clickable(
                    onClick = {
                        onClick.withEffect(
                            context = context,
                            haptic = haptic,
                            needSound = config.needSound,
                            needHaptic = config.needHaptic
                        ).invoke()
                    }
                )
            }

        )
    }
)

/**
 * @sample toStyleOfDigitalKeyBoard 調整為鍵盤樣式的extension
 */
fun Modifier.toStyleOfDigitalKeyBoard(
    height: Dp = 50.dp,
    background: Color = Color.Gray,
    shape: Shape = RoundedCornerShape(12.dp)
): Modifier {
    return this
        .height(height)
        .background(
            shape = shape,
            color = background
        )
        .clip(shape)
}

/**
 * @sample ratioHeight 自定義依照手機螢幕調整適當元件高度比例
 *
 * @param height 填入 FIGMA 給的高度
 * @param isUnderSizeConstant 當希望維持最小的大小 ➔ true, 希望自適應的狀態 ➔ false,
 */

fun Modifier.ratioHeight(
    height: Dp,
    isUnderSizeConstant: Boolean = true // 預設為維持最小大小, 類似 min Height的感覺
) = composed(
    factory = {
        val defaultHeight = 812 // figma 預設高度
        val configuration = LocalConfiguration.current

        val screenRatio by remember {
            mutableFloatStateOf(
                (configuration.screenHeightDp.dp / defaultHeight).value
            )
        }

        this.then(
            Modifier.height(
                if (isUnderSizeConstant && screenRatio < 1) {
                    height
                } else {
                    height * screenRatio
                }
            )
        )
    }
)

/**
 *  @sample screenHeight
 *
 *  @param ratio 螢幕比例
 */
fun Modifier.screenHeight(
    ratio: Float
)= composed(
    factory = {
        val configuration = LocalConfiguration.current

        val height by remember {
            mutableFloatStateOf(
                (configuration.screenHeightDp.dp * ratio).value
            )
        }

        this.then(
            Modifier.height(height.dp)
        )
    }
)

/**
 * @sample ratioSize 自定義依照手機螢幕調整適當元件大小比例
 *
 * @param figmaSize 填入 FIGMA 給的高度
 */
fun Modifier.ratioSize(
    figmaSize: Dp
) = composed(
    factory = {
        val figmaScreenWidth = 375 // figma 預設寬度
        val configuration = LocalConfiguration.current

        val ratio by remember {
            mutableFloatStateOf(
                (figmaSize / figmaScreenWidth).value
            )
        }

        this.then(
            Modifier.size(configuration.screenWidthDp.dp * ratio)
        )
    }
)

fun Modifier.advanceShadow(
    color: Color = Color.Black,
    borderRadius: Dp = 16.dp,
    blurRadius: Dp = 16.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Float = 1f,
) = drawBehind {
    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        val spreadPixel = spread.dp.toPx()
        val leftPixel = (0f - spreadPixel) + offsetX.toPx()
        val topPixel = (0f - spreadPixel) + offsetY.toPx()
        val rightPixel = (this.size.width)
        val bottomPixel = (this.size.height + spreadPixel)

        if (blurRadius != 0.dp) {
            // The feature maskFilter used below to apply the blur effect only works with hardware acceleration disabled.
            frameworkPaint.maskFilter =
                (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
        }

        frameworkPaint.color = color.toArgb()
        it.drawRoundRect(
            left = leftPixel,
            top = topPixel,
            right = rightPixel,
            bottom = bottomPixel,
            radiusX = borderRadius.toPx(),
            radiusY = borderRadius.toPx(),
            paint
        )
    }
}

/**
 * @sample circleShadow 圓形陰影
 *
 * @property color 陰影顏色
 * @property size 物件大小
 * @property blurRadius 欲在物件外圍再增添多大的陰影範圍
 * @property centerOffsetX 陰影對於物件中心點的偏移量
 * @property centerOffsetY 陰影對於物件中心點的偏移量
 */
fun Modifier.circleShadow(
    color: Color = Color.Black,
    size: Dp = 32.dp,
    blurRadius: Dp = 3.dp,
    centerOffsetX: Dp = 0.dp,
    centerOffsetY: Dp = 0.dp
) = drawBehind {
    this.drawIntoCanvas {
        val center = size.toPx() / 2
        val offsetX = center + centerOffsetX.toPx()
        val offsetY = center + centerOffsetY.toPx()
        val realRadius = center + blurRadius.toPx()

        val paint = Paint().apply {
            shader = RadialGradientShader(
                center = Offset(offsetX, offsetY),
                colors = listOf(color, Color.Transparent),
                radius = realRadius
            )
        }

        it.drawCircle(
            center = Offset(offsetX, offsetY),
            radius = realRadius,
            paint = paint
        )
    }
}

