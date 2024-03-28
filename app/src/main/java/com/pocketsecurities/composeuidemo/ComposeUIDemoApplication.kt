package com.pocketsecurities.composeuidemo

import android.app.Application
import com.pocketsecurities.composecomponent.di.getComposeImageLoaderModule
import com.pocketsecurities.composecomponent.di.getDrawableProviderModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class ComposeUIDemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ComposeUIDemoApplication)
            loadKoinModules(
                listOf(
                    getComposeImageLoaderModule(BuildConfig.DEBUG),
                    getDrawableProviderModule(isTw = true)
                )
            )
        }
    }
}