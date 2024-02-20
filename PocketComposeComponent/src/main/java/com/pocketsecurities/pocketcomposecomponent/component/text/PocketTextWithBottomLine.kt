package com.pocketsecurities.pocketcomposecomponent.component.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.pocketsecurities.pocketcomposecomponent.extension.ClickableConfig
import com.pocketsecurities.pocketcomposecomponent.extension.clickableEffectConfig

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

@Preview
@Composable
private fun SimpleTextWithBottomLinePreview() {
    PocketTextWithBottomLine(
        config = PocketTextConfig(
            value = "54879487"
        )
    )
}