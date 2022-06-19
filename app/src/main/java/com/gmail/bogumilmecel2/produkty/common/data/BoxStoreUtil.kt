package com.gmail.bogumilmecel2.produkty.common.data

import android.app.Application
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.object_box.MyObjectBox
import io.objectbox.BoxStore

class BoxStoreUtil(
    private val app:Application
) {
    init {
        initializeBoxStore()
    }

    companion object{
        lateinit var boxStore:BoxStore
    }

    private fun initializeBoxStore(){
        boxStore = MyObjectBox.builder().androidContext(app).name("DB").build()
    }

    fun clearBoxStore(){
        boxStore.close()
        boxStore.deleteAllFiles()
        initializeBoxStore()
    }

    fun getBoxStore():BoxStore = boxStore

}