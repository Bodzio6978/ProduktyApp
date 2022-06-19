package com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases

import com.gmail.bogumilmecel2.produkty.common.util.Result
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Item
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.object_box.ObjectBoxItem
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
import org.mockito.kotlin.argumentCaptor

@OptIn(ExperimentalCoroutinesApi::class)
internal class SaveItemToObjectBoxTest{

    @Mock
    private lateinit var itemsRepository: ItemsRepository

    private lateinit var saveItemToObjectBox: SaveItemToObjectBox
    private lateinit var closeable: AutoCloseable

    @Before
    fun setUp(){
        closeable = MockitoAnnotations.openMocks(this)
        itemsRepository = Mockito.mock(ItemsRepository::class.java)

        saveItemToObjectBox = SaveItemToObjectBox(
            itemsRepository = itemsRepository
        )
    }

    @Test
    fun passedItemList_EveryItemListTurnedToObjectBoxItem_ResultSuccess() = runTest{
        val onSuccessCapture = argumentCaptor<List<ObjectBoxItem>>()
        var objectBoxItems = listOf<ObjectBoxItem>()
        Mockito.`when`(itemsRepository.saveItemsToObjectBox(onSuccessCapture.capture())).thenReturn(Result.Success)
        val items = mutableListOf<Item>()
        for (i in 0..15){
            items.add(Item())
        }
        val result = saveItemToObjectBox(items)
        objectBoxItems =  onSuccessCapture.firstValue
        assert(objectBoxItems.size==items.size)
        assertTrue(result is Result.Success)

    }

    @After
    fun after(){
        closeable.close()
    }
}