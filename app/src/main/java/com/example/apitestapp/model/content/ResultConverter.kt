package com.example.apitestapp.model.content

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResultConverter {


    @TypeConverter
    fun stringToResult(string: String?): MutableList<Result?>? {
        val type = object : TypeToken<MutableList<Result?>?>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun resultToString(mutableList: MutableList<Result?>?): String? {
        val type = object : TypeToken<MutableList<Result?>?>() {}.type
        return Gson().toJson(mutableList, type)
    }
}