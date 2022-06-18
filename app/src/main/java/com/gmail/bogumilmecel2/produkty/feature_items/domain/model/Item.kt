package com.gmail.bogumilmecel2.produkty.feature_items.domain.model

@kotlinx.serialization.Serializable
data class Item(
    val category_id: Int,
    val id: Int,
    val image_link: ImageLink,
    val item_group_id: Int,
    val joint_id: String,
    val name: String,
    val price: Price,
    val status: String,
    val tax_id: Int,
    val updated_at: String
)