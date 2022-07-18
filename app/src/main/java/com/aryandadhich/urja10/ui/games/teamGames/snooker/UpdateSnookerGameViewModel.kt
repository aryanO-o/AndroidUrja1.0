package com.aryandadhich.urja10.ui.games.teamGames.snooker

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

class UpdateSnookerGameViewModel (eventId: String, teamAName: String, teamBName: String): ViewModel() {

    private val _eventId: String

    var editTextTeamAScore by Delegates.notNull<Int>()
    var editTextTeamBScore by Delegates.notNull<Int>()

    var _teamAName = teamAName;
    var _teamBName = teamBName;

    private var _game = MutableLiveData<SnookerGame>()
    val game: LiveData<SnookerGame>
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
        getSnookerGame()
    }

    private fun getSnookerGame() {
        coroutineScope.launch {
            val getSnookerGameByIdDeferred = API.retrofitService.getSnookerGameById(_eventId);
            try {
                val result = getSnookerGameByIdDeferred.await()
                _game.value = result;
                _message.value = "fetch Successful"
            }
            catch (t: Throwable){
                _message.value = "error loading game data: " + t.toString();
                Log.i("UpdateSnookerGameViewModel", _message.value.toString())
            }
        }
    }

    fun updateSnookerGameScores(){
        Log.i("UpdateSnookerGameViewModel", _eventId)
        coroutineScope.launch {
            val postSnookerUpdateDeferred = API.retrofitService.updateSnookerGame(_eventId, PostSnookerScoreUpdates(editTextTeamAScore, editTextTeamBScore))
            try {
                val result = postSnookerUpdateDeferred.await()
                _game.value = result;
                _message.value = "update successful"
                _navigation.value = true;
            }catch (t: Throwable){
                _message.value = "error updating game data: " + t.toString();
                Log.i("UpdateSnookerGameViewModel", _message.value.toString())
            }
        }
    }

    fun deleteGame(){
        coroutineScope.launch {
            val deleteGameDeferred = API.retrofitService.deleteSnookerGame(_eventId)
            try {
                val result = deleteGameDeferred.await()
                _message.value = "deleted successful"
                _navigation.value = true;
            }catch (t: Throwable){
                _message.value = "error updating game data: " + t.toString();
                Log.i("UpdateSnookerGameViewModel", _message.value.toString())
            }
        }
    }

    fun doneNavigating(){
        _navigation.value = false;
    }
}