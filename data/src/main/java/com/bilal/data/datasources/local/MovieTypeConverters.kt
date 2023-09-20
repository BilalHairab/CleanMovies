package com.bilal.data.datasources.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Bilal Hairab on 21/09/2023.
 */
object MovieTypeConverters {
    @TypeConverter
    fun intListToString(array: List<Int>) = Gson().toJson(array)

    @TypeConverter
    fun stringTointList(string: String): List<Int> {
        val itemType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(string, itemType)
    }
}