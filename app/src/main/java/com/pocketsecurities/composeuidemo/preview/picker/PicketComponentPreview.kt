package com.pocketsecurities.composeuidemo.preview.picker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.pocketsecurities.pocketcomposecomponent.component.picker.PickerComponent

@Preview
@Composable
private fun NumberPickerPreview() {
    var state by remember { mutableIntStateOf(0) }
    PickerComponent(
        value = state,
        range = -10..10,
        textStyle = TextStyle(Color.White),
        onValueChange = {
            state = it
        },
    )
}