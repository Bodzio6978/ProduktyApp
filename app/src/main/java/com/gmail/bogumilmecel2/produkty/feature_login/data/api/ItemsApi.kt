package com.gmail.bogumilmecel2.produkty.feature_login.data.api

import com.gmail.bogumilmecel2.produkty.BuildConfig
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.AccessToken
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemsApi {

    @GET("/oauth/token")
    suspend fun logInUser(
        @Query("password") password:String,
        @Query("username") username:String,
        @Query("client_secret") clientSecret:String = BuildConfig.CLIENT_SECRET,
        @Query("client_id") clientId:String = BuildConfig.CLIENT_ID,
        @Query("grant_type") grantType:String = "password"
    ):AccessToken
}