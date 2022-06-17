package com.gmail.bogumilmecel2.produkty.feature_login.domain.use_cases

import com.gmail.bogumilmecel2.produkty.common.util.ResourceProvider
import com.gmail.bogumilmecel2.produkty.feature_login.domain.repository.LoginRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Assertions.*
import com.gmail.bogumilmecel2.produkty.common.util.Result
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
internal class LogInTest {

    private lateinit var closeable: AutoCloseable

    @Mock
    private lateinit var loginRepository: LoginRepository

    private lateinit var logInUseCase:LogIn

    @Before
    fun setUp() = runTest {
        closeable = MockitoAnnotations.openMocks(this)

        loginRepository = Mockito.mock(LoginRepository::class.java)
        Mockito.`when`(
            loginRepository.logIn(
                Mockito.anyString(),
                Mockito.anyString()
            )
        ).thenReturn(Result.Success)

        logInUseCase = LogIn(
            resourceProvider = ResourceProvider(context = RuntimeEnvironment.getApplication()),
            loginRepository = loginRepository
        )
    }

    @After
    fun after() {
        closeable.close()
    }

    @Test
    fun emptyMail_ResultError() = runTest{
        val result = logInUseCase(email = "", password = "password")
        assertTrue(result is Result.Error)
    }

    @Test
    fun emptyPassword_ResultError() = runTest{
        val result = logInUseCase(email = "email@email.com", password = "")
        assertTrue(result is Result.Error)
    }

    @Test
    fun invalidEmail_ResultError() = runTest{
        val result = logInUseCase(email = "email", password = "password")
        assertTrue(result is Result.Error)
    }

    @Test
    fun correctData_ResultSuccess() = runTest{
        val result = logInUseCase(email = "email@email.com", password = "password")
        println(result.toString())
        assertTrue(result is Result.Success)
    }


}