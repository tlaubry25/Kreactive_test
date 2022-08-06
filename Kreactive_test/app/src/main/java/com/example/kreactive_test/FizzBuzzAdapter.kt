package com.example.kreactive_test


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kreactive_test.database.FizzBuzzData
import java.util.ArrayList
import androidx.databinding.DataBindingUtil
import com.example.kreactive_test.databinding.FizzbuzzListItemBinding


class FizzBuzzAdapter : RecyclerView.Adapter<FizzBuzzAdapter.FizzBuzzViewHolder>(){

    private var fizzBuzzList: ArrayList<FizzBuzzData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FizzBuzzAdapter.FizzBuzzViewHolder {
        var fizzbuzzListItemBinding = DataBindingUtil.inflate<FizzbuzzListItemBinding>(LayoutInflater.from(parent.context), R.layout.fizzbuzz_list_item, parent, false)

        return FizzBuzzViewHolder(fizzbuzzListItemBinding)
    }

    override fun onBindViewHolder(
        holder: FizzBuzzAdapter.FizzBuzzViewHolder,
        position: Int
    ) {
        var fizzBuzzdata = fizzBuzzList!![position]
        holder.fizzbuzzListItemBinding.tvIdItem.text = fizzBuzzdata.id.toString()
        holder.fizzbuzzListItemBinding.tvValueItem.text = fizzBuzzdata.value
    }

    override fun getItemCount(): Int {
        if(fizzBuzzList!=null){
            return fizzBuzzList!!.size
        }else{
            return 0
        }
    }

    fun setFizzBuzzDataList(dataList: ArrayList<FizzBuzzData>) {
        this.fizzBuzzList = dataList
        notifyDataSetChanged()
    }

    inner class FizzBuzzViewHolder(var fizzbuzzListItemBinding: FizzbuzzListItemBinding) :
        RecyclerView.ViewHolder(fizzbuzzListItemBinding.root)
}





//class FizzBuzzItemAdapter():RecyclerView.Adapter<FizzBuzzItemAdapter.FizzBuzzViewModel>() {
//    private val allFizzBuzzDatas = ArrayList<FizzBuzzData>()
//
//
//    private lateinit var binding: FizzbuzzListItemBinding
//    inner class FizzBuzzViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        init {
//            binding = FizzbuzzListItemBinding.inflate(LayoutInflater.from(itemView.context))
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FizzBuzzViewHolder {
//        return FizzBuzzViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fizzbuzz_list_item, parent, false))
//    }
//
//    override fun onBindViewHolder(holder: FizzBuzzViewHolder, position: Int) {
//        holder.binding. = allFizzBuzzDatas.get(position).id
//        holder.binding.tv_value_item = allFizzBuzzDatas.get(position).value
//    }
//
//    override fun getItemCount(): Int {
//    }
//}
//class FizzBuzzViewHolder(val binding: FizzBuzzItemBinding) : RecyclerView.ViewHolder(binding.root) {}