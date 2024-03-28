package com.pocketsecurities.composecomponent.component.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isFinite
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue

@Composable
fun AutoSizeText(
    text: String,
    modifier: Modifier = Modifier,
    acceptableError: Dp = 5.dp,
    maxFontSize: TextUnit = TextUnit.Unspecified,
    color: Color = Color.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    contentAlignment: Alignment? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current
) {
    val alignment: Alignment = contentAlignment ?: when (textAlign) {
        TextAlign.Left -> Alignment.TopStart
        TextAlign.Right -> Alignment.TopEnd
        TextAlign.Center -> Alignment.Center
        TextAlign.Justify -> Alignment.TopCenter
        TextAlign.Start -> Alignment.TopStart
        TextAlign.End -> Alignment.TopEnd
        else -> Alignment.TopStart
    }
    BoxWithConstraints(modifier = modifier, contentAlignment = alignment) {
        var shrunkFontSize = if (maxFontSize.isSpecified) maxFontSize else 100.sp

        val calculateIntrinsics = @Composable {
            val mergedStyle = style.merge(
                TextStyle(
                    color = color,
                    fontSize = shrunkFontSize,
                    fontWeight = fontWeight,
                    textAlign = textAlign ?: TextAlign.Unspecified,
                    lineHeight = lineHeight,
                    fontFamily = fontFamily,
                    textDecoration = textDecoration,
                    fontStyle = fontStyle,
                    letterSpacing = letterSpacing
                )
            )
            Paragraph(
                text = text.ifEmpty { " " },
                style = mergedStyle,
                constraints = Constraints(maxWidth = kotlin.math.ceil(LocalDensity.current.run { maxWidth.toPx() }
                    .toDouble()).toInt()),
                density = LocalDensity.current,
                fontFamilyResolver = LocalFontFamilyResolver.current,
                spanStyles = listOf(),
                placeholders = listOf(),
                maxLines = maxLines,
                ellipsis = false
            )
        }

        var intrinsics = calculateIntrinsics()

        val targetWidth = maxWidth - acceptableError / 2f

        check(targetWidth.isFinite || maxFontSize.isSpecified) { "maxFontSize must be specified if the target with isn't finite!" }

        with(LocalDensity.current) {
            // this loop will attempt to quickly find the correct size font by scaling it by the error
            // it only runs if the max font size isn't specified or the font must be smaller
            // minIntrinsicWidth is "The width for text if all soft wrap opportunities were taken."
            if (maxFontSize.isUnspecified || targetWidth < intrinsics.minIntrinsicWidth.toDp())
                while ((targetWidth - intrinsics.minIntrinsicWidth.toDp()).toPx().absoluteValue.toDp() > acceptableError / 2f) {
                    shrunkFontSize *= targetWidth.toPx() / intrinsics.minIntrinsicWidth
                    intrinsics = calculateIntrinsics()
                }
            // checks if the text fits in the bounds and scales it by 90% until it does
            while (intrinsics.didExceedMaxLines || maxHeight < intrinsics.height.toDp() || maxWidth < intrinsics.minIntrinsicWidth.toDp()) {
                shrunkFontSize *= 0.9f
                intrinsics = calculateIntrinsics()
            }
        }

        if (maxFontSize.isSpecified && shrunkFontSize > maxFontSize)
            shrunkFontSize = maxFontSize

        Text(
            text = text,
            color = color,
            fontSize = shrunkFontSize,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign,
            lineHeight = lineHeight,
            onTextLayout = onTextLayout,
            maxLines = maxLines,
            style = style
        )
    }
}

/**
 * @sample AutoSizeText 自適應text
 * !使用方式!：不要將 modifier設定 長寬 因為會觸發 hasVisualOverflow 無限迴圈
 *
 * @param key 用於強制重新計算字體大小的key
 * @param maxFontSize 最適字體極限大小
 * @param fontWeight 字體粗度
 * @param config text設定檔案(目前可填入有用的參數為value,textColor與alignment)
 */
@Composable
fun AutoSizeText(
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    key: Any? = null,
    maxFontSize: TextUnit = 13.sp,
    fontWeight: FontWeight = FontWeight(500),
    config: PocketTextConfig = PocketTextConfig()
) {
    var multiplier by remember { mutableFloatStateOf(1f) }

    key?.let {
        LaunchedEffect(it) {
            multiplier = 1f
        }
    }

    Box(
        modifier = modifier,
        contentAlignment = config.alignment
    ) {
        Text(
            modifier = textModifier,
            text = config.value,
            color = config.textColor,
            maxLines = config.maxLines,
            overflow = TextOverflow.Visible,
            style = LocalTextStyle.current.copy(
                fontSize = maxFontSize * multiplier,
                fontWeight = fontWeight
            ),
            onTextLayout = {
                if (it.hasVisualOverflow) {
                    multiplier *= 0.95f
                }
            }
        )
    }
}
