package com.aryandadhich.urja10.ui.games.common.gameInfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameInfoViewModelFactory(private val eventId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameInfoViewModel::class.java)){
            return GameInfoViewModel(eventId) as T;
        }
        throw IllegalArgumentException("unknown view model class")
    }
}