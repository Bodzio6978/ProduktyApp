package com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases

import com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases.GetAccessToken
import com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases.GetItems

data class ItemsUseCases(
    val getAccessToken: GetAccessToken,
    val getItems: GetItems,
    val saveItemsToObjectBox: SaveItemToObjectBox
)