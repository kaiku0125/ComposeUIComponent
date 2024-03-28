package com.pocketsecurities.composeuidemo.preview.radio

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.composeuidemo.ui.theme.DarkComposeUIDemoTheme
import com.pocketsecurities.pocketcomposecomponent.component.radio.LeadingRadioSelectedComponent
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig

@PreviewLightDark
@Composable
private fun LeadingRadioSelectedComponentPreview() {

    val isChecked = remember { mutableStateOf(true) }

    ComposeUIDemoTheme {
        LeadingRadioSelectedComponent(
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth(),
            isChecked = isChecked.value,
            isEnabled = true,
            onFieldClick = {
                isChecked.value = isChecked.value.not()
            },
            content = {
                PocketText(
                    config = PocketTextConfig(
                        value = "GoodTiming郭台銘",
                        textColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        )
    }
}

@Preview
@Composable
fun LeadingRatioSelectedWithSameWeight() {
    val radioList = listOf(
        (false to "比特幣"),
        (true to "以太幣"),
        (false to "艾達幣")
    )

    DarkComposeUIDemoTheme {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            radioList.forEach { (isChecked, title) ->
                LeadingRadioSelectedComponent(
                    modifier = Modifier.weight(1f),
                    isChecked = isChecked,
                    isEnabled = true,
                    onFieldClick = {

                    },
                    content = {
                        PocketText(
                            config = PocketTextConfig(
                                value = title,
                                textColor = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                )
            }
        }

    }
}