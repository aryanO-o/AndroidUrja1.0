package com.aryandadhich.urja10.ui.games.teamGames.tableTennis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UpdateTableTennisGameViewModelFactory (private val eventId: String, private val teamAName: String, private val teamBName: String): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(UpdateTableTennisGameViewModel::class.java)){
            return UpdateTableTennisGameViewModel(eventId, teamAName, teamBName) as T;
        }
        throw IllegalArgumentException("unknown view model class")
    }
}