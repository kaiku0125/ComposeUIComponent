package com.pocketsecurities.composecomponent.utils.navigation

import com.pocketsecurities.composecomponent.utils.navigation.data.NavIntent


object ScreenNavigator : NavAction {

    override fun back(route: String?, inclusive: Boolean) {
        navigate(
            NavIntent.Back(
                route = route,
                inclusive = inclusive,
            )
        )
    }

    override fun to(
        route: String,
        popUpToRoute: String?,
        inclusive: Boolean,
        isSingleTop: Boolean,
    ) {
        navigate(
            NavIntent.To(
                route = route,
                popUpToRoute = popUpToRoute,
                inclusive = inclusive,
                isSingleTop = isSingleTop,
            )
        )
    }

    override fun replace(route: String, isSingleTop: Boolean) {
        navigate(
            NavIntent.Replace(
                route = route,
                isSingleTop = isSingleTop,
            )
        )

    }

    override fun offAllTo(route: String) {
        navigate(NavIntent.OffAllTo(route))
    }

    private fun navigate(destination: NavIntent) {
        NavChannel.navigate(destination)
    }

}