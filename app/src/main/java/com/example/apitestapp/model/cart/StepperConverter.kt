package com.example.apitestapp.model.cart

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StepperConverter {

    @TypeConverter
    fun stringToList(string: String?): MutableList<Int?>? {
        val type = object : TypeToken<MutableList<Int?>?>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun listToString(mutableList: MutableList<Int?>?): String? {
        val type = object : TypeToken<MutableList<Int?>?>() {}.type
        return Gson().toJson(mutableList, type)
    }
}