package com.example.apitestapp.model.content

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Content")
data class MovieDB(
    @PrimaryKey
    val id: Int = 0,
    val page: Int? = null,
    val results: List<Content>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)