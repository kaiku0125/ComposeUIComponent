package com.pocketsecurities.pocketcomposecomponent.model

import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.util.DebugLogger

class PocketImageLoader(
    private val context: Context,
    private val isDebug: Boolean = true
) : ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        return ImageLoader(context).newBuilder()
            .memoryCachePolicy(CachePolicy.ENABLED)
            .memoryCache {
                MemoryCache.Builder(context)
                    .maxSizePercent(0.1)
                    .strongReferencesEnabled(true)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .diskCache {
                DiskCache.Builder()
                    .maxSizePercent(0.03)
                    .directory(context.cacheDir)
                    .build()
            }
            .logger(
                if (isDebug) {
                    DebugLogger()
                } else {
                    null
                }
            )
            .crossfade(true)
            .build()
    }
}