package com.pocketsecurities.pocketcomposecomponent.component.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

/**
 * @sample SimpleTextWithHint
 *
 * @param contentConfig 主內容文檔設定
 * @param hintConfig 提示文檔設定
 * @param paddingToContent 設定內容與提示文字之間的間距
 */
@Composable
fun SimpleTextWithHint(
    modifier: Modifier = Modifier,
    contentConfig: PocketTextConfig = PocketTextConfig(
        value = "",
        style = TextStyle.Default.copy(
            fontSize = 15.sp,
            fontWeight = FontWeight(500)
        ),
        alignment = Alignment.Center,
        textColor = Color.White
    ),
    hintConfig: PocketTextConfig = PocketTextConfig(
        value = "",
        style = TextStyle.Default.copy(
            fontSize = 11.sp,
            fontWeight = FontWeight(400)
        ),
        alignment = Alignment.Center,
        textColor = Color.LightGray
    ),
    paddingToContent: Dp = 0.dp
) {
    ConstraintLayout(
        modifier = modifier
    ) {

        val (contentRegion, hintRegion) = createRefs()

        PocketText(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(contentRegion) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                },
            config = contentConfig
        )

        PocketText(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(hintRegion) {
                    top.linkTo(contentRegion.bottom, margin = paddingToContent)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
            config = hintConfig
        )

    }

}

@Preview
@Composable
private fun SimpleTextWithHintPreview() {
    SimpleTextWithHint(
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