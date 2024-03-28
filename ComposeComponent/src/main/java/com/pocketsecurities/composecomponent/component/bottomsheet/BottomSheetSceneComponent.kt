package com.pocketsecurities.composecomponent.component.bottomsheet

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
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composecomponent.color_333333
import com.pocketsecurities.composecomponent.component.button.PocketIconButton
import com.pocketsecurities.composecomponent.component.text.PocketText
import com.pocketsecurities.composecomponent.component.text.PocketTextConfig
import com.pocketsecurities.composecomponent.extension.ClickableConfig
import com.pocketsecurities.composecomponent.extension.ratioHeight
import com.pocketsecurities.composecomponent.extension.screenHeight

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
 *  @sample BottomSheetSceneComponent 帶有標題與關閉元件的bottom sheet
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