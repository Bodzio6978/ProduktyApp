package com.gmail.bogumilmecel2.produkty.feature_splash.presentation

sealed class SplashState {
    object Loading:SplashState()
    data class Error(val message:String):SplashState()
}