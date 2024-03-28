package com.pocketsecurities.composecomponent.component.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composecomponent.color_9e9e9f
import com.pocketsecurities.composecomponent.component.text.PocketText
import com.pocketsecurities.composecomponent.component.text.PocketTextConfig
import com.pocketsecurities.composecomponent.extension.ratioHeight

@Composable
fun PocketPrimaryButton(
    modifier: Modifier = Modifier,
    config: PocketTextConfig = PocketTextConfig(),
    isEnable: Boolean = true,
    height: Dp? = null,
    primaryColor: Color = MaterialTheme.colorScheme.primary,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit
) {

    val mModifier = if (height != null) {
        modifier.ratioHeight(height)
    } else {
        modifier
    }

    val color = if (isEnable) {
        ButtonDefaults.buttonColors(
            containerColor = primaryColor,
            contentColor = Color.White
        )
    } else {
        ButtonDefaults.buttonColors(
            containerColor = color_9e9e9f,
            contentColor = Color.White
        )
    }

    Button(
        modifier = mModifier,
        colors = color,
        shape = RoundedCornerShape(8.dp),
        contentPadding = contentPadding,
        onClick = {
            if (isEnable) {
                onClick.invoke()
            }
        },
    ) {
        PocketText(
            config = config
        )
    }
}