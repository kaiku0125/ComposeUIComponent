package com.pocketsecurities.composecomponent.model.drawableProvider

import androidx.annotation.DrawableRes

interface DrawableProvider {
    @DrawableRes fun getIconDeleteResource() : Int
    @DrawableRes fun getCheckOnResource() : Int
    @DrawableRes fun getCheckOffResource() : Int

}