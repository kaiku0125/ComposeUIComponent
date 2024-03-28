package com.pocketsecurities.composeuidemo.preview.radio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.composecomponent.component.radio.PocketRadioIconComponent

@PreviewLightDark
@Composable
private fun PocketRadioIconComponentPreview() {

    val isChecked = remember { mutableStateOf(true) }

    ComposeUIDemoTheme {
        PocketRadioIconComponent(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .clickable {
                    isChecked.value = isChecked.value.not()
                },
            isChecked = isChecked.value,
            isEnabled = true,
        )
    }
}