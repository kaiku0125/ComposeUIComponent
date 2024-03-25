package com.pocketsecurities.pocketcomposecomponent.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import com.pocketsecurities.pocketcomposecomponent.model.drawableProvider.DrawableProvider
import com.pocketsecurities.pocketcomposecomponent.model.drawableProvider.TwDrawableProvider
import org.koin.androidx.compose.get

@Composable
fun isPreviewMode(): Boolean = LocalInspectionMode.current