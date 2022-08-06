package com.example.kreactive_test.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.kreactive_test.R
import com.example.kreactive_test.database.FizzBuzzData
import com.example.kreactive_test.database.FizzBuzzDatabase
//import com.example.kreactive_test.database.FizzBuzzDatabase
import com.example.kreactive_test.database.FizzBuzzRepository
import com.example.kreactive_test.viewmodels.SurveyViewModel
import com.example.kreactive_test.databinding.SurveyActivityBinding
import com.example.kreactive_test.viewmodels.FizzbuzzDataList
import com.google.gson.Gson
import com.google.gson.GsonBuilder

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

            override fun firstNumberCheck(correct: Boolean) {
                if(!correct){
                    binding.etFirstNumber.tooltipText = getText(R.string.incorrect_number)
                }
            }

            override fun secondNumberCheck(correct: Boolean) {
                if(!correct){
                    binding.etSecondNumber.tooltipText = getText(R.string.incorrect_number)
                }
            }

            override fun limitNumberCheck(correct: Boolean) {
                if(!correct){
                    binding.etLimite.tooltipText = getText(R.string.incorrect_number)
                }
            }

            override fun firstTextCheck(correct: Boolean) {
                if(!correct){
                    binding.etFirstWord.tooltipText = getText(R.string.empty_text)
                }
            }

            override fun secondTextCheck(correct: Boolean) {
                if(!correct){
                    binding.etSecondWord.tooltipText = getText(R.string.empty_text)
                }
            }

            override fun warningParameters() {
                Toast.makeText(applicationContext, getText(R.string.incorrect_values), Toast.LENGTH_SHORT)
            }
        }
        surveyViewModel?.setUpListener(surveyCallBack)

        binding.processButton.setOnClickListener {
            surveyViewModel?.checkThenGetParameters()
        }

        binding.etFirstNumber.doAfterTextChanged {
            surveyViewModel?.isFirstNumberCorrect(binding.etFirstNumber.text.toString().toInt())
        }
        binding.etSecondNumber.doAfterTextChanged {
            surveyViewModel?.isSecondNumberCorrect(binding.etSecondNumber.text.toString().toInt())
        }
        binding.etLimite.doAfterTextChanged {
            surveyViewModel?.isLimitNumberCorrect(binding.etLimite.text.toString().toInt())
        }
        binding.etFirstWord.doAfterTextChanged {
            surveyViewModel?.isFirstTextCorrect(binding.etFirstWord.text.toString())
        }
        binding.etSecondWord.doAfterTextChanged {
            surveyViewModel?.isSecondTextCorrect(binding.etSecondWord.text.toString())
        }
    }




    }
