package com.gmail.bogumilmecel2.produkty.common.navigation.nav_actions

import androidx.navigation.NavOptions
import com.gmail.bogumilmecel2.produkty.common.util.Screen

object NavigationActions {
    object ItemsScreen{
        fun itemsToLogin() = object : NavigationAction {
            override val destination = Screen.LoginScreen.route
        }
    }

    object LoginScreen{
        fun loginToItems() = object : NavigationAction {
            override val destination = Screen.ItemsScreen.route
            override val navOptions: NavOptions = NavOptions.Builder()
                .setPopUpTo(0, true)
                .build()
        }
    }
}