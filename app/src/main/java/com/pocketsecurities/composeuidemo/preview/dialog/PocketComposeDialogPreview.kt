package com.pocketsecurities.composeuidemo.preview.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.composecomponent.component.dialog.PocketComposeDialog

@Composable
@Preview
fun PreviewBaseDialog() {
    ComposeUIDemoTheme {
        PocketComposeDialog(
            title = "我是標題",
            contentMessage = "嗨\n我是第一行\n充數第二行"
        )
    }
}

@Composable
@Preview
fun PreviewOneButtonDialog() {
    ComposeUIDemoTheme {
        PocketComposeDialog(
            title = "我是標題",
            contentMessage = "嗨\n我是第一行\n充數第二行",
            positiveText = "我知道辣",
            negativeText = null
        )
    }
}

@Composable
@Preview
fun PreviewTitleMessageDialog() {
    ComposeUIDemoTheme {
        PocketComposeDialog(
            contentMessage = "嗨\n我是第一行\n充數第二行",
            positiveText = null,
            negativeText = null
        )
    }
}