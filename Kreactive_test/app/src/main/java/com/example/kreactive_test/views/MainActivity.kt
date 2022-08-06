package com.example.kreactive_test.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.kreactive_test.R
import com.example.kreactive_test.database.FizzBuzzData
//import com.example.kreactive_test.database.FizzBuzzDatabase
import com.example.kreactive_test.database.FizzBuzzRepository
import com.example.kreactive_test.viewmodels.SurveyViewModel
import com.example.kreactive_test.databinding.SurveyActivityBinding
import com.example.kreactive_test.models.FizzbuzzDataList
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: SurveyActivityBinding
    private var surveyViewModel : SurveyViewModel? = null
    lateinit var repository : FizzBuzzRepository
    lateinit var surveyCallBack : SurveyViewModel.SurveyCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.survey_activity)
        binding = SurveyActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        surveyViewModel = ViewModelProvider(this)[SurveyViewModel::class.java]



        surveyCallBack = object : SurveyViewModel.SurveyCallback{
            override fun insertInDb(data: List<FizzBuzzData>) {
//                val dao = FizzBuzzDatabase.getInstance(application)!!.fizzFuzzDao()
//                repository = FizzBuzzRepository(dao)
//                repository.insertAll(data)
                val newActivity = Intent(applicationContext, FizzBuzzResultActivity::class.java)
                val gson = Gson()
                val jsonData = gson.toJson(FizzbuzzDataList(data))
                newActivity.putExtra("jsonData", jsonData)
                startActivity(newActivity)
            }
        }
        surveyViewModel?.setUpListener(surveyCallBack)

        binding.processButton.setOnClickListener {
            surveyViewModel?.checkThenGetParameters()
        }

        binding.tiedFirstNumber.doAfterTextChanged {
            val isFormatCorrect = surveyViewModel?.isFormatCorrect(it!!, SurveyViewModel.ExpectedType.NUMBER)
            if(isFormatCorrect!!){
                surveyViewModel?.firstNumberOK = true
                surveyViewModel?.fizzBuzzParameters!!.number1 = it.toString().toInt()
                val enableProcessButton = surveyViewModel?.checkAllData()
                    binding.processButton.isEnabled = enableProcessButton!!
            }else{
                binding.processButton.isEnabled = false
                surveyViewModel?.firstNumberOK = false
                binding.tiedFirstNumber.setError(getText(R.string.incorrect_number))
            }
        }
        binding.tiedSecondNumber.doAfterTextChanged {
            val isFormatCorrect = surveyViewModel?.isFormatCorrect(it!!, SurveyViewModel.ExpectedType.NUMBER)
            if(isFormatCorrect!!){
                surveyViewModel?.secondNumberOK = true
                surveyViewModel?.fizzBuzzParameters!!.number2 = it.toString().toInt()
                val enableProcessButton = surveyViewModel?.checkAllData()
                binding.processButton.isEnabled = enableProcessButton!!
            }else{
                binding.processButton.isEnabled = false
                surveyViewModel?.secondNumberOK = false
                binding.tiedSecondNumber.setError(getText(R.string.incorrect_number))
            }
        }
        binding.tiedLimitNumber.doAfterTextChanged {
            val isFormatCorrect = surveyViewModel?.isFormatCorrect(it!!, SurveyViewModel.ExpectedType.NUMBER)
            if(isFormatCorrect!!){
                surveyViewModel?.limitNumberOK = true
                surveyViewModel?.fizzBuzzParameters!!.limit = it.toString().toInt()
                val enableProcessButton = surveyViewModel?.checkAllData()
                binding.processButton.isEnabled = enableProcessButton!!
            }else{
                binding.processButton.isEnabled = false
                surveyViewModel?.limitNumberOK = false
                binding.tiedLimitNumber.setError(getText(R.string.incorrect_number))
            }
        }
        binding.tiedFirstText.doAfterTextChanged {
            val isFormatCorrect = surveyViewModel?.isFormatCorrect(it!!, SurveyViewModel.ExpectedType.TEXT)
            if(isFormatCorrect!!){
                surveyViewModel?.firstWordOK = true
                surveyViewModel?.fizzBuzzParameters!!.text1 = it.toString()
                val enableProcessButton = surveyViewModel?.checkAllData()
                binding.processButton.isEnabled = enableProcessButton!!
            }else{
                binding.processButton.isEnabled = false
                surveyViewModel?.firstWordOK = false
                binding.tiedFirstText.setError(getText(R.string.empty_text))
            }
        }
        binding.tiedSecondText.doAfterTextChanged {
            val isFormatCorrect = surveyViewModel?.isFormatCorrect(it!!, SurveyViewModel.ExpectedType.TEXT)
            if(isFormatCorrect!!){
                surveyViewModel?.secondWordOK = true
                surveyViewModel?.fizzBuzzParameters!!.text2 = it.toString()
                val enableProcessButton = surveyViewModel?.checkAllData()
                binding.processButton.isEnabled = enableProcessButton!!
            }else{
                binding.processButton.isEnabled = false
                surveyViewModel?.secondWordOK = false
                binding.tiedSecondText.setError(getText(R.string.empty_text))
            }
        }
    }




    }
