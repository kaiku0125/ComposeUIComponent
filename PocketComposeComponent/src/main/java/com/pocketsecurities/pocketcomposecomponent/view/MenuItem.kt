package com.pocketsecurities.pocketcomposecomponent.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pocketsecurities.pocketcomposecomponent.R
import com.pocketsecurities.pocketcomposecomponent.color_717071
import com.pocketsecurities.pocketcomposecomponent.component.button.PocketIconButton
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketText
import com.pocketsecurities.pocketcomposecomponent.component.text.PocketTextConfig
import com.pocketsecurities.pocketcomposecomponent.component.text.TextWithIcon
import com.pocketsecurities.pocketcomposecomponent.component.text.TextWithIconConfig
import com.pocketsecurities.pocketcomposecomponent.extension.clickableEffectConfig


// 不需要dash的BaseMenuItem
@Composable
fun MenuItem(
    modifier: Modifier = Modifier,
    titleConfig: PocketTextConfig = PocketTextConfig(),
    badgeType: Int,
    @DrawableRes drawableRes: Int,
    onClick: (() -> Unit)? = null
) {
    BaseMenuItem(
        modifier = modifier,
        titleConfig = titleConfig,
        menuRes = drawableRes,
        onClick = onClick,
        badgeType = badgeType,
        withDash = false
    )
}

@Composable
fun BaseMenuItem(
    modifier: Modifier = Modifier,
    titleConfig: PocketTextConfig = PocketTextConfig(),
    @DrawableRes menuRes: Int,
    badgeType: Int = 0,
    withDash: Boolean = true,
    @DrawableRes deleteRes: Int? = null,
    onClick: (() -> Unit)? = null
) {
    BoxWithDash(
        modifier = modifier.clickableEffectConfig {
            onClick?.invoke()
        },
        withDash = withDash,
        content = {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (menuIcon, delete) = createRefs()

                TextWithIcon(
                    modifier = Modifier.constrainAs(menuIcon) {
                        centerTo(parent)
                    },
                    topConfig = TextWithIconConfig(
                        drawableRes = menuRes,
                        drawableSize = 30.dp,
                        paddingToText = 3.dp,
                        onClick = onClick
                    ),
                    content = {
                        PocketText(
                            config = titleConfig
                        )
                    }
                )

                deleteRes?.let { mDeleteRes ->
                    PocketIconButton(
                        modifier = Modifier
                            .size(14.dp)
                            .constrainAs(delete) {
                                end.linkTo(parent.end, margin = 5.dp)
                                top.linkTo(parent.top, margin = 5.dp)
                            },
                        drawableRes = mDeleteRes,
                        onIconClick = {
                            onClick?.invoke()
                        }
                    )
                }

                val badgeRes = when (badgeType) {
                    1 -> {
                        R.drawable.pocket_home_menu_badge_1
                    }

                    2 -> {
                        R.drawable.pocket_home_menu_badge_2
                    }

                    else -> {
                        null
                    }
                }

                badgeRes?.let {
                    Image(
                        painter = painterResource(id = it),
                        modifier = Modifier
                            .width(32.dp)
                            .height(12.dp)
                            .constrainAs(delete) {
                                end.linkTo(parent.end, margin = 5.dp)
                                top.linkTo(parent.top, margin = 5.dp)
                            },
                        contentDescription = ""
                    )
                }
            }
        }
    )
}

@Composable
fun MenuItemBlank(
    modifier: Modifier = Modifier,
    isActive: Boolean = true
) {
    BoxWithDash(
        modifier = modifier,
        content = {
            Icon(
                imageVector = Icons.Default.Add,
                tint = if (isActive) {
                    Color.White
                } else {
                    color_717071
                },
                contentDescription = ""
            )
        }
    )
}


@Composable
fun BoxWithDash(
    modifier: Modifier = Modifier,
    withDash: Boolean = true,
    content: @Composable () -> Unit = {
        PocketText(
            config = PocketTextConfig(
                value = "BTC",
                alignment = Alignment.Center,
                textColor = color_717071
            )
        )
    }
) {
    val stroke = Stroke(
        width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .aspectRatio(1f)
            .drawBehind {
                if (withDash) {
                    drawRoundRect(
                        color = Color.White,
                        style = stroke,
                        cornerRadius = CornerRadius(8.dp.toPx(), 8.dp.toPx())
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        content.invoke()
    }
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
private fun BoxWithDashPreview() {
    BoxWithDash(
        modifier = Modifier.size(70.dp)
    )
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
private fun MenuItemDashPreview() {
    BaseMenuItem(
        modifier = Modifier.size(70.dp),
        menuRes = R.drawable.pocket_shortcut_condition_order,
        titleConfig = PocketTextConfig(
            value = "空吧哇"
        ),
        badgeType = 1
    )
}

@Preview(backgroundColor = 0xFFFFFFFF)
@Composable
private fun MenuItemBlankPreview() {
    MenuItemBlank(
        modifier = Modifier.size(70.dp),
        isActive = true
    )
}