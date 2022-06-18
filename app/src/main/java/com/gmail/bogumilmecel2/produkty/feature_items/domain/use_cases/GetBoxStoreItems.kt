package com.gmail.bogumilmecel2.produkty.feature_items.domain.use_cases

import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.extensions.toItem
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Item
import com.gmail.bogumilmecel2.produkty.feature_items.domain.repository.ItemsRepository

class GetBoxStoreItems(
    private val itemsRepository: ItemsRepository
) {

    suspend operator fun invoke():Resource<List<Item>>{
        val boxStoreItemsResource = itemsRepository.getObjectBoxItems()
        return if (boxStoreItemsResource is Resource.Success){
            val items = mutableListOf<Item>()
            boxStoreItemsResource.data!!.forEach {
                items.add(it.toItem())
            }
            Resource.Success(items)
        }else{
            Resource.Error(uiText = boxStoreItemsResource.uiText.toString())
        }
    }
}