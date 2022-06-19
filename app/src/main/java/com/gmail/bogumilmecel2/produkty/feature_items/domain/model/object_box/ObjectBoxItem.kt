package com.gmail.bogumilmecel2.produkty.feature_items.domain.model.object_box

import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Category
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.ImageLink
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Price
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Tax
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ObjectBoxItem(
    val category: String = "",
    val category_id: Int = 0,
    @Id var id: Long = 0,
    val image_link: String? = "",
    val item_group_id: Int = 0,
    val joint_id: String = "",
    val name: String = "",
    val price: String = "",
    val status: String = "",
    val tax: String = "",
    val tax_id: Int = 0,
    val updated_at: String = ""
)
