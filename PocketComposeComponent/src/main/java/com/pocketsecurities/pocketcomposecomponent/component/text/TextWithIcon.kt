package com.pocketsecurities.pocketcomposecomponent.component.text


import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Shape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.pocketsecurities.pocketcomposecomponent.R
import com.pocketsecurities.pocketcomposecomponent.component.button.PocketIconButton
import com.pocketsecurities.pocketcomposecomponent.extension.ClickableConfig
import com.pocketsecurities.pocketcomposecomponent.extension.clickableEffectConfig

data class TextWithIconConfig(
    @DrawableRes val drawableRes: Int? = null,
    val drawableSize: Dp = 17.dp,
    val drawableColor: Color = Color.Unspecified,
    val paddingToText: Dp = 5.dp,
    val shape: Shape = RoundedCornerShape(0.dp),
    val clickableConfig: ClickableConfig = ClickableConfig(needHaptic = true),
    val onClick: (() -> Unit)? = null
)

/**
 * @sample TextWithIcon Text 與 Icon 組合元件
 *
 * @param topConfig 加入上方icon
 * @param startConfig 加入起始icon
 * @param bottomConfig 加入底部icon
 * @param endConfig 加入尾部icon
 * @param content 放入中間的text
 */

@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier,
    topConfig: TextWithIconConfig? = null,
    startConfig: TextWithIconConfig? = null,
    bottomConfig: TextWithIconConfig? = null,
    endConfig: TextWithIconConfig? = null,
    content: @Composable () -> Unit,
) {

    ConstraintLayout(
        modifier = modifier
    ) {
        val (refImgStart, refImgTop, refImgBottom, refImgEnd, refContent) = createRefs()
        Box(
            modifier = Modifier.constrainAs(refContent) {
                top.linkTo(topConfig?.drawableRes?.let { refImgTop.bottom } ?: parent.top)
                bottom.linkTo(bottomConfig?.drawableRes?.let { refImgBottom.top } ?: parent.bottom)
                start.linkTo(startConfig?.drawableRes?.let { refImgStart.end } ?: parent.start)
                end.linkTo(endConfig?.drawableRes?.let { refImgEnd.start } ?: parent.end)
            },
            contentAlignment = Alignment.Center
        ) {
            content()
        }

        topConfig?.let {
            IconContent(
                modifier = Modifier
                    .constrainAs(refImgTop) {
                        top.linkTo(parent.top)
                        start.linkTo(refContent.start)
                        end.linkTo(refContent.end)
                    }
                    .padding(bottom = it.paddingToText),
                vs = it
            )
        }

        startConfig?.let {
            IconContent(
                modifier = Modifier
                    .constrainAs(refImgStart) {
                        top.linkTo(refContent.top)
                        bottom.linkTo(refContent.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(end = it.paddingToText),
                vs = it,
            )
        }

        bottomConfig?.let {
            IconContent(
                modifier = Modifier
                    .constrainAs(refImgBottom) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(refContent.start)
                        end.linkTo(refContent.end)
                    }
                    .padding(top = it.paddingToText),
                vs = it,
            )
        }

        endConfig?.let {
            IconContent(
                modifier = Modifier
                    .constrainAs(refImgEnd) {
                        top.linkTo(refContent.top)
                        bottom.linkTo(refContent.bottom)
                        end.linkTo(parent.end)
                    }
                    .padding(start = it.paddingToText),
                vs = it,
            )
        }
    }
}

@Composable
private fun IconContent(
    modifier: Modifier,
    vs: TextWithIconConfig
) {
    if (vs.onClick != null) {
        vs.drawableRes?.let {
            PocketIconButton(
                modifier = modifier.size(vs.drawableSize),
                drawableRes = it,
                tint = vs.drawableColor,
                iconSize = vs.drawableSize,
                shape = vs.shape,
                clickableConfig = vs.clickableConfig,
                onIconClick = {
                    vs.onClick.invoke()
                }
            )
        }
    } else {
        vs.drawableRes?.let {
            Icon(
                modifier = modifier
                    .size(vs.drawableSize)
                    .clickableEffectConfig(
                        config = ClickableConfig(
                            needRipple = false,
                            needSound = false,
                            needHaptic = false
                        ),
                        onClick = {}
                    ),
                painter = painterResource(id = it),
                contentDescription = null,
                tint = vs.drawableColor
            )
        }
    }
}

@Preview
@Composable
private fun TextViewIconPreview() {
    TextWithIcon(
        topConfig = TextWithIconConfig(R.drawable.preview_ic_information),
        startConfig = TextWithIconConfig(R.drawable.preview_ic_information),
        bottomConfig = TextWithIconConfig(R.drawable.preview_ic_information),
        endConfig = TextWithIconConfig(R.drawable.preview_ic_information),
        content = {
            PocketText(
                textModifier = Modifier.padding(horizontal = 3.dp),
                config = PocketTextConfig(
                    value = "title"
                )
            )
        }
    )
}

