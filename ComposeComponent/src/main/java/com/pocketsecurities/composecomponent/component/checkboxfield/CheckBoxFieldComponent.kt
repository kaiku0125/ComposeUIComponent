package com.pocketsecurities.composecomponent.component.checkboxfield

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composecomponent.color_717071
import com.pocketsecurities.composecomponent.component.text.PocketText
import com.pocketsecurities.composecomponent.component.text.PocketTextConfig
import com.pocketsecurities.composecomponent.component.text.PocketTextWithHint
import com.pocketsecurities.composecomponent.extension.ClickableConfig
import com.pocketsecurities.composecomponent.extension.clickableEffectConfig

/**
 *  @sample
 *
 *  @property isEnable 是否啟用
 *  @property isChecked 是否勾選
 *  @property paddingCheckBoxToText CheckBox與文字間距
 *  @property contentTextConfig 內容文字設定
 *  @property hintTextConfig 提示文字設定
 */

data class CheckBoxFieldConfig(
    val isEnable: Boolean = true,
    val isChecked: Boolean = false,
    val paddingCheckBoxToText: Dp = 10.dp,
    val contentTextConfig: PocketTextConfig = PocketTextConfig(),
    val hintTextConfig: PocketTextConfig = PocketTextConfig()
)


/**
 *  @sample
 *
 *  @param cbConfig CheckBox設定檔
 *  @param clickableConfig 點擊設定檔
 *  @param onCheckChanged export 勾選事件
 *  @param onFieldClick export 整條點擊事件
 */

@Composable
fun CheckBoxFieldComponent(
    modifier: Modifier = Modifier,
    cbConfig: CheckBoxFieldConfig = CheckBoxFieldConfig(),
    clickableConfig: ClickableConfig = ClickableConfig(),
    onCheckChanged: () -> Unit,
    onFieldClick: () -> Unit
) {

    Row(
        modifier = modifier
            .clickableEffectConfig(
                config = clickableConfig,
                onClick = onFieldClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = if (cbConfig.isEnable) cbConfig.isChecked else false,
            onCheckedChange = {
                onCheckChanged.invoke()
            },
            modifier = Modifier.size(24.dp),
            enabled = cbConfig.isEnable,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colorScheme.primary,
                uncheckedColor = Color.White,
                disabledCheckedColor = color_717071
            )
        )

        Spacer(modifier = Modifier.width(cbConfig.paddingCheckBoxToText))

        if (cbConfig.hintTextConfig.value.isNotEmpty()) {
            PocketTextWithHint(
                contentConfig = cbConfig.contentTextConfig,
                hintConfig = cbConfig.hintTextConfig
            )
        } else {
            PocketText(
                config = cbConfig.contentTextConfig
            )
        }

    }
}

