package com.example.apitestapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ContentConverter {

    @TypeConverter
    fun stringToList(mutableList: MutableList<String?>?): String? {
        val type = object : TypeToken<MutableList<String?>?>() {}.type
        return Gson().toJson(mutableList, type)
    }

    @TypeConverter
    fun listToString(string: String?): MutableList<String?>? {
        val type = object : TypeToken<MutableList<String?>?>() {}.type
        return Gson().fromJson(string, type)
    }
}