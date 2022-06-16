package com.gmail.bogumilmecel2.produkty.common.navigation.navigator

import com.gmail.bogumilmecel2.produkty.common.navigation.nav_actions.NavigationAction
import kotlinx.coroutines.flow.StateFlow

interface Navigator {
    val navActions:StateFlow<NavigationAction?>
    fun navigate(navAction:NavigationAction?)
}