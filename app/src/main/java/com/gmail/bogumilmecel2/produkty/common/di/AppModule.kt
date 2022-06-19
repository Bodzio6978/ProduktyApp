package com.gmail.bogumilmecel2.produkty.common.di

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.gmail.bogumilmecel2.produkty.common.data.BoxStoreUtil
import com.gmail.bogumilmecel2.produkty.common.data.api.ItemsApi
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.ComposeCustomNavigator
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.Navigator
import com.gmail.bogumilmecel2.produkty.common.util.Constants
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.feature_items.data.repository.ItemsRepositoryImp
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
object AppModule {

    @Singleton
    @Provides
    fun provideNavigator():Navigator = ComposeCustomNavigator()

    @Singleton
    @Provides
    fun provideResourceProvider(app: Application):ResourceProvider = ResourceProvider(app)

    @Singleton
    @Provides
    fun provideItemsApi(): ItemsApi = Retrofit.Builder()
        .baseUrl("https://demo2.gopos.pl/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ItemsApi::class.java)

    @Singleton
    @Provides
    fun provideKeyAliases(app: Application): MasterKey = MasterKey.Builder(app, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    @Singleton
    @Provides
    fun provideEncryptedSharedPreferences(
        app: Application,
        masterKey: MasterKey
    ):SharedPreferences = EncryptedSharedPreferences.create(
        app,
        Constants.SHARED_PREF_NAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    @Singleton
    @Provides
    fun provideLoginRepository(
        itemsApi: ItemsApi,
        resourceProvider: ResourceProvider,
        sharedPreferences: SharedPreferences
    ):LoginRepository = LoginRepositoryImp(
        itemsApi = itemsApi,
        resourceProvider = resourceProvider,
        sharedPreferences = sharedPreferences
    )

    @Singleton
    @Provides
    fun provideLogInUseCase(
        loginRepository: LoginRepository,
        resourceProvider: ResourceProvider
    ) = LogIn(loginRepository = loginRepository, resourceProvider = resourceProvider)

    @Singleton
    @Provides
    fun provideBoxStoreUtil(app:Application):BoxStoreUtil = BoxStoreUtil(app)


    @Singleton
    @Provides
    fun provideItemsRepository(
        sharedPreferences: SharedPreferences,
        resourceProvider: ResourceProvider,
        itemsApi: ItemsApi,
        boxStoreUtil: BoxStoreUtil
    ):ItemsRepository = ItemsRepositoryImp(
        sharedPreferences = sharedPreferences,
        resourceProvider = resourceProvider,
        itemsApi = itemsApi,
        boxStoreUtil = boxStoreUtil
    )

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
    ):GetBoxStoreItems = GetBoxStoreItems(itemsRepository = itemsRepository)



}