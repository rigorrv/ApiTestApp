package com.example.apitestapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Steppers(
    @PrimaryKey
    @NonNull
    val id: Int? = 1,
    val stepper: List<Stepper>
)
