package com.pocketsecurities.pocketcomposecomponent.model.drawableProvider

import androidx.compose.runtime.Composable
import com.pocketsecurities.pocketcomposecomponent.utils.isPreviewMode
import org.koin.androidx.compose.get


@Composable
fun localDrawableProvider(): DrawableProvider {
    return if (isPreviewMode()) {
        TwDrawableProvider()
    } else {
        get<DrawableProvider>()
    }
}