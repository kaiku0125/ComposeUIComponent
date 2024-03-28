package com.pocketsecurities.composeuidemo.preview.button

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composeuidemo.ui.theme.ComposeUIDemoTheme
import com.pocketsecurities.pocketcomposecomponent.component.button.PocketIconButton

@Preview
@Composable
private fun PocketIconButtonPreview() {
    ComposeUIDemoTheme {
        PocketIconButton(
            modifier = Modifier.size(24.dp),
            imageVector = Icons.Default.KeyboardArrowDown,
            tint = Color.White,
            iconSize = 17.dp,
            onIconClick = {

            }
        )
    }
}