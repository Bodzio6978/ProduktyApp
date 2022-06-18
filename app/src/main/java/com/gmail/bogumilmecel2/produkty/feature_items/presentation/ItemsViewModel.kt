package com.gmail.bogumilmecel2.produkty.feature_items.presentation

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.navigation.nav_actions.NavigationActions
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.Navigator
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.TAG
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Data
import com.gmail.bogumilmecel2.produkty.feature_items.domain.use_cases.ItemsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val itemsUseCases: ItemsUseCases,
    private val navigator: Navigator
): ViewModel(){

    private val _accessToken = MutableSharedFlow<AccessToken>()
    val accessToken : SharedFlow<AccessToken> = _accessToken

    private val _itemsState = mutableStateOf(Data())
    val itemsState : State<Data> = _itemsState

    private val _snackbarEvent = MutableSharedFlow<String>()
    val snackbarEvent : SharedFlow<String> = _snackbarEvent

    fun getAccessToken(){
        viewModelScope.launch(Dispatchers.IO) {
            val accessTokenResource = itemsUseCases.getAccessToken()

            if (accessTokenResource is Resource.Success){
                if (accessTokenResource.data==null){
                    navigator.navigate(NavigationActions.ItemsScreen.itemsToLogin())
                }else{
                    _accessToken.emit(accessTokenResource.data)
                }
            }else{
                _snackbarEvent.emit(accessTokenResource.uiText.toString())
            }
        }
    }

    fun getItems(accessToken: AccessToken){
        viewModelScope.launch(Dispatchers.IO) {
            val itemsResource = itemsUseCases.getItems(accessToken)

            if (itemsResource is Resource.Success){
                _itemsState.value = itemsResource.data!!
            }else{
                _snackbarEvent.emit(itemsResource.uiText.toString())
            }
        }
    }
}