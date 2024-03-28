package com.pocketsecurities.composecomponent.model.drawableProvider

import androidx.compose.runtime.Composable
import com.pocketsecurities.composecomponent.utils.isPreviewMode
import org.koin.androidx.compose.get


@Composable
fun localDrawableProvider(): DrawableProvider {
    return if (isPreviewMode()) {
        TwDrawableProvider()
//        UsDrawableProvider()
    } else {
        get<DrawableProvider>()
    }
}