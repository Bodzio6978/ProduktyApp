package com.gmail.bogumilmecel2.produkty.common.util.extensions

import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Item
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.object_box.ObjectBoxItem
import com.google.gson.Gson

fun Item.toObjectBoxItem(): ObjectBoxItem {
    return ObjectBoxItem(
        category = Gson().toJson(this.category),
        category_id = this.category_id,
        id = 0,
        image_link = if(this.image_link!=null) Gson().toJson(this.image_link) else null,
        item_group_id = this.item_group_id,
        joint_id = this.joint_id,
        name = this.name,
        price = Gson().toJson(this.price),
        status = this.status,
        tax = Gson().toJson(this.tax),
        tax_id = this.tax_id,
        updated_at = this.updated_at
    )
}