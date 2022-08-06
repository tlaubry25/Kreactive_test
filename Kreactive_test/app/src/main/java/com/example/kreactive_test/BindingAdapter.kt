package com.example.kreactive_test

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("itemViewModels")
fun bindItemViewModels(recyclerView: RecyclerView, itemViewModels: List<ItemViewModel>?) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.updateItems(itemViewModels)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView): FizzBuzzBindableViewAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is FizzBuzzBindableViewAdapter) {
        recyclerView.adapter as FizzBuzzBindableViewAdapter
    } else {
        val fizzbuzzBindableRecyclerAdapter = FizzBuzzBindableViewAdapter()
        recyclerView.adapter = fizzbuzzBindableRecyclerAdapter
        fizzbuzzBindableRecyclerAdapter
    }
}