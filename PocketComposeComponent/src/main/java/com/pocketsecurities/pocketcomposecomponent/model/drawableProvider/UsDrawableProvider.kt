package com.pocketsecurities.pocketcomposecomponent.model.drawableProvider

import com.pocketsecurities.pocketcomposecomponent.R

class UsDrawableProvider: DrawableProvider {
    override fun getIconDeleteResource(): Int = R.drawable.pocket_ic_delete_us

    override fun getCheckOnResource(): Int = R.drawable.pocket_radio_check_on_primary_us

    override fun getCheckOffResource(): Int = R.drawable.pocket_radio_check_off_gray_us
}