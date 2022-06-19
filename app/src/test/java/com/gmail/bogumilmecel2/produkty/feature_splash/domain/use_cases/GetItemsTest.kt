package com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases

import com.gmail.bogumilmecel2.produkty.common.domain.model.AccessToken
import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.feature_items.domain.repository.ItemsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetItemsTest{

    @Mock
    private lateinit var itemsRepository: ItemsRepository

    private lateinit var getItems: GetItems
    private lateinit var closeable: AutoCloseable

    @Before
    fun setUp(){
        closeable = MockitoAnnotations.openMocks(this)
        itemsRepository = Mockito.mock(ItemsRepository::class.java)

        getItems = GetItems(
            itemsRepository = itemsRepository,
        )
    }

    @Test
    fun repositoryReturnedSuccess_ReturnedSuccess() = runTest{
        Mockito.`when`(itemsRepository.getItems(any())).thenReturn(
            Resource.Success(data = null))

        val resource = getItems(AccessToken())
        assertTrue(resource is Resource.Success)
    }

    @After
    fun after(){
        closeable.close()
    }
}