package com.gmail.bogumilmecel2.produkty.feature_items.domain.model

@kotlinx.serialization.Serializable
data class Data(
    val `data`: List<Item> = emptyList()
)