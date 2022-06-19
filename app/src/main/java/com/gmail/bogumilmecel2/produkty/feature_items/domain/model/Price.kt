package com.gmail.bogumilmecel2.produkty.feature_items.domain.model

@kotlinx.serialization.Serializable
data class Price(
    val amount: Double = 0.0,
    val currency: String = ""
)