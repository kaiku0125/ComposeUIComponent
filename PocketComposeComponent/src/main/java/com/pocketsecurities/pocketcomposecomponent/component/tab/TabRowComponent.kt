package com.pocketsecurities.pocketcomposecomponent.component.tab

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.pocketsecurities.pocketcomposecomponent.color_292929
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import kotlinx.coroutines.launch

/**
 * @sample TabRowComponent 基本tab row
 */
@Composable
fun TabRowComponent(
    modifier: Modifier = Modifier,
    selectedItemIndex: Int,
    items: List<String>,
    tabWidth: Dp = 100.dp,
    shape: Shape = RoundedCornerShape(8.dp),
    textStyle: TextStyle = TextStyle.Default,
    onClick: (index: Int) -> Unit,
) {
    val unSelectedColor = Color.White

    val indicatorOffset: Dp by animateDpAsState(
        targetValue = tabWidth * selectedItemIndex,
        animationSpec = tween(
            easing = LinearEasing,
            durationMillis = 200
        ),
        label = "",
    )

    Box(
        modifier = modifier
            .clip(shape)
            .background(Color.DarkGray)
            .height(intrinsicSize = IntrinsicSize.Min)
    ) {
        TabIndicator(
            indicatorWidth = tabWidth,
            indicatorOffset = indicatorOffset,
            indicatorColor = Color.White,
            shape = shape
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(shape)
                .fillMaxHeight(),
        ) {
            items.mapIndexed { index, text ->
                val isSelected = index == selectedItemIndex
                TabItem(
                    modifier = Modifier.fillMaxHeight(),
                    config = TabItemConfig(
                        isSelected = isSelected,
                        shape = shape,
                        tabWidth = tabWidth,
                        selectedTextColor = Color.Black,
                        textConfig = PocketTextConfig(
                            value = text,
                            style = textStyle,
                            textColor = unSelectedColor
                        )
                    ),
                    onClick = {
                        onClick.invoke(index)
                    }
                )
            }
        }
    }
}

/**
 * @sample MaxWidthTabRowComponent 填滿整個width的 tabRow
 */
@Composable
fun MaxWidthTabRowComponent(
    selectedItemIndex: Int,
    items: List<String>,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    textStyle: TextStyle = TextStyle.Default,
    onClick: (index: Int) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    var wholeWidth by remember { mutableStateOf(0.dp) }
    val tabWidth = wholeWidth / items.size
    val unSelectedColor = Color.White

    val calculate = remember<(LayoutCoordinates) -> Unit> {
        {
            scope.launch {
                wholeWidth = with(density) {
                    it.size.width.toDp()
                }
            }
        }

    }


    val indicatorOffset: Dp by animateDpAsState(
        targetValue = tabWidth * selectedItemIndex,
        animationSpec = tween(
            easing = LinearEasing,
            durationMillis = 200
        ),
        label = "",
    )

    Box(
        modifier = modifier
            .clip(shape)
            .background(color_292929)
            .height(intrinsicSize = IntrinsicSize.Min)
            .fillMaxWidth()
            .onGloballyPositioned {
                calculate.invoke(it)
            },
    ) {
        TabIndicator(
            indicatorWidth = tabWidth,
            indicatorOffset = indicatorOffset,
            indicatorColor = Color.White,
            shape = shape
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .clip(shape)
                .fillMaxHeight(),
        ) {
            items.mapIndexed { index, text ->
                val isSelected = index == selectedItemIndex
                TabItem(
                    modifier = Modifier.fillMaxHeight(),
                    config = TabItemConfig(
                        isSelected = isSelected,
                        shape = shape,
                        tabWidth = tabWidth,
                        selectedTextColor = Color.Black,
                        textConfig = PocketTextConfig(
                            value = text,
                            style = textStyle,
                            textColor = unSelectedColor
                        )
                    ),
                    onClick = {
                        onClick.invoke(index)
                    }
                )
            }
        }
    }


}

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
