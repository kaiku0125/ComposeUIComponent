package com.pocketsecurities.composeuidemo.preview.text

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextWithBottomLine

@PreviewLightDark
@Composable
private fun SimpleTextWithBottomLinePreview() {
    ComposeUIDemoTheme {
        PocketText(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            config = PocketTextConfig(
                value = "54879487",
                textColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}

@PreviewLightDark
@Composable
private fun PocketTextWithBottomLinePreview() {
    ComposeUIDemoTheme {
        PocketTextWithBottomLine(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(10.dp),
            config = PocketTextConfig(
                value = "54879487",
                textColor = MaterialTheme.colorScheme.onBackground
            )
        )
    }
}
