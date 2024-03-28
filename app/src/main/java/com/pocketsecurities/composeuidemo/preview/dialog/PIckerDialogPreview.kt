package com.pocketsecurities.composeuidemo.preview.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pocketsecurities.pocketcomposecomponent.component.dialog.NumberPickerDialog

@Preview
@Composable
private fun NumberPickerDialogPreview() {
    NumberPickerDialog(
        state = 0,
        onValueChanged = {

        },
        onConfirmed = {

        },
        onDismiss = {

        }
    )
}