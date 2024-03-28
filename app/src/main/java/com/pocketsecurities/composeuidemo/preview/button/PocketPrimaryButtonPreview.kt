package com.pocketsecurities.composeuidemo.preview.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.pocketcomposecomponent.component.button.PocketPrimaryButton
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig

@Preview
@Composable
private fun PocketPrimaryButtonPreview() {
    ComposeUIDemoTheme {
        PocketPrimaryButton(
            config = PocketTextConfig(
                value = "口袋證券"
            ),
            onClick = {

            }
        )
    }
}