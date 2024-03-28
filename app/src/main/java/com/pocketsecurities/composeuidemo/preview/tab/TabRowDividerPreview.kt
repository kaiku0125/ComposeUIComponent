package com.pocketsecurities.composeuidemo.preview.tab

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composecomponent.R
import com.pocketsecurities.composecomponent.color_333333
import com.pocketsecurities.composecomponent.color_484848
import com.pocketsecurities.composecomponent.color_9e9e9f
import com.pocketsecurities.composecomponent.component.tab.MaxWidthTabRowDividerComponent
import com.pocketsecurities.composecomponent.component.tab.TabRowDividerComponent
import com.pocketsecurities.composecomponent.component.tab.data.TabType

@Preview
@Composable
private fun MaxWidthTabRowDividerComponentPreview() {
    var selectedItemIndex by remember { mutableIntStateOf(0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TabRowDividerComponent(
            selectedItemIndex = selectedItemIndex,
            items = CryptoTabType.getAll(),
            tabWidth = 100.dp,
            tabHeight = 50.dp,
            indicatorColor = color_484848,
            indicatorPadding = 5.dp,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            selectedTextColor = Color.White,
            unselectedTextColor = color_9e9e9f,
            onClick = {
                selectedItemIndex = it.position
            }
        )

        MaxWidthTabRowDividerComponent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            selectedItemIndex = selectedItemIndex,
            items = CryptoTabType.getAll(),
            tabHeight = 40.dp,
            indicatorColor = color_484848,
            indicatorPadding = 2.dp,
            border = BorderStroke(1.dp, color_333333),
            selectedTextColor = Color.White,
            unselectedTextColor = color_9e9e9f,
            onClick = {
                selectedItemIndex = it.position
            }
        )
    }

}

internal sealed class CryptoTabType(
    override val position: Int,
    @StringRes override val description: Int,
    override val tag: String,
) : TabType {

    data object SpotTab : CryptoTabType(
        position = 0,
        description = R.string.tab_spot,
        tag = "現貨",
    )

    data object PerpetualTab : CryptoTabType(
        position = 1,
        description = R.string.tab_perpetual,
        tag = "合約",
    )

    data object GridBotTab : CryptoTabType(
        position = 2,
        description = R.string.tab_grid,
        tag = "網格機器人",
    )

    companion object {

        fun getAll(): List<CryptoTabType> {
            return listOf(
                SpotTab,
                PerpetualTab,
                GridBotTab
            )
        }
    }
}