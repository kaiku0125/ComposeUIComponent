package com.pocketsecurities.pocketcomposecomponent.component.bottomsheet

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.color_333333
import com.pocketsecurities.pocketcomposecomponent.component.button.PocketIconButton
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.extension.ClickableConfig
import com.pocketsecurities.pocketcomposecomponent.extension.ratioHeight
import com.pocketsecurities.pocketcomposecomponent.extension.screenHeight

/**
 *  @sample BottomSheetSceneConfig
 *
 *  @property titleConfig 標題的設定
 *  @property shape bottom sheet的圓角設定
 *  @property background 背景
 *  @property screenRatio bottom sheet佔整個螢幕的比例
 */
data class BottomSheetSceneConfig(
    val titleConfig : PocketTextConfig = PocketTextConfig(),
    val shape: RoundedCornerShape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
    val background: Color = color_333333,
    val screenRatio: Float = 0.5f,
)


/**
 *  @sample BottomSheetSceneComponent
 *
 *  @param config bottom sheet的設定檔
 *  @param content compose內容
 *  @param onDismiss export dismiss 事件
 */

@Composable
fun BottomSheetSceneComponent(
    modifier: Modifier = Modifier,
    config: BottomSheetSceneConfig = BottomSheetSceneConfig(),
    content: @Composable ColumnScope.() -> Unit,
    onDismiss: () -> Unit
) {
    Surface(
        modifier = modifier
            .clip(shape = config.shape)
            .background(config.background),
        shadowElevation = 16.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(tween(100))
                .screenHeight(config.screenRatio)
                .background(config.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .ratioHeight(44.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PocketText(
                    config = config.titleConfig
                )
                PocketIconButton(
                    modifier = Modifier.size(24.dp),
                    imageVector = Icons.Default.Close,
                    tint = Color.White,
                    iconSize = 24.dp,
                    clickableConfig = ClickableConfig(),
                    onIconClick = {
                        onDismiss.invoke()
                    }
                )
            }

            content.invoke(this)
        }

    }
}

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

