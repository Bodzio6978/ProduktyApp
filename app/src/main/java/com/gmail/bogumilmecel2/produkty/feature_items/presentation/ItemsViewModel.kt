package com.gmail.bogumilmecel2.produkty.feature_items.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.Navigator
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Category
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.ImageLink
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Item
import com.gmail.bogumilmecel2.produkty.feature_items.domain.use_cases.GetBoxStoreItems
import com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases.ItemsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val getBoxStoreItems: GetBoxStoreItems
): ViewModel(){

    private val _itemsState = mutableStateOf(emptyList<Item>())
    val itemsState : State<List<Item>> = _itemsState

    private val _snackbarEvent = MutableSharedFlow<String>()
    val snackbarEvent : SharedFlow<String> = _snackbarEvent

    fun getItems(){
        viewModelScope.launch(Dispatchers.IO) {
            val resource = getBoxStoreItems()

            if (resource is Resource.Error){
                _snackbarEvent.emit(resource.uiText.toString())
            }else{
                _itemsState.value = resource.data!!.sortedWith(compareBy(nullsLast()) { it.image_link })
            }
        }
    }
}