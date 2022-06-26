package com.aryandadhich.urja10.ui.coordinator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoordinatorEditViewModelFactory (private val loginId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CoordinatorEditViewModel::class.java)){
            return CoordinatorEditViewModel(loginId) as T;
        }
        throw IllegalArgumentException("unknown view model class")
    }
}