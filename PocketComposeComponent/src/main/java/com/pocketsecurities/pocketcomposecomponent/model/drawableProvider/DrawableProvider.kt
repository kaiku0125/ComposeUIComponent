package com.pocketsecurities.pocketcomposecomponent.model.drawableProvider

import androidx.annotation.DrawableRes

interface DrawableProvider {
    @DrawableRes fun getIconDeleteResource() : Int

}