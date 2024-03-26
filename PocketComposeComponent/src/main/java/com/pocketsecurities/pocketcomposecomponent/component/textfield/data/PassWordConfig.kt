package com.pocketsecurities.pocketcomposecomponent.component.textfield.data

import androidx.annotation.DrawableRes
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.pocketsecurities.pocketcomposecomponent.R

data class PassWordConfig(
    val needPasswordSecurity: Boolean = false,
    @DrawableRes val inVisualDrawable: Int = R.drawable.ic_eye_close,
    @DrawableRes val visualDrawable: Int = R.drawable.ic_eye_alt,
    val pwdVisualTransformation: VisualTransformation = PasswordVisualTransformation()
)
