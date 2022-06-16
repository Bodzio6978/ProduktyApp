package com.gmail.bogumilmecel2.produkty.common.di

import android.app.Application
import android.content.Context
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.ComposeCustomNavigator
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.Navigator
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.feature_login.data.api.ItemsApi
import com.gmail.bogumilmecel2.produkty.feature_login.data.repository.LoginRepositoryImp
import com.gmail.bogumilmecel2.produkty.feature_login.domain.repository.LoginRepository
import com.gmail.bogumilmecel2.produkty.feature_login.domain.use_cases.LogIn
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNavigator():Navigator = ComposeCustomNavigator()

    @Singleton
    @Provides
    fun provideResourceProvider(app: Application):ResourceProvider = ResourceProvider(app)

    @Singleton
    @Provides
    fun provideItemsApi():ItemsApi = Retrofit.Builder()
        .baseUrl("https://demo2.gopos.pl/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ItemsApi::class.java)

    @Singleton
    @Provides
    fun provideLoginRepository(
        itemsApi: ItemsApi,
        resourceProvider: ResourceProvider
    ):LoginRepository = LoginRepositoryImp(itemsApi = itemsApi, resourceProvider)

    @Singleton
    @Provides
    fun provideLogInUseCase(
        loginRepository: LoginRepository
    ) = LogIn(loginRepository = loginRepository)
}