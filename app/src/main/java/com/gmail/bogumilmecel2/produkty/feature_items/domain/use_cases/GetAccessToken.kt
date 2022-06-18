package com.gmail.bogumilmecel2.produkty.feature_items.domain.use_cases

import android.content.SharedPreferences
import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.common.util.Result
import com.gmail.bogumilmecel2.produkty.feature_items.domain.repository.ItemsRepository

class GetAccessToken(
    private val itemsRepository: ItemsRepository,
) {

    suspend operator fun invoke():Resource<AccessToken>{
        return itemsRepository.getAccessToken()
    }
}