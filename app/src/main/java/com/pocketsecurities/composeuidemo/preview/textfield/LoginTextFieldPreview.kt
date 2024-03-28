package com.pocketsecurities.composeuidemo.preview.textfield

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.component.textfield.LoginTextField
import com.pocketsecurities.pocketcomposecomponent.component.textfield.LoginTextFieldConfig
import com.pocketsecurities.pocketcomposecomponent.component.textfield.data.TextFieldConfig

@Preview
@Composable
private fun LoginTextFieldPreview() {
    LoginTextField(
        modifier = Modifier.height(45.dp),
        loginTextFieldConfig = LoginTextFieldConfig(
            text = TextFieldConfig(
                value = "BTC TO THE MOON"
            ),
            hint = PocketTextConfig(
                value = "ADA FOREVER"
            ),
        ),
        onTextChange = {

        },
        onFocusChange = {

        }
    )
}