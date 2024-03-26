package com.pocketsecurities.pocketcomposecomponent.component.radio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.color_333333
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.extension.ClickableConfig
import com.pocketsecurities.pocketcomposecomponent.extension.clickableEffectConfig

@Composable
fun LeadingRadioSelectedComponent(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    isEnabled: Boolean,
    horizontalPadding: Dp = 16.dp,
    onFieldClick : () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(color_333333)
            .clickableEffectConfig(
                config = ClickableConfig(
                    needHaptic = false
                ),
                onClick = {
                    if (isEnabled) {
                        onFieldClick.invoke()
                    }
                }
            )
            .padding(horizontal = horizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PocketRadioIconComponent(
            modifier = Modifier.padding(top = 1.dp), // 不確定為何會有1.dp的跑版
            isChecked = isChecked,
            isEnabled = isEnabled,
            iconSize = 24.dp
        )

        Spacer(modifier = Modifier.width(5.dp))

        content()
    }
}

@Preview
@Composable
private fun LeadingRadioSelectedComponentPreview(){

    val isChecked = remember { mutableStateOf(true) }

    LeadingRadioSelectedComponent(
        modifier = Modifier.height(45.dp),
        isChecked = isChecked.value,
        isEnabled = true,
        onFieldClick = {
            isChecked.value = isChecked.value.not()
        },
        content = {
            PocketText(
                config = PocketTextConfig(
                    value = "GoodTiming郭台銘"
                )
            )
        }
    )
}