package com.pocketsecurities.composeuidemo.preview.radio

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.pocketcomposecomponent.component.radio.TrailingRadioSelectedComponent
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig

@PreviewLightDark
@Composable
private fun TrailingRadioSelectedComponentPreview() {

    val isChecked = remember { mutableStateOf(true) }

    ComposeUIDemoTheme {
        TrailingRadioSelectedComponent(
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth(),
            isChecked = isChecked.value,
            onFieldClick = {
                isChecked.value = isChecked.value.not()
            },
            content = {
                PocketText(
                    config = PocketTextConfig(
                        value = "喝了搖曳",
                        textColor = MaterialTheme.colorScheme.onBackground,
                        alignment = Alignment.CenterStart
                    )
                )
            }
        )
    }

}