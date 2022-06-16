package com.gmail.bogumilmecel2.produkty.common.navigation.navigator

import com.gmail.bogumilmecel2.produkty.common.navigation.nav_actions.NavigationAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ComposeCustomNavigator:Navigator {
    private val _navActions: MutableStateFlow<NavigationAction?> by lazy {
        MutableStateFlow(null)
    }
    override val navActions = _navActions.asStateFlow()

    override fun navigate(navAction: NavigationAction?) {
        _navActions.update { navAction }
    }
}