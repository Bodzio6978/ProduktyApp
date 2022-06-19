package com.gmail.bogumilmecel2.produkty.feature_items.domain.repository


import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.Result
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Data
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.object_box.ObjectBoxItem

interface ItemsRepository {

    suspend fun getAccessToken():Resource<AccessToken>

    suspend fun refreshToken(refreshToken:String):Result

    suspend fun getItems(accessToken: AccessToken): Resource<Data>

    suspend fun getObjectBoxItems():Resource<List<ObjectBoxItem>>
    suspend fun saveItemsToObjectBox(items:List<ObjectBoxItem>):Result
}