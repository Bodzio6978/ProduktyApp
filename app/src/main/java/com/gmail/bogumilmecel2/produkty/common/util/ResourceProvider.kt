package com.gmail.bogumilmecel2.produkty.common.util

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

class ResourceProvider(private val context: Context) {

    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }

    fun getStringArray(@ArrayRes stringArrayId:Int):List<String>{
        return context.resources.getStringArray(stringArrayId).toList()
    }
}