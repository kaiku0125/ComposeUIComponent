package com.pocketsecurities.composecomponent.component.tab


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
fun TabIndicator(
    modifier: Modifier = Modifier,
    indicatorWidth: Dp,
    indicatorOffset: Dp,
    indicatorColor: Color,
    shape : Shape
) {
    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(width = indicatorWidth)
            .offset(x = indicatorOffset)
            .clip(shape = shape)
            .background(color = indicatorColor),
    )
}