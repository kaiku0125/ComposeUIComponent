package com.pocketsecurities.pocketcomposecomponent.component.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.SubcomposeAsyncImage
import coil.decode.SvgDecoder
import coil.imageLoader
import coil.request.ImageRequest
import com.pocketsecurities.pocketcomposecomponent.component.loading.CircularLoadingScene
import com.pocketsecurities.pocketcomposecomponent.extension.ClickableConfig
import com.pocketsecurities.pocketcomposecomponent.extension.clickableEffectConfig
import com.pocketsecurities.pocketcomposecomponent.R
import com.pocketsecurities.pocketcomposecomponent.utils.isPreviewMode
import org.koin.androidx.compose.get

/**
 * @sample BoxAsyncImageConfig
 *
 * @param url 圖片網址
 * @param ratio 圖片寬高比
 * @param shape 圖片形狀
 * @param contentScale 圖片剪裁模式
 * @param bg 背景顏色
 * @param description 圖片描述
 */
data class BoxAsyncImageConfig(
    val url: String = "",
    val ratio: Float = 1f,
    val errorDrawable: Int = 0,
    val shape: Shape = RoundedCornerShape(8.dp),
    val contentScale: ContentScale = ContentScale.Fit,
    val bg: Color = Color.Transparent,
    val description: String = "box_async_image"
)

/**
 * @sample BoxAsyncImage coil image with click effect
 *
 * @param imageLoader 自定義 image loader
 * @param viewConfig view 設定
 * @param clickConfig clickable 設定
 * @param onClick export 點擊事件
 */
@Composable
fun BoxAsyncImage(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader? = null,
    viewConfig: BoxAsyncImageConfig = BoxAsyncImageConfig(),
    clickConfig: ClickableConfig = ClickableConfig(),
    onClick: () -> Unit = {}
) {
    val dynamicImageLoader = if (isPreviewMode()) {
        LocalContext.current.imageLoader
    } else {
        imageLoader ?: get()
    }

    Box(
        modifier = modifier
            .aspectRatio(viewConfig.ratio)
            .background(
                color = viewConfig.bg,
                shape = viewConfig.shape
            )
            .clip(shape = viewConfig.shape)
            .clickableEffectConfig(
                config = if (viewConfig.url.isNotEmpty()) {
                    clickConfig
                } else {
                    ClickableConfig(
                        needRipple = false,
                        needSound = false
                    )
                },
                onClick = {
                    if (viewConfig.url.isNotEmpty()) {
                        onClick.invoke()
                    }
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .decoderFactory(SvgDecoder.Factory())
                .data(viewConfig.url)
                .diskCacheKey(viewConfig.url)
                .memoryCacheKey(viewConfig.url)
                .build(),
            imageLoader = dynamicImageLoader,
            loading = {
                BoxAsyncImageLoadingScene(viewConfig.errorDrawable)
            },
            error = {
                if (viewConfig.url.isNotEmpty()) {
                    Image(
                        modifier = Modifier
                            .aspectRatio(viewConfig.ratio)
                            .clip(shape = viewConfig.shape),
                        painter = painterResource(id = viewConfig.errorDrawable),
                        contentDescription = null
                    )
                }
            },
            contentScale = viewConfig.contentScale,
            contentDescription = viewConfig.description
        )
    }
}

@Composable
private fun BoxAsyncImageLoadingScene(
    @DrawableRes drawableRes: Int
) {
    if (LocalInspectionMode.current) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = drawableRes),
            contentDescription = null
        )
    } else {
        CircularLoadingScene()
    }
}
