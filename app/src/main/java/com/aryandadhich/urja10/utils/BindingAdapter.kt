package com.aryandadhich.urja10.utils

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.aryandadhich.urja10.ui.coordinator.Coordinator
import com.aryandadhich.urja10.ui.coordinator.CoordinatorAdapter
import com.aryandadhich.urja10.ui.eventCoordinator.EventCoordinator
import com.aryandadhich.urja10.ui.eventCoordinator.EventCoordinatorAdapter
import com.aryandadhich.urja10.ui.forms.AvailableFormsAdapter
import com.aryandadhich.urja10.ui.forms.FillForm
import com.aryandadhich.urja10.ui.forms.Form
import com.aryandadhich.urja10.ui.forms.GetFilledFormAdapter
import com.aryandadhich.urja10.ui.houseCaptain.HouseCaptain
import com.aryandadhich.urja10.ui.houseCaptain.HouseCaptainAdapter

@BindingAdapter("houseCaptainListData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<HouseCaptain>?){
    val adapter = recyclerView.adapter as HouseCaptainAdapter
    adapter.submitList(data)
}


@BindingAdapter("coordinatorListData")
fun bindRecyclerViewOfCoordinator(recyclerView: RecyclerView, data: List<Coordinator>?){
    val adapter = recyclerView.adapter as CoordinatorAdapter
    adapter.submitList(data)
}


@BindingAdapter("eventCoordinatorListData")
fun bindRecyclerViewOfEventCoordinator(recyclerView: RecyclerView, data: List<EventCoordinator>?){
    val adapter = recyclerView.adapter as EventCoordinatorAdapter
    adapter.submitList(data)
}

@BindingAdapter("availableFormListData")
fun bindRecyclerViewOfAvailableForms(recyclerView: RecyclerView, data: List<Form>?){
    val adapter = recyclerView.adapter as AvailableFormsAdapter
    adapter.submitList(data)
}


@BindingAdapter("filledFormListData")
fun bindRecyclerViewOfFilledforms(recyclerView: RecyclerView, data: List<FillForm>?){
    val adapter = recyclerView.adapter as GetFilledFormAdapter
    adapter.submitList(data)
}
