package com.pocketsecurities.composeuidemo.preview.bottomsheet

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.component.bottomsheet.BottomSheetSceneComponent
import com.pocketsecurities.pocketcomposecomponent.component.bottomsheet.BottomSheetSceneConfig
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig

@Preview
@Composable
private fun BottomSheetSceneComponentPreview() {
    BottomSheetSceneComponent(
        config = BottomSheetSceneConfig(
            titleConfig = PocketTextConfig(
                value = "口袋 x MOMO 交易滿額禮",
                textColor = Color.White
            ),
            screenRatio = 0.2f
        ),
        content = {
            PocketText(
                modifier = Modifier.padding(horizontal = 20.dp),
                config = PocketTextConfig(
                    value = "活動時間：2024/3/1~5/31\n" +
                            "活動說明：依登記當月起計算，次月交易金額歸零重新計算，僅需登記一次即可",
                    maxLines = 3
                )
            )
        },
        onDismiss = {

        }
    )
}