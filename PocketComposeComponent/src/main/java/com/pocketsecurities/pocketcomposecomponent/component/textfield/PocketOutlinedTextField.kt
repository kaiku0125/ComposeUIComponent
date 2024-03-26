package com.pocketsecurities.pocketcomposecomponent.component.textfield

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.twotone.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.R
import com.pocketsecurities.pocketcomposecomponent.color_252525
import com.pocketsecurities.pocketcomposecomponent.color_414141
import com.pocketsecurities.pocketcomposecomponent.color_e95356
import com.pocketsecurities.pocketcomposecomponent.color_primary
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig

@Composable
fun PocketOutlinedTextField(
    modifier: Modifier = Modifier,
    textField: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    placeHolderConfig: PocketTextConfig = PocketTextConfig(),
    needClearAll: Boolean = false,
    needPasswordSecurity: Boolean = false,
    hideKeyboard: Boolean,
    leadingIcon: @Composable (() -> Unit)? = null,
    leadingContentText: String? = "身分證",
    letterSpacing : TextUnit = TextUnit.Unspecified,
    onFocusChanged : (Boolean) -> Unit,
    onFocusClear: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    var passwordVisible by rememberSaveable { mutableStateOf(needPasswordSecurity) }

    var isFocus by remember{ mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier
            .height(53.dp)
            .fillMaxWidth()
            .onFocusChanged {
                isFocus = it.isFocused
                onFocusChanged.invoke(isFocus)
            },
        value = textField,
        onValueChange = {
            onValueChange.invoke(it)
        },
        label = null,
        placeholder = {
            PocketText(config = placeHolderConfig)
        },
        visualTransformation = if (passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        leadingIcon = {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(15.dp))
                leadingIcon?.invoke()
                Spacer(modifier = Modifier.width(3.dp))
                leadingContentText?.let {
                    PocketText(
                        modifier = Modifier.width(45.dp),
                        config = PocketTextConfig(
                            value = it,
                            textColor = color_e95356,
                            letterSpacing = letterSpacing
                        )
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                VerticalDivider(
                    modifier = Modifier
                        .height(35.dp)
                        .padding(horizontal = 6.dp)
                        .width(1.dp),
                    color = color_e95356,
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        },
        trailingIcon = {
            if (needClearAll) {
                if (textField.text.isNotEmpty())
                    IconButton(
                        modifier = Modifier.padding(end = 10.dp),
                        onClick = {
                            onValueChange.invoke(
                                TextFieldValue("")
                            )
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
            }
            if (needPasswordSecurity) {
                val icon =
                    if (passwordVisible) painterResource(id = R.drawable.ic_eye_close) else painterResource(
                        id = R.drawable.ic_eye_alt
                    )

                IconButton(
                    modifier = Modifier.padding(end = 10.dp),
                    onClick = {
                        passwordVisible = passwordVisible.not()
                    }
                ) {
                    Icon(
                        painter = icon,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor =  Color.White,
            disabledTextColor = Color.Transparent,
            focusedContainerColor = color_414141,
            unfocusedContainerColor = color_414141,
            disabledContainerColor = color_414141,
            focusedIndicatorColor = color_414141,
            unfocusedIndicatorColor = color_414141,
            disabledIndicatorColor = color_414141,
        ),
        shape = MaterialTheme.shapes.medium,
    )
    if (hideKeyboard) {
        focusManager.clearFocus()
        onFocusClear.invoke()
    }

}

@Preview
@Composable
private fun PocketTextFieldWithIconPreview() {
    PocketOutlinedTextField(
        textField = TextFieldValue(""),
        onValueChange = {

        },
        placeHolderConfig = PocketTextConfig(
            value = "請輸入您的CMoney帳號"
        ),
        needPasswordSecurity = true,
        hideKeyboard = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.TwoTone.Person,
                contentDescription = null,
                tint = color_primary
            )
        },
        onFocusChanged = {

        },
        onFocusClear = {

        },
    )
}