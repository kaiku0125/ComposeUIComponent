package com.pocketsecurities.composecomponent.component.radio


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composecomponent.extension.ClickableConfig
import com.pocketsecurities.composecomponent.extension.clickableEffectConfig

@Composable
fun TrailingRadioSelectedComponent(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    isEnabled: Boolean = true,
    background: Color = MaterialTheme.colorScheme.background,
    horizontalPadding: Dp = 16.dp,
    clickableConfig: ClickableConfig = ClickableConfig(),
    onFieldClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .background(background)
            .clickableEffectConfig(
                config = clickableConfig,
                onClick = {
                    if (isEnabled) {
                        onFieldClick.invoke()
                    }
                }
            )
            .padding(horizontal = horizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        content.invoke(this)

        PocketRadioIconComponent(
            modifier = Modifier.padding(top = 1.dp), // 不確定為何會有1.dp的跑版
            isChecked = isChecked,
            isEnabled = isEnabled,
            iconSize = 24.dp
        )
    }
}