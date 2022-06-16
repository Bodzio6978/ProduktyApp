package com.gmail.bogumilmecel2.produkty.common.util

sealed class Result {
    object Success: Result()
    data class Error(val message:String): Result()
}