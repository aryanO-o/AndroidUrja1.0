package com.aryandadhich.urja10.ui.games.teamGames.cricket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpdateCricketGameViewModelFactory(private val eventId: String, private val teamAName: String, private val teamBName: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UpdateCricketGameViewModel::class.java)){
            return UpdateCricketGameViewModel(eventId, teamAName, teamBName) as T;
        }
        throw IllegalArgumentException("unknown view model class")
    }
}