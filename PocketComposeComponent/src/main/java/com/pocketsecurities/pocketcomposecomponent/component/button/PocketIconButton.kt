package com.pocketsecurities.pocketcomposecomponent.component.button

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.extension.ClickableConfig
import com.pocketsecurities.pocketcomposecomponent.extension.clickableEffectConfig

/**
 * @sample PocketIconButton 用 Box 來包裝 IconButton 達成佈局便利性
 *
 * @param modifier 必須配置 size 否則 IconButton fillMaxSize 會跑版
 * @param tint Icon顏色
 * @param iconSize Icon大小
 * @param onIconClick Icon點擊事件
 */
@Composable
fun PocketIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes drawableRes: Int,
    tint : Color = Color.Unspecified,
    iconSize : Dp = 17.dp,
    iconModifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    clickableConfig: ClickableConfig = ClickableConfig(needHaptic = true),
    onIconClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .clickableEffectConfig(
                config = clickableConfig,
                onClick = {
                    onIconClick.invoke()
                }
            )
    ) {
        Icon(
            modifier = iconModifier.size(iconSize),
            painter = painterResource(id = drawableRes),
            contentDescription = "simple_icon_button_by_drawable",
            tint = tint
        )
    }
}

@Composable
fun PocketIconButton(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
    imageVector: ImageVector,
    shape: Shape = CircleShape,
    tint: Color = Color.Unspecified,
    iconSize: Dp = 17.dp,
    clickableConfig: ClickableConfig = ClickableConfig(needHaptic = true),
    onIconClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .clickableEffectConfig(
                config = clickableConfig,
                onClick = {
                    onIconClick.invoke()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = iconModifier.size(iconSize),
            imageVector = imageVector,
            contentDescription = "simple_icon_button_by_drawable",
            tint = tint
        )
    }
}

@Composable
fun PocketRotationIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector = Icons.Default.KeyboardArrowDown,
    @DrawableRes imageResource: Int? = null,
    tint: Color = Color.Unspecified,
    iconSize: Dp = 17.dp,
    isExpand: Boolean,
    clickableConfig: ClickableConfig = ClickableConfig(needHaptic = true),
    onIconClick: () -> Unit,
) {
    var rotateAnimationAngle by remember {
        mutableFloatStateOf(0f)
    }

    val rotateAnimation = animateFloatAsState(
        targetValue = rotateAnimationAngle,
        animationSpec = tween(
            easing = LinearEasing
        ), label = ""
    )

    LaunchedEffect(key1 = isExpand) {
        rotateAnimationAngle = if (isExpand) {
            180f
        } else {
            0f
        }
    }

    if (imageResource != null) {
        PocketIconButton(
            modifier = modifier
                .size(24.dp)
                .graphicsLayer {
                    rotationZ = rotateAnimation.value
                },
            drawableRes = imageResource,
            iconSize = iconSize,
            tint = tint,
            clickableConfig = clickableConfig,
            onIconClick = onIconClick
        )
    } else {
        PocketIconButton(
            modifier = modifier
                .size(24.dp)
                .graphicsLayer {
                    rotationZ = rotateAnimation.value
                },
            imageVector = imageVector,
            iconSize = iconSize,
            tint = tint,
            clickableConfig = clickableConfig,
            onIconClick = onIconClick
        )
    }
}