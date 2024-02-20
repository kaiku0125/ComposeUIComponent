package com.pocketsecurities.composeuidemo.preview.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextWithBottomLine

// box 的背景不確定為啥都是黑色的
@PreviewLightDark
@Composable
private fun SimpleTextWithBottomLinePreview() {
    ComposeUIDemoTheme {
        PocketText(
            modifier = Modifier,
            config = PocketTextConfig(
                value = "54879487"
            )
        )
    }
}

@Preview
@Composable
private fun PocketTextWithBottomLinePreview() {
    ComposeUIDemoTheme {
        PocketTextWithBottomLine(
            config = PocketTextConfig(
                value = "54879487"
            )
        )
    }
}
