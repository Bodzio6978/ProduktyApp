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
        val resource = loginRepository.logIn(email,password)
        Log.e("login use case", resource.toString())
        return if (resource is Resource.Success){
            Log.e("login use case",resource.data!!.toString())
            Result.Success
        }else{
            Log.e("login use case", resource.uiText.toString())
            Result.Error(resource.uiText!!)
        }
    }
}