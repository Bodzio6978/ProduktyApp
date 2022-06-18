package com.gmail.bogumilmecel2.produkty.common.navigation.nav_actions

import androidx.navigation.NavOptions
import com.gmail.bogumilmecel2.produkty.common.util.Screen

object NavigationActions {
    object SplashScreen{
        fun splashToLogin() = object :NavigationAction {
            override val destination = Screen.LoginScreen.route
            override val navOptions: NavOptions = NavOptions.Builder()
                .setPopUpTo(0, true)
                .build()
        }

        fun splashToItems() = object :NavigationAction {
            override val destination = Screen.ItemsScreen.route
            override val navOptions: NavOptions = NavOptions.Builder()
                .setPopUpTo(0, true)
                .build()
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