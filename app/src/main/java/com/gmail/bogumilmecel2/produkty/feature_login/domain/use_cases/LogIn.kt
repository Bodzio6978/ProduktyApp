package com.gmail.bogumilmecel2.produkty.feature_login.domain.use_cases

import android.util.Patterns
import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.common.util.Result
import com.gmail.bogumilmecel2.produkty.feature_login.domain.repository.LoginRepository

class LogIn(
    private val loginRepository: LoginRepository,
    private val resourceProvider: ResourceProvider
) {

    suspend operator fun invoke(
        email:String,
        password:String
    ):Result{
        if (email.isBlank()||password.isBlank()){
            return Result.Error(resourceProvider.getString(R.string.please_make_sure_all_field_are_filled_in))
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return Result.Error(resourceProvider.getString(R.string.please_make_sure_you_have_entered_correct_email))
        }
        return loginRepository.logIn(email,password)
    }
}