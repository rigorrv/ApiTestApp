package com.example.apitestapp.model.content

import androidx.room.TypeConverter
import com.example.apitestapp.model.cart.Cart
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResultConverter {


    @TypeConverter
    fun stringToResult(string: String?): MutableList<Cart?>? {
        val type = object : TypeToken<MutableList<Cart?>?>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun resultToString(mutableList: MutableList<Cart?>?): String? {
        val type = object : TypeToken<MutableList<Cart?>?>() {}.type
        return Gson().toJson(mutableList, type)
    }
}