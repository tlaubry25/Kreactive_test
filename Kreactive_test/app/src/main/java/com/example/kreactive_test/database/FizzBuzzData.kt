package com.example.kreactive_test.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FizzBuzz")
data class FizzBuzzData(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val value : String
)