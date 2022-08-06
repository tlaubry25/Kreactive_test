package com.example.kreactive_test.views
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kreactive_test.viewmodels.FizzBuzzViewModel
import com.example.kreactive_test.R
import com.example.kreactive_test.viewmodels.FizzbuzzDataList
import com.google.gson.Gson

class FizzBuzzResultActivity : AppCompatActivity() {

    var fizzBuzzViewModel : FizzBuzzViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fizzbuzz_result)
        var datas = intent.getStringExtra("jsonData")
        val list = Gson().fromJson(datas, FizzbuzzDataList::class.java)
        println()

        
        //fizzBuzzViewModel = ViewModelProvider(this)[FizzBuzzViewModel::class.java]


    }


}

