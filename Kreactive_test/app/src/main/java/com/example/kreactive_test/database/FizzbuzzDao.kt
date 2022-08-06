package com.example.kreactive_test.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FizzbuzzDao {

    @Query("SELECT * FROM FizzBuzz ORDER BY id ASC")
    fun getAll(): LiveData<List<FizzBuzzData>>

    @Query("SELECT * FROM FizzBuzz WHERE id IN (:fizzBuzzIds) ORDER BY id ASC")
    fun loadAllByIds(fizzBuzzIds: IntArray): LiveData<List<FizzBuzzData>>

    @Insert
    fun insertAll(fizzBuzzData: List<FizzBuzzData>)

    @Query("DELETE FROM FizzBuzz")
    fun deleteAll()
}