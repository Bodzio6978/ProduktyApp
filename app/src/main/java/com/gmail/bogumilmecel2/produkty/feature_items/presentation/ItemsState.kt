package com.gmail.bogumilmecel2.produkty.feature_items.presentation

import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Data

data class ItemsState(
    val accessToken: AccessToken? = null,
    val items:Data? = null
)
