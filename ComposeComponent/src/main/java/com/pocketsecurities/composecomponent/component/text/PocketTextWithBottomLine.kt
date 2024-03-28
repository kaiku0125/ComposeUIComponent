package com.pocketsecurities.composecomponent.component.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import com.pocketsecurities.composecomponent.extension.ClickableConfig
import com.pocketsecurities.composecomponent.extension.clickableEffectConfig

@Composable
fun PocketTextWithBottomLine(
    modifier: Modifier = Modifier,
    config: PocketTextConfig = PocketTextConfig(),
    onClick: (() -> Unit)? = null
) {
    PocketText(
        modifier = modifier
            .drawWithContent {
                drawContent()
                drawLine(
                    color = config.textColor,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 2f
                )
            }
            .clickableEffectConfig(
                config = ClickableConfig(
                    needRipple = onClick != null,
                    needSound = onClick != null,
                    needHaptic = onClick != null,
                ),
                onClick = {
                    onClick?.invoke()
                }
            ),
        config = config
    )
}