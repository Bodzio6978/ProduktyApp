package com.gmail.bogumilmecel2.produkty.feature_login.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.common.util.Result
import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.data.api.ItemsApi
import com.gmail.bogumilmecel2.produkty.common.util.Constants
import com.gmail.bogumilmecel2.produkty.common.util.TAG
import com.gmail.bogumilmecel2.produkty.feature_login.domain.repository.LoginRepository
import com.google.gson.Gson
import retrofit2.HttpException

class LoginRepositoryImp(
    private val itemsApi: ItemsApi,
    private val resourceProvider: ResourceProvider,
    private val sharedPreferences: SharedPreferences
):LoginRepository {

    override suspend fun logIn(
        username: String,
        password: String
    ): Result {
        return try {
            val accessToken = itemsApi.logInUser(
                username = username,
                password = password
            )
            saveAccessToken(accessToken = accessToken)
        }catch (e:HttpException){
            if (e.code() == 401){
                Result.Error(resourceProvider.getString(R.string.the_email_or_password_is_incorrect))
            }else{
                Result.Error(resourceProvider.getString(R.string.unknown_http_error))
            }
        }catch (e:Exception){
            Result.Error(resourceProvider.getString(R.string.unknown_error))
        }
    }

    override suspend fun saveAccessToken(accessToken: AccessToken): Result {
        return try {
            val accessTokenString = Gson().toJson(accessToken)
            Log.e(TAG,accessTokenString)
            sharedPreferences
                .edit()
                .putString(Constants.ACCESS_TOKEN,accessTokenString)
                .apply()
            Result.Success
        }catch (e:Exception){
            Result.Error(resourceProvider.getString(R.string.unknown_error))
        }
    }
}