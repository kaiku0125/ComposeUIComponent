package com.pocketsecurities.pocketcomposecomponent.component.toggle

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pocketsecurities.pocketcomposecomponent.extension.ClickableConfig
import com.pocketsecurities.pocketcomposecomponent.extension.clickableEffectConfig

data class PocketToggleConfig(
    val isTurnedOn: Boolean = false,
    val alignment: Alignment = Alignment.Center,
    val clickableConfig: ClickableConfig = ClickableConfig()
)

@Composable
fun PocketToggle(
    modifier: Modifier = Modifier,
    config: PocketToggleConfig = PocketToggleConfig(),
    turnOnContent: @Composable (() -> Unit)? = {},
    turnOffContent: @Composable (() -> Unit)? = {},
    onToggle: (Boolean) -> Unit = {}
) {
    Box(
        modifier = modifier.clickableEffectConfig(
            config = config.clickableConfig,
            onClick = {
                onToggle(config.isTurnedOn.not())
            }
        ),
        contentAlignment = Alignment.Center,
        content = {
            Crossfade(
                targetState = config.isTurnedOn,
                label = "onToggleLabel"
            ) { toggleState ->
                if (toggleState) {
                    turnOnContent?.invoke()
                } else {
                    turnOffContent?.invoke()
                }
            }

        }
    )
}
