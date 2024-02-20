package com.pocketsecurities.pocketcomposecomponent.di

import coil.ImageLoader
import com.pocketsecurities.pocketcomposecomponent.utils.PocketImageLoader
import org.koin.android.BuildConfig
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