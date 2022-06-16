package com.gmail.bogumilmecel2.produkty.feature_login.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.gmail.bogumilmecel2.produkty.common.di.AppModule
import com.gmail.bogumilmecel2.produkty.common.navigation.NavHostGraph
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.Navigator
import com.gmail.bogumilmecel2.produkty.common.presentation.MainActivity
import com.gmail.bogumilmecel2.produkty.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(AppModule::class)
internal class LoginScreenTest{

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var navigator: Navigator

    @Before
    fun setUp(){
        hiltRule.inject()
        composeRule.setContent {
            NavHostGraph(navigator = navigator)
        }
    }

    @Test
    fun enteredLogin_EnteredTextVisible(){
        val text = "abc"
        val loginTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.email))
        loginTextField.performTextInput(text)
        loginTextField.assert(hasText(text))
        loginTextField.performTextClearance()
        loginTextField.assert(hasText(""))
    }

    @Test
    fun enteredPassword_EnteredTextVisible(){
        val text = "abc"
        val registerTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.password))
        registerTextField.performTextInput(text)
        registerTextField.assert(hasText(text))
        registerTextField.performTextClearance()
        registerTextField.assert(hasText(""))
    }

}