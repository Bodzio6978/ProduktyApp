package com.gmail.bogumilmecel2.produkty.feature_login.domain.use_cases

import android.util.Patterns
import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.common.util.Result
import com.gmail.bogumilmecel2.produkty.feature_login.domain.repository.LoginRepository
import com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases.GetItems
import com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases.SaveItemToObjectBox

class LogIn(
    private val loginRepository: LoginRepository,
    private val resourceProvider: ResourceProvider,
    private val getItems: GetItems,
    private val saveItemToObjectBox: SaveItemToObjectBox
) {

    suspend operator fun invoke(
        email:String,
        password:String
    ):Result{
        println(email)
        if (email.isBlank()||password.isBlank()){
            return Result.Error(resourceProvider.getString(R.string.please_make_sure_all_field_are_filled_in))
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return Result.Error(resourceProvider.getString(R.string.please_make_sure_you_have_entered_correct_email))
        }
        val loginResource =  loginRepository.logIn(email,password)

        return if (loginResource is Resource.Success){
            val itemsResource = getItems(loginResource.data!!)

            if (itemsResource is Resource.Success){
                saveItemToObjectBox(itemsResource.data!!.data)
            }
            Result.Success
        }else{
            Result.Error(loginResource.uiText.toString())
        }
    }
}