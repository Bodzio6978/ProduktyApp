package com.gmail.bogumilmecel2.produkty.feature_login.presentation

sealed class LoginEvent{
    data class EnteredLogin(val text:String):LoginEvent()
    data class EnteredPassword(val text:String):LoginEvent()
    object ClickedSignIn:LoginEvent()
}
