package com.aryandadhich.urja10.ui.games.individualGames.carrom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpdateCarromGameViewModelFactory(private val eventId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UpdateCarromGameViewModel::class.java)){
            return UpdateCarromGameViewModel(eventId) as T;
        }
        throw IllegalArgumentException("unknown view model class")
    }
}