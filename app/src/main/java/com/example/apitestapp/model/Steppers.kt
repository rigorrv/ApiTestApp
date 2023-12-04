package com.example.apitestapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Steppers(
    @PrimaryKey
    val id: Int = 0,
    @NonNull
    val stepper: MutableList<Int>?
)