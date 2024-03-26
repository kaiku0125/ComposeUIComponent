package com.pocketsecurities.pocketcomposecomponent.component.radio


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.color_333333
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.extension.ClickableConfig
import com.pocketsecurities.pocketcomposecomponent.extension.clickableEffectConfig

@Composable
fun TrailingRadioSelectedComponent(
    modifier: Modifier = Modifier,
    textConfig: PocketTextConfig = PocketTextConfig(),
    isChecked: Boolean,
    background : Color = color_333333,
    horizontalPadding: Dp = 16.dp,
    onFieldClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(background)
            .clickableEffectConfig(
                config = ClickableConfig(
                    needRipple = false
                ),
                onClick = {
                    onFieldClick.invoke()
                }
            )
            .padding(horizontal = horizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PocketText(
            config = textConfig
        )

        PocketRadioIconComponent(
            isChecked = isChecked,
            isEnabled = true,
            iconSize = 24.dp
        )
    }
}

@Preview
@Composable
private fun TrailingRadioSelectedComponentPreview() {

    val isChecked = remember { mutableStateOf(true) }

    TrailingRadioSelectedComponent(
        modifier = Modifier.height(45.dp),
        textConfig = PocketTextConfig(
            value = "喝了搖曳",
            alignment = Alignment.CenterStart
        ),
        isChecked = isChecked.value,
        onFieldClick = {
            isChecked.value = isChecked.value.not()
        }
    )

}