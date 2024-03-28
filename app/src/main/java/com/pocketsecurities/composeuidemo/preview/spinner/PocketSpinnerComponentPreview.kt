package com.pocketsecurities.composeuidemo.preview.spinner

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composeuidemo.ui.theme.DarkComposeUIDemoTheme
import com.pocketsecurities.composecomponent.R
import com.pocketsecurities.composecomponent.component.spinner.PocketSpinnerComponent
import com.pocketsecurities.composecomponent.component.spinner.SpinnerType
import com.pocketsecurities.composecomponent.component.text.PocketText
import com.pocketsecurities.composecomponent.component.text.PocketTextConfig

@Preview
@Composable
private fun PocketSpinnerComponentPreview() {
    var currentType by remember {
        mutableStateOf<SpinnerType>(SpinnerPreviewType.SPOT)
    }

    DarkComposeUIDemoTheme {
        PocketSpinnerComponent(
            modifier = Modifier,
            list = SpinnerPreviewType.getAll(),
            chosenTextConfig = PocketTextConfig(
                value = stringResource(id = currentType.description),
                style = TextStyle.Default
            ),

            onTypeChange = {
                currentType = SpinnerPreviewType.find(it.position)
            }
        )
    }
}

@Preview
@Composable
private fun DropdownMenuItemPreview() {
    DarkComposeUIDemoTheme {
        DropdownMenuItem(
            modifier = Modifier.height(32.dp),
            text = {
                PocketText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 3.dp),
                    config = PocketTextConfig(
                        value = "DropdownMenuItemPreview",
                        alignment = Alignment.CenterStart,
                        style = TextStyle.Default,
                        overflow = TextOverflow.Ellipsis
                    )
                )
            },
            onClick = {

            }
        )

    }
}

sealed class SpinnerPreviewType(
    override val position: Int,
    @StringRes override val description: Int,
    override val tag: String
) : SpinnerType {
    data object SPOT : SpinnerPreviewType(
        position = 0,
        description = R.string.tab_spot,
        tag = TAB_SPOT
    )

    data object PERPETUAL : SpinnerPreviewType(
        position = 1,
        description = R.string.tab_perpetual,
        tag = TAB_PERPETUAL
    )


    companion object {
        private const val TAB_SPOT = "現貨"
        private const val TAB_PERPETUAL = "合約"

        fun getAll(): List<SpinnerPreviewType> {
            return listOf(
                SPOT,
                PERPETUAL
            )
        }

        fun find(position: Int): SpinnerType {
            return when (position) {
                0 -> SPOT
                1 -> PERPETUAL
                else -> SPOT
            }
        }
    }


}