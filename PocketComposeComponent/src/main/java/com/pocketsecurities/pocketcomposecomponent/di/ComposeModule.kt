package com.pocketsecurities.pocketcomposecomponent.di

import coil.ImageLoader
import com.pocketsecurities.pocketcomposecomponent.model.PocketImageLoader
import com.pocketsecurities.pocketcomposecomponent.model.drawableProvider.DrawableProvider
import com.pocketsecurities.pocketcomposecomponent.model.drawableProvider.TwDrawableProvider
import com.pocketsecurities.pocketcomposecomponent.model.drawableProvider.UsDrawableProvider
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

fun getComposeImageLoaderModule(isDebugMode: Boolean): Module {
    return module {
        single<ImageLoader> {
            PocketImageLoader(
                context = androidContext(),
                isDebug = isDebugMode
            ).newImageLoader()
        }
    }
}

fun getDrawableProviderModule(isTw: Boolean): Module {
    return module {
        factory<DrawableProvider> {
            if (isTw) {
                TwDrawableProvider()
            } else {
                UsDrawableProvider()
            }
        }
    }
}