package com.pocketsecurities.composeuidemo.preview.checkbox

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.pocketsecurities.pocketcomposecomponent.component.checkboxfield.CheckBoxFieldComponent
import com.pocketsecurities.pocketcomposecomponent.component.checkboxfield.CheckBoxFieldConfig
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig

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
