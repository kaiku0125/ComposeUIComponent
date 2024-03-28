package com.pocketsecurities.composeuidemo.preview.text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextWithHint

@Preview
@Composable
private fun PocketTextWithHintPreview() {
    PocketTextWithHint(
        modifier = Modifier,
        contentConfig = PocketTextConfig(
            value = "內容",
            alignment = Alignment.CenterStart,
            textColor = Color.White
        ),
        hintConfig = PocketTextConfig(
            value = "提示",
            alignment = Alignment.CenterStart,
            textColor = Color.LightGray
        ),
        paddingToContent = 0.dp
    )
}