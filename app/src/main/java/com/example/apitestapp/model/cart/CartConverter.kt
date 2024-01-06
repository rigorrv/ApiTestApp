package com.example.apitestapp.model.cart

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartConverter {

    @TypeConverter
    fun stringToList(string: String?): MutableList<Cart?>? {
        val type = object : TypeToken<MutableList<Cart?>?>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun listToString(mutableList: MutableList<Cart?>?): String? {
        val type = object : TypeToken<MutableList<Cart?>?>() {}.type
        return Gson().toJson(mutableList, type)
    }
}