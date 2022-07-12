package com.aryandadhich.urja10.ui.games.individualGames.chess

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpdateChessGameViewModelFactory(private val eventId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UpdateChessGameViewModel::class.java)){
            return UpdateChessGameViewModel(eventId) as T;
        }
        throw IllegalArgumentException("unknown view model class")
    }
}