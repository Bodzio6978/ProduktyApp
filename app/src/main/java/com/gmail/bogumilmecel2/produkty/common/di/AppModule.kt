package com.gmail.bogumilmecel2.produkty.common.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKeys
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.ComposeCustomNavigator
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.Navigator
import com.gmail.bogumilmecel2.produkty.common.util.Constants
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.feature_login.data.api.ItemsApi
import com.gmail.bogumilmecel2.produkty.feature_login.data.repository.LoginRepositoryImp
import com.gmail.bogumilmecel2.produkty.feature_login.domain.repository.LoginRepository
import com.gmail.bogumilmecel2.produkty.feature_login.domain.use_cases.LogIn
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

}