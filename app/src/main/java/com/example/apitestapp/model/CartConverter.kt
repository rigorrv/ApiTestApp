package com.example.apitestapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartConverter {


    @TypeConverter
    fun stringToVideo(string: String?): MutableSet<Result>? {
        val type = object : TypeToken<MutableSet<Result?>?>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun videoToString(video: MutableSet<Result?>?): String? {
        val type = object : TypeToken<MutableSet<Result?>?>() {}.type
        return Gson().toJson(video, type)
    }
}