package com.pocketsecurities.pocketcomposecomponent.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.color_414141
import com.pocketsecurities.pocketcomposecomponent.component.button.PocketIconButton
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.component.textfield.data.LoginTextFieldConfig
import com.pocketsecurities.pocketcomposecomponent.component.textfield.data.PassWordConfig
import com.pocketsecurities.pocketcomposecomponent.component.textfield.data.TextFieldConfig

/**
 *  @sample LoginTextField 使用者輸入元件
 *
 *  @param loginTextFieldConfig 輸入相關 viewState
 *  @param pwdConfig 密碼相關 viewState
 *  @param onTextChange export 最新輸入
 *  @param onFocusChange export 焦點變化
 */

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    loginTextFieldConfig: LoginTextFieldConfig = LoginTextFieldConfig(),
    pwdConfig: PassWordConfig = PassWordConfig(),
    onTextChange: (String) -> Unit,
    onFocusChange: (Boolean) -> Unit
) {

    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val visualTransformation by remember(
        isPasswordVisible,
        pwdConfig.pwdVisualTransformation
    ) {
        derivedStateOf {
            if (pwdConfig.needPasswordSecurity && isPasswordVisible.not()) {
                pwdConfig.pwdVisualTransformation
            } else {
                VisualTransformation.None
            }
        }
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(
                color = color_414141,
                shape = RoundedCornerShape(8.dp)
            )
            .onFocusChanged {
                onFocusChange.invoke(it.isFocused)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(15.dp))

        loginTextFieldConfig.leadingIcon?.invoke()

        if (loginTextFieldConfig.title.value.isNotEmpty()) {
            Spacer(modifier = Modifier.width(3.dp))
            PocketText(
                modifier = Modifier.width(45.dp),
                config = loginTextFieldConfig.title
            )
        }
        Spacer(modifier = Modifier.width(10.dp))

        VerticalDivider(
            modifier = Modifier.height(30.dp).padding(horizontal = 6.dp),
            thickness = 1.dp,
            color = loginTextFieldConfig.dividerColor,
        )

        Spacer(modifier = Modifier.width(10.dp))

        PocketTextFieldComponent(
            modifier = Modifier.weight(1f),
            value = loginTextFieldConfig.text.value,
            valueAlignment = loginTextFieldConfig.text.alignment,
            valueStyle = loginTextFieldConfig.text.style,
            hintConfig = loginTextFieldConfig.hint,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = visualTransformation,
            onFocusChange = {

            },
            onTextChange = {
                onTextChange.invoke(it)
            }
        )

        if (pwdConfig.needPasswordSecurity) {
            Spacer(modifier = Modifier.size(5.dp))
            PocketIconButton(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .size(25.dp),
                iconSize = 25.dp,
                drawableRes = if (isPasswordVisible) {
                    pwdConfig.visualDrawable
                } else {
                    pwdConfig.inVisualDrawable
                },
                onIconClick = {
                    isPasswordVisible = isPasswordVisible.not()
                }
            )
        }

    }
}

@Preview
@Composable
private fun LoginTextFieldPreview() {
    LoginTextField(
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