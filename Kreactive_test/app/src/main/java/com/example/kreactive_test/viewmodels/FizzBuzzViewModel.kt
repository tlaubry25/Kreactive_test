package com.example.kreactive_test.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kreactive_test.database.FizzBuzzData
import com.example.kreactive_test.database.FizzBuzzDatabase
import com.example.kreactive_test.database.FizzBuzzRepository
import com.example.kreactive_test.databinding.FizzbuzzResultBinding


class FizzBuzzViewModel(application: Application):AndroidViewModel(application) {

//    val allFizzBuzzDatas : LiveData<List<FizzBuzzData>>
//    val repository : FizzBuzzRepository
    private lateinit var binding: FizzbuzzResultBinding

    init {
//        val dao = FizzBuzzDatabase.getInstance(application)!!.fizzFuzzDao()
//        repository = FizzBuzzRepository(dao)
//        allFizzBuzzDatas = repository.getAll()

    }


}