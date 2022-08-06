package com.example.kreactive_test.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kreactive_test.database.models.FizzBuzzParameters
import com.example.kreactive_test.database.FizzBuzzData

class SurveyViewModel:ViewModel() {


    lateinit var listener : SurveyCallback
    var fizzBuzzParameters = FizzBuzzParameters()

    fun setUpListener(callback: SurveyCallback){
        listener = callback
    }

    fun surveyFirstWordChange(value: String){
        fizzBuzzParameters.text1 = value
    }

    fun surveySecondWordChange(value: String){
        fizzBuzzParameters.text2 = value
    }

    fun surveyFirstValueChange(value: Int){
        fizzBuzzParameters.number1 = value
    }

    fun surveySecondValueChange(value: Int){
        fizzBuzzParameters.number2 = value
    }

    fun surveyLimitValueChange(value: Int){
        fizzBuzzParameters.limit = value
    }

    fun checkThenGetParameters(){
        if(
            isFirstNumberCorrect(fizzBuzzParameters.number1) &&
            isSecondNumberCorrect(fizzBuzzParameters.number2) &&
            isLimitNumberCorrect(fizzBuzzParameters.limit) &&
            isFirstNumberCorrect(fizzBuzzParameters.number1) &&
            isSecondNumberCorrect(fizzBuzzParameters.number2)){
                getParameters()
        }else{

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

    fun isFirstNumberCorrect(input:Any):Boolean{
        val isCorrect : Boolean

            surveyFirstValueChange(input as Int)
            if(input > 0){
                isCorrect = true
                listener?.firstNumberCheck(isCorrect)
            }else{
                isCorrect = false
                listener?.firstNumberCheck(isCorrect)
            }
        return isCorrect
    }

    fun isSecondNumberCorrect(input:Any):Boolean{
        val isCorrect : Boolean
            surveySecondValueChange(input as Int)
            if(input >0){
                isCorrect = true
                listener?.secondNumberCheck(isCorrect)
            }else{
                isCorrect = false
                listener?.secondNumberCheck(isCorrect)
            }

        return isCorrect
    }

    fun isLimitNumberCorrect(input:Any):Boolean{
        val isCorrect : Boolean

            surveyLimitValueChange(input as Int)
            if(input >0){
                isCorrect = true
                listener?.limitNumberCheck(isCorrect)
            }else{
                isCorrect = false
                listener?.limitNumberCheck(isCorrect)
            }
        return isCorrect
    }

    fun isFirstTextCorrect(input:String):Boolean{
        val isCorrect : Boolean
        surveyFirstWordChange(input)
        if(input != ""){
            isCorrect = true
            listener?.firstTextCheck(isCorrect)
        }else{
            isCorrect = false
            listener?.firstTextCheck(isCorrect)
        }
        return isCorrect
    }

    fun isSecondTextCorrect(input:String):Boolean{
        val isCorrect : Boolean
        surveySecondWordChange(input)
        if(input != ""){
            isCorrect = true
            listener?.secondTextCheck(isCorrect)
        }else{
            isCorrect = false
            listener?.secondTextCheck(isCorrect)
        }
        return isCorrect
    }

    interface SurveyCallback{
        fun insertInDb(data : List<FizzBuzzData>)
        fun firstNumberCheck(correct : Boolean)
        fun secondNumberCheck(correct : Boolean)
        fun limitNumberCheck(correct : Boolean)
        fun firstTextCheck(correct : Boolean)
        fun secondTextCheck(correct : Boolean)
        fun warningParameters()
    }
}

