package com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases

data class ItemsUseCases(
    val getAccessToken: GetAccessToken,
    val getItems: GetItems,
    val saveItemsToObjectBox: SaveItemToObjectBox
)