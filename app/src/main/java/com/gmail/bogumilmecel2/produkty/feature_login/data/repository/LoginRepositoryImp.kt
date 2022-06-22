package com.gmail.bogumilmecel2.produkty.feature_login.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.data.api.ItemsApi
import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.util.*
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
    ): Resource<AccessToken> {
        return try {
            val accessToken = itemsApi.logInUser(
                username = username,
                password = password
            )
            val result = saveAccessToken(accessToken = accessToken)
            if (result is Result.Error){
                Resource.Error(result.message)
            }else{
                Resource.Success(accessToken)
            }
        }catch (e:HttpException){
            if (e.code() == 401){
                Resource.Error(resourceProvider.getString(R.string.the_email_or_password_is_incorrect))
            }else{
                Resource.Error(resourceProvider.getString(R.string.unknown_http_error))
            }
        }catch (e:Exception){
            Resource.Error(resourceProvider.getString(R.string.unknown_error))
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