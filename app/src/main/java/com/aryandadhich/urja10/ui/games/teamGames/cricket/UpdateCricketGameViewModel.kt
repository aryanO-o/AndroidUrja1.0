package com.aryandadhich.urja10.ui.games.teamGames.cricket

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

class UpdateCricketGameViewModel(eventId: String, teamAName: String, teamBName: String): ViewModel() {

    private val _eventId: String

    lateinit var editTextTeamAScore: String
    lateinit var editTextTeamBScore: String

    var _teamAName = teamAName;
    var _teamBName = teamBName;

    private var _game = MutableLiveData<CricketGame>()
    val game: LiveData<CricketGame>
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
        getCricketGame()
    }

    private fun getCricketGame() {
        coroutineScope.launch {
            val getCricketGameByIdDeferred = API.retrofitService.getCricketGameById(_eventId);
            try {
                val result = getCricketGameByIdDeferred.await()
                _game.value = result;
                _message.value = "fetch Successful"
            }
            catch (t: Throwable){
                _message.value = "error loading game data: " + t.toString();
                Log.i("UpdateCricketGameViewModel", _message.value.toString())
            }
        }
    }

    fun updateCricketGameScores(){
        Log.i("UpdateCricketGameViewModel", _eventId)
        coroutineScope.launch {
            val postCricketUpdateDeferred = API.retrofitService.updateCricketGame(_eventId, PostCricketScoreUpdates(editTextTeamAScore, editTextTeamBScore))
            try {
                val result = postCricketUpdateDeferred.await()
                _game.value = result;
                _message.value = "update successful"
                _navigation.value = true;
            }catch (t: Throwable){
                _message.value = "error updating game data: " + t.toString();
                Log.i("UpdateCricketGameViewModel", _message.value.toString())
            }
        }
    }

    fun doneNavigating(){
        _navigation.value = false;
    }
}