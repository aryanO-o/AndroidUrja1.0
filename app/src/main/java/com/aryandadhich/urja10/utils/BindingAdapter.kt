package com.aryandadhich.urja10.utils

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.ui.houseCaptain.HouseCaptain
import com.aryandadhich.urja10.ui.houseCaptain.HouseCaptainAdapter

@BindingAdapter("houseCaptainListData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<HouseCaptain>?){
    val adapter = recyclerView.adapter as HouseCaptainAdapter
    adapter.submitList(data)
}