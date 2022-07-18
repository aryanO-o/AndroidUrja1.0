package com.aryandadhich.urja10.ui.games.teamGames.tennis

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class UpdateTennisGameViewModel(eventId: String, teamAName: String, teamBName: String): ViewModel() {

    private val _eventId: String

    var editTextTeamAScore by Delegates.notNull<Int>()
    var editTextTeamBScore by Delegates.notNull<Int>()

    var _teamAName = teamAName;
    var _teamBName = teamBName;

    private var _game = MutableLiveData<TennisGame>()
    val game: LiveData<TennisGame>
        get() = _game

    private var _navigation = MutableLiveData<Boolean>()
    val navigation: LiveData<Boolean>
        get() = _navigation;

    private var _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message;

    private var job = Job();
    private var coroutineScope = CoroutineScope(job + Dispatchers.Main);
    init {
        _eventId = eventId
        getTennisGame()
    }

    private fun getTennisGame() {
        coroutineScope.launch {
            val getTennisGameByIdDeferred = API.retrofitService.getTennisGameById(_eventId);
            try {
                val result = getTennisGameByIdDeferred.await()
                _game.value = result;
                _message.value = "fetch Successful"
            }
            catch (t: Throwable){
                _message.value = "error loading game data: " + t.toString();
                Log.i("UpdateTennisGameViewModel", _message.value.toString())
            }
        }
    }

    fun updateTennisGameScores(){
        coroutineScope.launch {
            val postTennisUpdateDeferred = API.retrofitService.updateTennisGame(_eventId, PostTennisScoreUpdates(editTextTeamAScore, editTextTeamBScore))
            try {
                val result = postTennisUpdateDeferred.await()
                _game.value = result;
                _message.value = "update successful"
                _navigation.value = true;
            }catch (t: Throwable){
                _message.value = "error updating game data: " + t.toString();
                Log.i("UpdateTennisGameViewModel", _message.value.toString())
            }
        }
    }

    fun deleteGame(){
        coroutineScope.launch {
            val deleteGameDeferred = API.retrofitService.deleteTennisGame(_eventId)
            try {
                val result = deleteGameDeferred.await()
                _message.value = "deleted successful"
                _navigation.value = true;
            }catch (t: Throwable){
                _message.value = "error updating game data: " + t.toString();
                Log.i("UpdateTennisGameViewModel", _message.value.toString())
            }
        }
    }

    fun doneNavigating(){
        _navigation.value = false;
    }
}