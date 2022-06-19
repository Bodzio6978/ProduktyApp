package com.gmail.bogumilmecel2.produkty.feature_items.data.repository

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.Result
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Data
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.object_box.ObjectBoxItem
import com.gmail.bogumilmecel2.produkty.feature_items.domain.repository.ItemsRepository

class FakeItemsRepository:ItemsRepository {

    private val _items = mutableListOf<ObjectBoxItem>()
    val items : List<ObjectBoxItem> = _items

    override suspend fun getAccessToken(): Resource<AccessToken> {
        return Resource.Success(AccessToken())
    }

    override suspend fun refreshToken(refreshToken: String): Result {
        return Result.Success
    }

    override suspend fun getItems(accessToken: AccessToken): Resource<Data> {
        return Resource.Success(data = null)
    }

    override suspend fun getObjectBoxItems(): Resource<List<ObjectBoxItem>> {
        return Resource.Success(items)
    }

    override suspend fun saveItemsToObjectBox(items: List<ObjectBoxItem>): Result {
        _items.clear()
        _items.addAll(items)
        return Result.Success
    }
}