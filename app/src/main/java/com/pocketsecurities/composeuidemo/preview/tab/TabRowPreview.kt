package com.pocketsecurities.composeuidemo.preview.tab

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pocketsecurities.composecomponent.component.tab.MaxWidthTabRowComponent
import com.pocketsecurities.composecomponent.component.tab.TabRowComponent

@Preview
@Composable
private fun CustomTabFillMaxWidthPreview() {

    var index by remember { mutableIntStateOf(0) }

    MaxWidthTabRowComponent(
        modifier = Modifier.fillMaxWidth().height(50.dp),
        selectedItemIndex = index,
        items = listOf("一般單", "觸價單"),
        onClick = {
            index = it
        }
    )
}

@Preview
@Composable
private fun PocketTabPreview() {

    var index by remember { mutableIntStateOf(0) }

    TabRowComponent(
        modifier = Modifier.height(45.dp),
        selectedItemIndex = index,
        items = listOf("一般單", "觸價單")
    ) {
        index = it
    }
}