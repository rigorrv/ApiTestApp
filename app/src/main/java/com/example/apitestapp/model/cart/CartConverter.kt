package com.example.apitestapp.model.cart

import androidx.room.TypeConverter
import com.example.apitestapp.model.content.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartConverter {

    @TypeConverter
    fun stringToList(string: String?): MutableList<Result?>? {
        val type = object : TypeToken<MutableList<Result?>?>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun listToString(mutableList: MutableList<Result?>?): String? {
        val type = object : TypeToken<MutableList<Result?>?>() {}.type
        return Gson().toJson(mutableList, type)
    }
}