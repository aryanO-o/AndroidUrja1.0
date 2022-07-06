package com.aryandadhich.urja10.ui.games.teamGames.tennis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpdateTennisGameViewModelFactory (private val eventId: String, private val teamAName: String, private val teamBName: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UpdateTennisGameViewModel::class.java)){
            return UpdateTennisGameViewModel(eventId, teamAName, teamBName) as T;
        }
        throw IllegalArgumentException("unknown view model class")
    }
}