package com.aryandadhich.urja10.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EditNoticeViewModelFactory(private val loginId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EditNoticeViewModel::class.java)){
            return EditNoticeViewModel(loginId) as T;
        }
        throw IllegalArgumentException("unknown view model class")
    }
}