package com.example.kreactive_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kreactive_test.database.FizzBuzzData
import com.example.kreactive_test.databinding.FizzbuzzListItemBinding


class FizzBuzzBindableViewAdapter : RecyclerView.Adapter<FizzBuzzBindableViewAdapter.FizzBuzzBindableViewHolder>(){

        var itemViewModels: List<ItemViewModel> = emptyList()
        private val viewTypeToLayoutId: MutableMap<Int, Int> = mutableMapOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
            val binding: ViewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewTypeToLayoutId[viewType] ?: 0,
                parent,
                false)
            return BindableViewHolder(binding)
        }

        override fun getItemViewType(position: Int): Int {
            val item = itemViewModels[position]
            if (!viewTypeToLayoutId.containsKey(item.viewType)) {
                viewTypeToLayoutId[item.viewType] = item.layoutId
            }
            return item.viewType
        }

        override fun getItemCount(): Int = itemViewModels.size

        override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
            holder.bind(itemViewModels[position])
        }

        fun updateItems(items: List<ItemViewModel>?) {
            itemViewModels = items ?: emptyList()
            notifyDataSetChanged()
        }
    }

    class FizzBuzzBindableViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemViewModel: ItemViewModel) {
            binding.setVariable(BR.itemViewModel, itemViewModel)
        }
    }
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