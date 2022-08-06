package com.example.kreactive_test.views
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kreactive_test.FizzBuzzAdapter
import com.example.kreactive_test.viewmodels.FizzBuzzViewModel
import com.example.kreactive_test.R
import com.example.kreactive_test.database.FizzBuzzData
import com.example.kreactive_test.databinding.FizzbuzzResultBinding
import com.example.kreactive_test.models.FizzbuzzDataList
import com.google.gson.Gson
import java.util.ArrayList

class FizzBuzzResultActivity : AppCompatActivity() {
    private lateinit var binding: FizzbuzzResultBinding
    var fizzBuzzViewModel : FizzBuzzViewModel? = null
    private var fizzBuzzAdapter : FizzBuzzAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fizzbuzz_result)
        var datas = intent.getStringExtra("jsonData")
        val list = Gson().fromJson(datas, FizzbuzzDataList::class.java)
        println()

        binding = FizzbuzzResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        fizzBuzzViewModel = ViewModelProvider(this)[FizzBuzzViewModel::class.java]
        //fizzBuzzViewModel = FizzBuzzViewModel(list)

        val recyclerView = binding.rvFizzbuzzList
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.setHasFixedSize(true)

        fizzBuzzAdapter = FizzBuzzAdapter()
        fizzBuzzAdapter!!.setFizzBuzzDataList(list.listData as ArrayList<FizzBuzzData>)

        recyclerView.adapter = fizzBuzzAdapter

        val dividerItemDecoration = DividerItemDecoration(this, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { drawable -> dividerItemDecoration.setDrawable(drawable) }
        binding.rvFizzbuzzList.addItemDecoration(dividerItemDecoration)


    }


}

