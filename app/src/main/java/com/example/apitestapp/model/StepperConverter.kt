package com.example.apitestapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StepperConverter {

    @TypeConverter
    fun stringToVideo(string: String?): List<Stepper>? {
        val type = object : TypeToken<List<Stepper?>?>() {}.type
        return Gson().fromJson(string, type)
    }

    @TypeConverter
    fun videoToString(video: List<Stepper?>?): String? {
        val type = object : TypeToken<List<Stepper?>?>() {}.type
        return Gson().toJson(video, type)
    }
}