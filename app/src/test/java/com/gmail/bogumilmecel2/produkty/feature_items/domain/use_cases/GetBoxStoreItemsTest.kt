package com.gmail.bogumilmecel2.produkty.feature_items.domain.use_cases

import com.gmail.bogumilmecel2.produkty.common.util.Resource
import com.gmail.bogumilmecel2.produkty.common.util.Result
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Item
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.object_box.ObjectBoxItem
import com.gmail.bogumilmecel2.produkty.feature_items.domain.repository.ItemsRepository
import com.gmail.bogumilmecel2.produkty.feature_splash.domain.use_cases.SaveItemToObjectBox
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetBoxStoreItemsTest{

    @Mock
    private lateinit var itemsRepository: ItemsRepository

    private lateinit var getBoxStoreItems: GetBoxStoreItems
    private lateinit var closeable: AutoCloseable

    @Before
    fun setUp(){
        closeable = MockitoAnnotations.openMocks(this)
        itemsRepository = Mockito.mock(ItemsRepository::class.java)

        getBoxStoreItems = GetBoxStoreItems(
            itemsRepository = itemsRepository
        )
    }

    @Test
    fun getObjectBoxItems_EveryObjectBoxItemTurnedToItem_ResultSuccess() = runTest{
        val objectBoxItems = mutableListOf<ObjectBoxItem>()
        for (i in 0..15){
            objectBoxItems.add(ObjectBoxItem())
        }
        Mockito.`when`(itemsRepository.getObjectBoxItems()).thenReturn(Resource.Success(data = objectBoxItems))
        val resource = getBoxStoreItems()
        assert(resource is Resource.Success)
        assert(resource.data!!.size==objectBoxItems.size)
    }

    @After
    fun after(){
        closeable.close()
    }
}