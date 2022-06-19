package com.gmail.bogumilmecel2.produkty.feature_login.presentation

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.testing.TestNavHostController
import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.di.AppModule
import com.gmail.bogumilmecel2.produkty.common.navigation.NavHostGraph
import com.gmail.bogumilmecel2.produkty.common.navigation.navigator.Navigator
import com.gmail.bogumilmecel2.produkty.common.presentation.MainActivity
import com.gmail.bogumilmecel2.produkty.common.util.Screen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import kotlin.math.log

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
            NavHostGraph(
                navigator = navigator,
                startDestination = Screen.LoginScreen.route
            )
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
    fun enteredPassword_TextIsDisplayed(){
        val text = "abc"
        val passwordTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.password))
        passwordTextField.assertIsDisplayed().performTextInput(text)
        val stringBuilder = StringBuilder()
        for (c in text.indices){
            stringBuilder.append("•")
        }
        passwordTextField.assert(
            hasText(stringBuilder.toString())
        )
    }

    @Test
    fun clickedSignIn_NavigatedToItems(){
        val passwordTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.password))
        val loginTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.email))
        passwordTextField.performTextClearance()
        loginTextField.performTextClearance()
        passwordTextField.performTextInput("password")
        loginTextField.performTextInput("login@login.com")
        val signInButton = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.sign_in))
        signInButton.performClick()
        Thread.sleep(10000)
        composeRule.onNodeWithTag(composeRule.activity.getString(R.string.lazy_column)).assertIsDisplayed()
    }

    @Test
    fun emptyEmail_ProperSnackbarDisplayed(){
        val signInButton = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.sign_in))
        val passwordTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.password))
        passwordTextField.performTextInput("password")
        signInButton.performClick()
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.please_make_sure_all_field_are_filled_in)).assertIsDisplayed()
    }

    @Test
    fun emptyPassword_ProperSnackbarDisplayed(){
        val signInButton = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.sign_in))
        val loginTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.email))
        loginTextField.performTextInput("email@email.com")
        signInButton.performClick()
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.please_make_sure_all_field_are_filled_in)).assertIsDisplayed()
    }

    @Test
    fun incorrectEmail_ProperSnackbarDisplayed(){
        val signInButton = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.sign_in))
        val loginTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.email))
        val passwordTextField = composeRule.onNodeWithTag(composeRule.activity.getString(R.string.password))
        passwordTextField.performTextInput("password")
        loginTextField.performTextInput("email")
        signInButton.performClick()
        composeRule.onNodeWithText(composeRule.activity.getString(R.string.please_make_sure_you_have_entered_correct_email)).assertIsDisplayed()
    }

}