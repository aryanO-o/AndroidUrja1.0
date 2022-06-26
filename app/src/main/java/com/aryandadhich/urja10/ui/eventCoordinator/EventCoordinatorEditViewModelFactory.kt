package com.aryandadhich.urja10.ui.eventCoordinator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EventCoordinatorEditViewModelFactory(private val loginId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EventCoordinatorEditViewModel::class.java)){
            return EventCoordinatorEditViewModel(loginId) as T;
        }
        throw IllegalArgumentException("unknown view model class")
    }
}