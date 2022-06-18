package com.gmail.bogumilmecel2.produkty.feature_items.data.repository
import android.content.SharedPreferences
import android.util.Log
import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.data.api.ItemsApi
import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.util.*
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Data
import com.gmail.bogumilmecel2.produkty.feature_items.domain.repository.ItemsRepository
import com.google.gson.Gson
import retrofit2.HttpException

class ItemsRepositoryImp(
    private val sharedPreferences: SharedPreferences,
    private val resourceProvider: ResourceProvider,
    private val itemsApi:ItemsApi
): ItemsRepository {

    override suspend fun getAccessToken(): Resource<AccessToken> {
        return try {
            val accessTokenString = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)

            if (accessTokenString!=null){
                val accessToken = Gson().fromJson(accessTokenString, AccessToken::class.java)
                Log.e(TAG,accessToken.toString())
                Resource.Success(data = accessToken)
            }else{
                Resource.Success(data = null)
            }
        }catch (e:Exception){
            Resource.Error(uiText = resourceProvider.getString(R.string.unknown_error))
        }
    }

    override suspend fun refreshToken(refreshToken: String): Result {
        return try {
            itemsApi.refreshToken(
                refreshToken = refreshToken
            )
            Result.Success
        }catch (e:Exception){
            Result.Error(message = resourceProvider.getString(R.string.unknown_error))
        }

    }

    override suspend fun getItems(accessToken: AccessToken): Resource<Data> {
        return try {
            val data =  itemsApi.getItems(
                accessToken = accessToken.access_token
            )
            Log.e(TAG,data.data.toString())
            Resource.Success(data = data)
        }catch (e:HttpException){
            if (e.code()==401){
                Resource.Error(resourceProvider.getString(R.string.the_token_has_expired_or_is_incorrect))
            }else{
                Resource.Error(resourceProvider.getString(R.string.unknown_error))
            }
        }catch (e:Exception){
            Resource.Error(resourceProvider.getString(R.string.unknown_error))
        }

    }
}