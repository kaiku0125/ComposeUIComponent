package com.pocketsecurities.composeuidemo.preview.loading

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.composecomponent.color_9e9e9f
import com.pocketsecurities.composecomponent.component.loading.CircularPercentageComponent
import com.pocketsecurities.composecomponent.component.loading.CircularPercentageConfig
import com.pocketsecurities.composecomponent.component.text.PocketText
import com.pocketsecurities.composecomponent.component.text.PocketTextConfig

@Preview
@Composable
private fun CircularPercentageComponentPreview() {
    ComposeUIDemoTheme {
        CircularPercentageComponent(
            modifier = Modifier.padding(10.dp),
            config = CircularPercentageConfig(
                percentage = 87f,
                number = 500,
                radius = 50.dp,
                strokeColor = MaterialTheme.colorScheme.primary,
                bg = color_9e9e9f
            ),
            centerContent = {
                val percent = it.nowPercent.toInt()
                val number = it.number * percent / 100
                
                PocketText(
                    config = PocketTextConfig(
                        value = "$number [${percent} %]",
                    )
                )
            }
        )
    }
}