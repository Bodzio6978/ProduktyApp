package com.gmail.bogumilmecel2.produkty.feature_login.domain.use_cases

import android.util.Log
import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.feature_login.domain.repository.LoginRepository
import com.gmail.bogumilmecel2.produkty.common.util.Result

class LogIn(
    private val loginRepository: LoginRepository,
    private val resourceProvider: ResourceProvider
) {

    suspend operator fun invoke(
        email:String,
        password:String
    ):Result{
        return loginRepository.logIn(email,password)
    }
}