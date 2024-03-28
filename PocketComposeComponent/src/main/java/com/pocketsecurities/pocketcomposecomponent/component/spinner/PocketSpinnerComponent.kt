package com.pocketsecurities.pocketcomposecomponent.component.spinner

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pocketsecurities.pocketcomposecomponent.color_1E1E1E
import com.pocketsecurities.pocketcomposecomponent.component.button.PocketRotationIconButton
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.extension.ClickableConfig
import com.pocketsecurities.pocketcomposecomponent.extension.clickableEffectConfig
import com.pocketsecurities.pocketcomposecomponent.extension.clipByShape
import kotlinx.coroutines.launch


@Composable
fun PocketSpinnerComponent(
    modifier: Modifier,
    list: List<SpinnerType>,
    chosenTextConfig: PocketTextConfig = PocketTextConfig(),
    isEnable: Boolean = true,
    shape: Shape = RoundedCornerShape(8.dp),
    itemHeight: Dp = 30.dp,
    onTypeChange: (SpinnerType) -> Unit
) {
    val scope = rememberCoroutineScope()
    val density = LocalDensity.current
    var spinnerWidth by remember { mutableStateOf(0.dp) }

    val popupState = remember { PopupState(false) }

    val onSpinnerClick: () -> Unit = {
        popupState.isVisible = popupState.isVisible.not()
    }

    val calculate: (LayoutCoordinates) -> Unit = remember {
        {
            scope.launch {
                spinnerWidth = with(density) {
                    it.size.width.toDp()
                }
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .clipByShape(
                shape = shape,
                border = BorderStroke(1.dp, Color.White),
                backgroundColor = color_1E1E1E
            )
            .clickableEffectConfig(
                onClick = {
                    onSpinnerClick.invoke()
                }
            )
            .onGloballyPositioned {
                calculate.invoke(it)
            },
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (text, icon) = createRefs()

            PocketText(
                modifier = Modifier.constrainAs(text) {
                    start.linkTo(parent.start, margin = 12.dp)
                    centerVerticallyTo(parent)
                },
                config = chosenTextConfig
            )

            if (isEnable) {
                PocketRotationIconButton(
                    modifier = Modifier.constrainAs(icon) {
                        end.linkTo(parent.end, margin = 12.dp)
                        centerVerticallyTo(parent)
                    },
                    tint = MaterialTheme.colorScheme.onBackground,
                    iconSize = 24.dp,
                    isExpand = popupState.isVisible,
                    clickableConfig = ClickableConfig(),
                    onIconClick = {
                        onSpinnerClick.invoke()
                    }
                )
            }
        }

        PocketPopup(
            modifier = Modifier
                .width(spinnerWidth)
                .background(
                    color = color_1E1E1E,
                    shape = shape
                )
                .clip(shape)
                .border(1.dp, Color.White, shape),
            popupState = popupState,
            onDismissRequest = {
                popupState.isVisible = false
            },
            content = {
                list.forEachIndexed { index, type ->
                    val isLast = index == list.lastIndex
                    val height = if (isLast) (itemHeight + 6.dp) else (itemHeight + 3.dp)
                    DropdownMenuItem(
                        modifier = Modifier.height(height),
                        text = {
                            Column {
                                Spacer(modifier = Modifier.height(3.dp))
                                PocketText(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(itemHeight),
                                    config = PocketTextConfig(
                                        value = stringResource(id = type.description),
                                        alignment = Alignment.CenterStart,
                                        style = chosenTextConfig.style,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                )
                                if (isLast) {
                                    Spacer(modifier = Modifier.height(3.dp))
                                }
                            }
                        },
                        onClick = {
                            onSpinnerClick.invoke()
                            onTypeChange.invoke(type)
                        }
                    )
                }
            }
        )
    }
}