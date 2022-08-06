package com.example.kreactive_test.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FizzBuzzData::class], version = 1, exportSchema = false)
abstract class FizzBuzzDatabase : RoomDatabase() {

    abstract fun fizzFuzzDao() : FizzbuzzDao

    companion object {
        private var INSTANCE: FizzBuzzDatabase? = null

        fun getInstance(context: Context): FizzBuzzDatabase? {
            if (INSTANCE == null) {
                synchronized(FizzBuzzDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        FizzBuzzDatabase::class.java, "fizzbuzz.db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}