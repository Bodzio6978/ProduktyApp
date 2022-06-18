package com.gmail.bogumilmecel2.produkty.common.domain.model

@kotlinx.serialization.Serializable
data class AccessToken(
    val access_token: String,
    val expires_in: Int,
    val refresh_token: String,
    val scope: String,
    val token_type: String
)