package com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases

import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.feature_items.domain.repository.ItemsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
internal class GetAccessTokenTest{

    @Mock
    private lateinit var itemsRepository: ItemsRepository

    private lateinit var getAccessToken: GetAccessToken

    private lateinit var closeable: AutoCloseable

    @Before
    fun setUp(){
        closeable = MockitoAnnotations.openMocks(this)
        itemsRepository = Mockito.mock(ItemsRepository::class.java)

        getAccessToken = GetAccessToken(itemsRepository = itemsRepository)
    }

    @After
    fun after(){
        closeable.close()
    }

    @Test
    fun repositoryReturnedSuccess_ReturnedSuccess() = runTest{
        Mockito.`when`(itemsRepository.getAccessToken()).thenReturn(
            Resource.Success(data = null))

        val resource = getAccessToken()
        assertTrue(resource is Resource.Success)
    }
}