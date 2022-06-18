package com.gmail.bogumilmecel2.produkty.feature_items.domain.model

@kotlinx.serialization.Serializable
data class Price(
    val amount: Double,
    val currency: String
)