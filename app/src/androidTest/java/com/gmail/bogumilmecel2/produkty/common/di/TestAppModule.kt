package com.gmail.bogumilmecel2.produkty.common.di

import android.app.Application
import android.content.SharedPreferences
import com.gmail.bogumilmecel2.produkty.common.data.api.ItemsApi
import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.ComposeCustomNavigator
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.Navigator
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.common.util.Result
import com.gmail.bogumilmecel2.produkty.feature_login.domain.repository.LoginRepository
import com.gmail.bogumilmecel2.produkty.feature_login.domain.use_cases.LogIn
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideLoginRepository(
        itemsApi: ItemsApi,
        resourceProvider: ResourceProvider,
        sharedPreferences: SharedPreferences
    ): LoginRepository = object : LoginRepository{
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

}