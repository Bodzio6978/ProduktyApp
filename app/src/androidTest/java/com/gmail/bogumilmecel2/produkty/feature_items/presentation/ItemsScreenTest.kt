package com.gmail.bogumilmecel2.produkty.feature_items.presentation

import com.gmail.bogumilmecel2.produkty.common.di.AppModule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.jupiter.api.Assertions.*

@HiltAndroidTest
@UninstallModules(AppModule::class)
internal class ItemsScreenTest