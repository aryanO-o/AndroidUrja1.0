package com.aryandadhich.urja10.ui.games.common.players

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PlayerFragmentViewModelFactory (private val teamId: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PlayerFragmentViewModel::class.java)){
            return PlayerFragmentViewModel(teamId) as T;
        }
        throw IllegalArgumentException("unknown view model class")
    }
}