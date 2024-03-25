package com.pocketsecurities.pocketcomposecomponent.component.checkboxfield

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.color_717071
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextWithHint
import com.pocketsecurities.pocketcomposecomponent.extension.ClickableConfig
import com.pocketsecurities.pocketcomposecomponent.extension.clickableEffectConfig

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

@Preview
@Composable
private fun CheckBoxFieldComponentPreview() {
    CheckBoxFieldComponent(
        cbConfig = CheckBoxFieldConfig(
            isChecked = true,
            contentTextConfig = PocketTextConfig(
                value = "54879487"
            ),
        ),
        onCheckChanged = {

        },
        onFieldClick = {

        }
    )
}

@Preview
@Composable
private fun CheckBoxFieldComponentWithHintPreview() {
    val hintText = "000.."

    CheckBoxFieldComponent(
        cbConfig = CheckBoxFieldConfig(
            isChecked = true,
            contentTextConfig = PocketTextConfig(
                value = "54879487",
                alignment = Alignment.CenterStart
            ),
            hintTextConfig = PocketTextConfig(
                value = hintText,
                alignment = Alignment.CenterStart
            )
        ),
        onCheckChanged = {

        },
        onFieldClick = {

        }
    )
}
