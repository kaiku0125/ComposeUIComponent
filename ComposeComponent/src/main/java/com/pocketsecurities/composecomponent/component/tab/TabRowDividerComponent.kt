package com.pocketsecurities.composecomponent.component.tab

import androidx.annotation.ColorRes
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.pocketsecurities.composecomponent.color_1E1E1E
import com.pocketsecurities.composecomponent.color_414141
import com.pocketsecurities.composecomponent.color_484848
import com.pocketsecurities.composecomponent.component.tab.data.TabType
import com.pocketsecurities.composecomponent.component.text.PocketTextConfig
import com.pocketsecurities.composecomponent.extension.clipByShape
import com.pocketsecurities.composecomponent.extension.ratioHeight
import kotlinx.coroutines.launch

/**
 * @sample TabRowDividerComponent 擁有分割線的tab row
 *
 * @param selectedItemIndex
 * @param items 使用 TabType 物件類型的 list (請實作TabType)
 * @param tabWidth 單一tab寬度
 * @param tabHeight 單一tab高度
 * @param textStyle tab文字樣式
 * @param indicatorPadding 顯示指標的padding
 * @param border tabRow的外框
 * @param indicatorColor 顯示指標的顏色
 * @param selectedTextColor 選擇時的文字顏色
 * @param unselectedTextColor 未選擇時的文字顏色
 * @param componentBackground 元件背景顏色
 * @param onClick export 點擊事件
 */
@Composable
fun TabRowDividerComponent(
    modifier: Modifier = Modifier,
    selectedItemIndex: Int,
    items: List<TabType>,
    tabWidth: Dp = 45.dp,
    tabHeight: Dp = 30.dp,
    textStyle: TextStyle = TextStyle.Default,
    shape: Shape = RoundedCornerShape(8.dp),
    indicatorPadding: Dp = 0.dp,
    border: BorderStroke = BorderStroke(1.dp, color_414141),
    @ColorRes indicatorColor: Color = Color.White,
    @ColorRes selectedTextColor: Color = Color.Black,
    @ColorRes unselectedTextColor: Color = Color.Gray,
    @ColorRes componentBackground: Color = color_1E1E1E,
    onClick: (TabType) -> Unit,
) {

    val indicatorOffset: Dp by animateDpAsState(
        targetValue = (tabWidth + 1.dp) * selectedItemIndex,
        animationSpec = tween(
            easing = LinearEasing,
            durationMillis = 200
        ),
        label = "",
    )

    Box(
        modifier = modifier
            .clipByShape(
                shape = shape,
                backgroundColor = componentBackground,
                border = border
            )
            .height(intrinsicSize = IntrinsicSize.Min)
    ) {
        TabIndicator(
            modifier = Modifier.padding(indicatorPadding),
            indicatorWidth = tabWidth - indicatorPadding * 2,
            indicatorOffset = indicatorOffset,
            indicatorColor = indicatorColor,
            shape = shape
        )

        Row(
            modifier = Modifier.ratioHeight(tabHeight),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, type ->
                Row {
                    if (index != 0) {
                        VerticalDivider(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(1.dp)
                                .padding(vertical = 3.dp),
                            color = color_484848
                        )
                    }
                    TabItem(
                        modifier = Modifier.fillMaxHeight(),
                        config = TabItemConfig(
                            isSelected = index == selectedItemIndex,
                            shape = shape,
                            tabWidth = tabWidth,
                            selectedTextColor = selectedTextColor,
                            textConfig = PocketTextConfig(
                                value = stringResource(id = type.description),
                                style = textStyle,
                                textColor = unselectedTextColor
                            )
                        ),
                        onClick = {
                            onClick.invoke(type)
                        }
                    )
                }

            }
        }
    }
}

@Composable
fun MaxWidthTabRowDividerComponent(
    modifier: Modifier = Modifier,
    selectedItemIndex: Int,
    items: List<TabType>,
    tabHeight: Dp = 30.dp,
    textStyle: TextStyle = TextStyle.Default,
    shape: Shape = RoundedCornerShape(8.dp),
    indicatorPadding: Dp = 0.dp,
    border: BorderStroke = BorderStroke(1.dp, color_414141),
    @ColorRes indicatorColor: Color = Color.White,
    @ColorRes selectedTextColor: Color = Color.Black,
    @ColorRes unselectedTextColor: Color = Color.Gray,
    @ColorRes componentBackground: Color = color_1E1E1E,
    onClick: (TabType) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    var wholeWidth by remember { mutableStateOf(20.dp) }
    val tabWidth = (wholeWidth - items.size * 1.dp) / items.size

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
        targetValue = (tabWidth + 1.dp) * selectedItemIndex,
        animationSpec = tween(
            easing = LinearEasing,
            durationMillis = 200
        ),
        label = "",
    )

    Surface(
        modifier = modifier
            .height(intrinsicSize = IntrinsicSize.Min)
            .onGloballyPositioned {
                calculate.invoke(it)
            },
        shape = shape,
        border = border,
        color = color_414141
    ) {
        Box(
            modifier = Modifier.background(componentBackground)
        ) {
            TabIndicator(
                modifier = Modifier.padding(indicatorPadding),
                indicatorWidth = tabWidth - indicatorPadding * 2,
                indicatorOffset = indicatorOffset,
                indicatorColor = indicatorColor,
                shape = shape
            )

            Row(
                modifier = Modifier
                    .clip(shape)
                    .ratioHeight(tabHeight),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                items.forEachIndexed { index, type ->
                    Row {
                        if (index != 0) {
                            VerticalDivider(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .padding(vertical = 3.dp),
                                color = color_484848
                            )
                        }
                        TabItem(
                            modifier = Modifier.fillMaxHeight(),
                            config = TabItemConfig(
                                isSelected = index == selectedItemIndex,
                                shape = shape,
                                tabWidth = tabWidth,
                                selectedTextColor = selectedTextColor,
                                textConfig = PocketTextConfig(
                                    value = stringResource(id = type.description),
                                    style = textStyle,
                                    textColor = unselectedTextColor
                                )
                            ),
                            onClick = {
                                onClick.invoke(type)
                            }
                        )
                    }

                }
            }
        }
    }
}