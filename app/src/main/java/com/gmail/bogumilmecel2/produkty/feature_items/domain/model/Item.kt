package com.gmail.bogumilmecel2.produkty.feature_items.domain.model

@kotlinx.serialization.Serializable
data class Item(
    val category: Category = Category(),
    val category_id: Int = 0,
    val id: Int = 0,
    val image_link: ImageLink? = ImageLink(),
    val item_group_id: Int = 0,
    val joint_id: String = "",
    val name: String = "",
    val price: Price = Price(),
    val status: String = "",
    val tax: Tax = Tax(),
    val tax_id: Int = 0,
    val updated_at: String = ""
)