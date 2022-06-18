package com.gmail.bogumilmecel2.produkty.common.util

sealed class Screen(val route:String){
    object LoginScreen:Screen("login_screen")
    object ItemsScreen:Screen("items_screen")
    object SplashScreen:Screen("splash_screen")
}
