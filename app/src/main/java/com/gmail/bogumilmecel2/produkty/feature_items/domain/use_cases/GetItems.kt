package com.gmail.bogumilmecel2.produkty.feature_items.domain.use_cases

import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Data
import com.gmail.bogumilmecel2.produkty.feature_items.domain.repository.ItemsRepository

class GetItems(
    private val itemsRepository: ItemsRepository,
    private val resourceProvider: ResourceProvider
) {

    suspend operator fun invoke(accessToken: AccessToken):Resource<Data>{
        var dataResource = itemsRepository.getItems(accessToken)

        if (dataResource is Resource.Error){
            if (dataResource.uiText==resourceProvider.getString(R.string.the_token_has_expired_or_is_incorrect)){
                itemsRepository.refreshToken(accessToken.refresh_token)
                dataResource = itemsRepository.getItems(accessToken = accessToken)
            }
        }
        return dataResource
    }
}