package com.gmail.bogumilmecel2.produkty.feature_login.data.repository

import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.feature_login.data.api.ItemsApi
import com.gmail.bogumilmecel2.produkty.feature_login.domain.repository.LoginRepository
import retrofit2.HttpException

class LoginRepositoryImp(
    private val itemsApi: ItemsApi,
    private val resourceProvider: ResourceProvider
):LoginRepository {

    override suspend fun logIn(
        username: String,
        password: String
    ): Resource<AccessToken> {
        return try {
            Resource.Success(
                itemsApi.logInUser(
                    username = username,
                    password = password
                )
            )
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
}