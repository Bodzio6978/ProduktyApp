package com.gmail.bogumilmecel2.produkty.feature_items.domain.model

@kotlinx.serialization.Serializable
data class Category(
    val id: Int = 0,
    val name: String = "",
    val status: String = "",
    val updated_at: String = ""
)