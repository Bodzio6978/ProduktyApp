package com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases

import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Data
import com.gmail.bogumilmecel2.produkty.feature_items.domain.repository.ItemsRepository

class GetItems(
    private val itemsRepository: ItemsRepository,
) {

    suspend operator fun invoke(accessToken: AccessToken): Resource<Data> {
        return itemsRepository.getItems(accessToken)
    }
}