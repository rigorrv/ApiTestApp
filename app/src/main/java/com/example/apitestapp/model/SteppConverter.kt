package com.example.apitestapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SteppConverter {


    @TypeConverter
    fun stringToVideo(string: String?): MutableList<Int>? {
        val type = object : TypeToken<MutableList<Int?>?>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun videoToString(video: MutableList<Int?>?): String? {
        val type = object : TypeToken<MutableList<Int?>?>() {}.type
        return Gson().toJson(video, type)
    }
}