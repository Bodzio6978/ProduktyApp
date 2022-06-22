package com.gmail.bogumilmecel2.produkty.feature_login.domain.repository

import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.Result

interface LoginRepository {

    suspend fun logIn(
        username:String,
        password:String
    ):Resource<AccessToken>

    suspend fun saveAccessToken(
        accessToken: AccessToken
    ):Result
}