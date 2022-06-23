package com.aryandadhich.urja10.ui.houseCaptain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HouseCaptainEditViewModelFactory(private val loginId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HouseCaptainEditViewModel::class.java)){
            return HouseCaptainEditViewModel(loginId) as T;
        }
        throw IllegalArgumentException("unknown view model class")
    }
}