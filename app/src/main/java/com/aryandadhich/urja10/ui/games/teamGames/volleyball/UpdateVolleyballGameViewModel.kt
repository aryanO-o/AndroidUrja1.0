package com.aryandadhich.urja10.ui.games.teamGames.volleyball

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

class UpdateVolleyballGameViewModel(eventId: String, teamAName: String, teamBName: String): ViewModel() {

    private val _eventId: String

    var editTextTeamAScore by Delegates.notNull<Int>()
    var editTextTeamBScore by Delegates.notNull<Int>()

    var _teamAName = teamAName;
    var _teamBName = teamBName;

    private var _game = MutableLiveData<VolleyballGame>()
    val game: LiveData<VolleyballGame>
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
        getVolleyballGame()
    }

    private fun getVolleyballGame() {
        coroutineScope.launch {
            val getVolleyballGameByIdDeferred = API.retrofitService.getVolleyballGameById(_eventId);
            try {
                val result = getVolleyballGameByIdDeferred.await()
                _game.value = result;
                _message.value = "fetch Successful"
            }
            catch (t: Throwable){
                _message.value = "error loading game data: " + t.toString();
                Log.i("UpdateVolleyballGameViewModel", _message.value.toString())
            }
        }
    }

    fun updateVolleyballGameScores(){
        Log.i("UpdateVolleyballGameViewModel", _eventId)
        coroutineScope.launch {
            val postVolleyballUpdateDeferred = API.retrofitService.updateVolleyballGame(_eventId, PostVolleyballScoreUpdates(editTextTeamAScore, editTextTeamBScore))
            try {
                val result = postVolleyballUpdateDeferred.await()
                _game.value = result;
                _message.value = "update successful"
                _navigation.value = true;
            }catch (t: Throwable){
                _message.value = "error updating game data: " + t.toString();
                Log.i("UpdateVolleyballGameViewModel", _message.value.toString())
            }
        }
    }

    fun deleteGame(){
        coroutineScope.launch {
            val deleteGameDeferred = API.retrofitService.deleteVolleyballGame(_eventId)
            try {
                val result = deleteGameDeferred.await()
                _message.value = "deleted successful"
                _navigation.value = true;
            }catch (t: Throwable){
                _message.value = "error updating game data: " + t.toString();
                Log.i("UpdateVolleyballGameViewModel", _message.value.toString())
            }
        }
    }

    fun doneNavigating(){
        _navigation.value = false;
    }
}