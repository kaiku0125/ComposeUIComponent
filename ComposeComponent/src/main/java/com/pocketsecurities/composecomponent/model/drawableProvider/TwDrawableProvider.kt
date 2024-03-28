package com.pocketsecurities.composecomponent.model.drawableProvider

import com.pocketsecurities.composecomponent.R

class TwDrawableProvider: DrawableProvider {
    override fun getIconDeleteResource(): Int = R.drawable.pocket_ic_delete_tw

    override fun getCheckOnResource(): Int = R.drawable.pocket_radio_check_on_primary_tw

    override fun getCheckOffResource(): Int = R.drawable.pocket_radio_check_off_gray_tw
}