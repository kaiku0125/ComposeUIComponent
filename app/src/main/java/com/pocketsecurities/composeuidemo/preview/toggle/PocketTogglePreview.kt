package com.pocketsecurities.composeuidemo.preview.toggle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ThumbUp
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.composecomponent.component.toggle.PocketToggle
import com.pocketsecurities.composecomponent.component.toggle.PocketToggleConfig


@Preview
@Composable
private fun PocketTogglePreview() {
    ComposeUIDemoTheme {
        var isOn by remember { mutableStateOf(false) }

        PocketToggle(
            modifier = Modifier.background(Color.White),
            config = PocketToggleConfig(
                isTurnedOn = isOn
            ),
            turnOnContent = {
                Image(imageVector = Icons.Rounded.ThumbUp, contentDescription = null)
            },
            turnOffContent = {
                Image(imageVector = Icons.Rounded.Warning, contentDescription = null)
            },
            onToggle = { newValue ->
                isOn = newValue
            }
        )
    }
}