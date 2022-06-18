package com.gmail.bogumilmecel2.produkty.feature_items.domain.model

@kotlinx.serialization.Serializable
data class ImageLink(
    val default_link: String,
    val small: String
)