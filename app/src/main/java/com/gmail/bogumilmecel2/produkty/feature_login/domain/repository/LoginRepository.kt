package com.gmail.bogumilmecel2.produkty.feature_login.domain.repository

import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.util.Result

interface LoginRepository {

    suspend fun logIn(
        username:String,
        password:String
    ):Result

    suspend fun saveAccessToken(
        accessToken: AccessToken
    ):Result
}