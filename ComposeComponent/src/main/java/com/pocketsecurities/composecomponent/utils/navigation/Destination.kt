package com.pocketsecurities.composecomponent.utils.navigation

import androidx.navigation.NamedNavArgument
import com.pocketsecurities.composecomponent.extension.appendArguments

abstract class Destination(
    path: String,
    val arguments: List<NamedNavArgument> = emptyList(),
) {
    val route: String = if (arguments.isEmpty()) path else path.appendArguments(arguments)
}
