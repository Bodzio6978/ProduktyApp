package com.gmail.bogumilmecel2.produkty.feature_items.domain.model

data class Tax(
    val code: String,
    val id: Int,
    val name: String,
    val rate: Double
)