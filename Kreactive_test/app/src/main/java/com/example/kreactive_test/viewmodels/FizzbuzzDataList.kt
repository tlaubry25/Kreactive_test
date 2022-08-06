package com.example.kreactive_test.viewmodels

import com.example.kreactive_test.database.FizzBuzzData

class FizzbuzzDataList {
    var listData = emptyList<FizzBuzzData>()

    constructor(list: List<FizzBuzzData>){
        listData = list
    }
}