package com.gmail.bogumilmecel2.produkty.common.di

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.gmail.bogumilmecel2.produkty.common.data.BoxStoreUtil
import com.gmail.bogumilmecel2.produkty.common.data.api.ItemsApi
import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.ComposeCustomNavigator
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.Navigator
import com.gmail.bogumilmecel2.produkty.common.util.Constants
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.common.util.Result
import com.gmail.bogumilmecel2.produkty.feature_items.data.repository.FakeItemsRepository
import com.gmail.bogumilmecel2.produkty.feature_items.data.repository.ItemsRepositoryImp
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Data
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.object_box.ObjectBoxItem
import com.gmail.bogumilmecel2.produkty.feature_items.domain.repository.ItemsRepository
import com.gmail.bogumilmecel2.produkty.feature_items.domain.use_cases.GetBoxStoreItems
import com.gmail.bogumilmecel2.produkty.feature_login.data.repository.LoginRepositoryImp
import com.gmail.bogumilmecel2.produkty.feature_login.domain.repository.LoginRepository
import com.gmail.bogumilmecel2.produkty.feature_login.domain.use_cases.LogIn
import com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases.GetAccessToken
import com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases.GetItems
import com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases.ItemsUseCases
import com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases.SaveItemToObjectBox
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Singleton
    @Provides
    fun provideNavigator():Navigator = ComposeCustomNavigator()

    @Singleton
    @Provides
    fun provideResourceProvider(app: Application):ResourceProvider = ResourceProvider(app)

    @Singleton
    @Provides
    fun provideLoginRepository():LoginRepository = object : LoginRepository{
        override suspend fun logIn(username: String, password: String): Result {
            return Result.Success
        }

        override suspend fun saveAccessToken(accessToken: AccessToken): Result {
            return Result.Success
        }
    }

    @Singleton
    @Provides
    fun provideLogInUseCase(
        loginRepository: LoginRepository,
        resourceProvider: ResourceProvider
    ) = LogIn(loginRepository = loginRepository, resourceProvider = resourceProvider)

    @Singleton
    @Provides
    fun provideItemsRepository(
        resourceProvider: ResourceProvider,
    ): ItemsRepository = FakeItemsRepository()

    @Singleton
    @Provides
    fun provideItemsUseCases(
        itemsRepository: ItemsRepository
    ): ItemsUseCases = ItemsUseCases(
        getItems = GetItems(itemsRepository = itemsRepository),
        getAccessToken = GetAccessToken(itemsRepository = itemsRepository),
        saveItemsToObjectBox = SaveItemToObjectBox(itemsRepository = itemsRepository)
    )

    @Singleton
    @Provides
    fun provideGetBoxStoreItemsUseCase(
        itemsRepository: ItemsRepository
    ): GetBoxStoreItems = GetBoxStoreItems(itemsRepository = itemsRepository)



}