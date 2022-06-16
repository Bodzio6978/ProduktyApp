package com.gmail.bogumilmecel2.produkty.common.di

import android.app.Application
import android.content.Context
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.ComposeCustomNavigator
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.Navigator
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

//    @Singleton
//    @Provides
//    fun provideContext() : Context =

    @Singleton
    @Provides
    fun provideNavigator(): Navigator = ComposeCustomNavigator()

    @Singleton
    @Provides
    fun provideResourceProvider(app: Application): ResourceProvider = ResourceProvider(app)
}