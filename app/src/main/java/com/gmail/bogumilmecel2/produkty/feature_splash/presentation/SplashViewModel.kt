package com.gmail.bogumilmecel2.produkty.feature_splash.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.navigation.nav_actions.NavigationActions
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.Navigator
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.Result
import com.gmail.bogumilmecel2.produkty.common.util.TAG
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Item
import com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases.ItemsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val itemsUseCases: ItemsUseCases,
    private val navigator: Navigator
) : ViewModel() {

    private val _accessToken = MutableSharedFlow<AccessToken>()
    val accessToken: SharedFlow<AccessToken> = _accessToken

    private val _splashState = mutableStateOf<SplashState>(SplashState.Loading)
    val splashState: State<SplashState> = _splashState

    fun getAccessToken() {
        viewModelScope.launch(Dispatchers.IO) {
            val accessTokenResource = itemsUseCases.getAccessToken()

            if (accessTokenResource is Resource.Success) {
                Log.e(TAG,accessTokenResource.data.toString())
                if (accessTokenResource.data == null) {
                    navigator.navigate(NavigationActions.SplashScreen.splashToLogin())
                } else {
                    getItems(accessTokenResource.data)
                }
            } else {
                _splashState.value = SplashState.Error(accessTokenResource.uiText.toString())
            }
        }
    }

    private suspend fun getItems(accessToken: AccessToken) {
        val itemsResource = itemsUseCases.getItems(accessToken)

        if (itemsResource is Resource.Success) {
            Log.e(TAG,itemsResource.data.toString())
            val items = itemsResource.data!!.data
            saveItems(items)
        } else {
            _splashState.value = SplashState.Error(itemsResource.uiText.toString())
        }

    }

    private suspend fun saveItems(items: List<Item>) {
        val result = itemsUseCases.saveItemsToObjectBox(items = items)
        Log.e(TAG,result.toString())
        if (result is Result.Error) {
            _splashState.value = SplashState.Error(result.message)
        } else {
            navigator.navigate(NavigationActions.SplashScreen.splashToItems())
        }
    }
}