package com.example.kreactive_test.viewmodels

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.example.kreactive_test.models.FizzBuzzParameters
import com.example.kreactive_test.database.FizzBuzzData

class SurveyViewModel:ViewModel() {
    enum class ExpectedType{NUMBER, TEXT}

    lateinit var listener : SurveyCallback
    var fizzBuzzParameters = FizzBuzzParameters()
    var firstNumberOK = false
    var secondNumberOK = false
    var limitNumberOK = false
    var firstWordOK = false
    var secondWordOK = false

    fun setUpListener(callback: SurveyCallback){
        listener = callback
    }

    fun checkThenGetParameters(){
        if(checkAllData()){
                getParameters()
        }
    }

    fun getParameters(){
        var listDatas = mutableListOf<FizzBuzzData>()

        for( i in 1..fizzBuzzParameters.limit){
            val result : String =
                when{
                    i % (fizzBuzzParameters.number1*fizzBuzzParameters.number2) == 0 -> fizzBuzzParameters.text1+fizzBuzzParameters.text2
                    i % fizzBuzzParameters.number1 == 0 -> fizzBuzzParameters.text1
                    i % fizzBuzzParameters.number2 == 0 -> fizzBuzzParameters.text2
                    else -> "$i"
                }
            var data = FizzBuzzData(i, result)
            listDatas.add(data)
        }

        listener?.insertInDb(listDatas)
    }
    fun isFormatCorrect(input : Editable, typeExpected : ExpectedType):Boolean{
        var isCorrect = false
        when(typeExpected){
            ExpectedType.NUMBER->{
                val inputText = input.toString()
                if(input.isNotEmpty()){
                    isCorrect = inputText.toInt() > 0
                }else{
                    isCorrect = false
                }

            }
            ExpectedType.TEXT->{
                isCorrect = input.isNotEmpty()
            }
        }
        return isCorrect
    }

    fun checkAllData():Boolean{
        return firstNumberOK && secondNumberOK && limitNumberOK && firstWordOK && secondWordOK
    }

    interface SurveyCallback{
        fun insertInDb(data : List<FizzBuzzData>)

    }
}

