package com.pocketsecurities.composecomponent.component.radio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composecomponent.extension.ClickableConfig
import com.pocketsecurities.composecomponent.extension.clickableEffectConfig

/**
 * @sample LeadingRadioSelectedComponent 帶有前導radio的選擇元件
 *
 * @param isChecked 是否勾選
 * @param isEnabled 是否可被選擇
 * @param background 背景顏色
 * @param horizontalPadding 水平padding
 * @param paddingToText radio元件與顯示內容間距
 * @param clickableConfig 點擊效果設定
 * @param onFieldClick 區域被點擊
 * @param content 顯示內容
 */
@Composable
fun LeadingRadioSelectedComponent(
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
    isEnabled: Boolean,
    background: Color = MaterialTheme.colorScheme.background,
    horizontalPadding: Dp = 16.dp,
    paddingToText: Dp = 5.dp,
    clickableConfig: ClickableConfig = ClickableConfig(),
    onFieldClick : () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .background(background)
            .clickableEffectConfig(
                config = clickableConfig,
                onClick = {
                    if (isEnabled) {
                        onFieldClick.invoke()
                    }
                }
            )
            .padding(horizontal = horizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PocketRadioIconComponent(
            modifier = Modifier.padding(top = 1.dp), // 不確定為何會有1.dp的跑版
            isChecked = isChecked,
            isEnabled = isEnabled,
            iconSize = 24.dp
        )

        Spacer(modifier = Modifier.width(paddingToText))

        content.invoke(this)
    }
}

