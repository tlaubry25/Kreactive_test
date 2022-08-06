package com.example.kreactive_test.database

import androidx.lifecycle.LiveData


class FizzBuzzRepository(private val fizzbuzzDao : FizzbuzzDao) {


    fun getAll():LiveData<List<FizzBuzzData>> {
        return fizzbuzzDao.getAll()
    }


    suspend fun loadAllByIds(fizzBuzzIds: IntArray):LiveData<List<FizzBuzzData>> {
        return fizzbuzzDao.loadAllByIds(fizzBuzzIds)
    }

    fun insertAll(fizzBuzzData: List<FizzBuzzData>){
        fizzbuzzDao.insertAll(fizzBuzzData)
    }

    suspend fun deleteAllFizzBuzzData(){
        fizzbuzzDao.deleteAll()
    }
}