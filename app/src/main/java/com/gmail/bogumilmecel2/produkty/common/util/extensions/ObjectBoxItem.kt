package com.gmail.bogumilmecel2.produkty.common.util.extensions

import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.*
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.object_box.ObjectBoxItem
import com.google.gson.Gson

fun ObjectBoxItem.toItem(): Item {
    return Item(
        category = if (this.category.isNotBlank()) Gson().fromJson(this.category,Category::class.java) else Category(),
        category_id = this.category_id,
        id = this.id.toInt(),
        image_link = if (this.image_link==null||this.image_link.isBlank()) null else Gson().fromJson(this.image_link,ImageLink::class.java),
        item_group_id = this.item_group_id,
        joint_id = this.joint_id,
        name = this.name,
        price = if (this.price.isNotBlank()) Gson().fromJson(this.price,Price::class.java) else Price(),
        status = this.status,
        tax = if (this.tax.isNotBlank()) Gson().fromJson(this.tax,Tax::class.java) else Tax(),
        tax_id = this.tax_id,
        updated_at = this.updated_at
    )
}