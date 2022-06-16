package com.gmail.bogumilmecel2.produkty.feature_login.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.feature_login.domain.use_cases.LogIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    resourceProvider: ResourceProvider,
    private val logIn:LogIn
): ViewModel(){

    private val _emailState = mutableStateOf(TextFieldState(hint = resourceProvider.getString(R.string.email)))
    val emailState : State<TextFieldState> = _emailState

    private val _passwordState = mutableStateOf(TextFieldState(hint = resourceProvider.getString(R.string.password)))
    val passwordState : State<TextFieldState> = _passwordState

    fun onEvent(event:LoginEvent){
        when(event){
            is LoginEvent.EnteredLogin -> {
                _emailState.value = emailState.value.copy(value = event.text)
            }
            is LoginEvent.EnteredPassword -> {
                _passwordState.value = passwordState.value.copy(value = event.text)
            }
            is LoginEvent.ClickedSignIn -> {
                viewModelScope.launch(Dispatchers.IO) {
                    logIn(
                        email = _emailState.value.value,
                        password = _passwordState.value.value
                    )
                }
            }
        }
    }
}