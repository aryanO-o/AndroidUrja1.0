package com.aryandadhich.urja10.ui.games.individualGames.chess

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UpdateChessGameViewModel(eventId: String): ViewModel() {

    private val _eventId: String

    lateinit var playerA :String
    lateinit var playerB :String
    lateinit var winner :String

    private var _game = MutableLiveData<ChessGame>()
    val game: LiveData<ChessGame>
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
        getChessGame()
    }

    private fun getChessGame() {
        coroutineScope.launch {
            val getChessGameByIdDeferred = API.retrofitService.getChessGameById(_eventId);
            try {
                val result = getChessGameByIdDeferred.await()
                _game.value = result;
                _message.value = "fetch Successful"
            }
            catch (t: Throwable){
                _message.value = "error loading game data: " + t.toString();
                Log.i("UpdateChessGameViewModel", _message.value.toString())
            }
        }
    }

    fun updateChessGameScores(){
        Log.i("UpdateChessGameViewModel", _eventId)
        coroutineScope.launch {
            val postChessUpdateDeferred = API.retrofitService.updateChessGame(_eventId, PostChessGame(playerA, playerB, winner))
            try {
                val result = postChessUpdateDeferred.await()
                _game.value = result;
                _message.value = "update successful"
                _navigation.value = true;
            }catch (t: Throwable){
                _message.value = "error updating game data: " + t.toString();
                Log.i("UpdateChessGameViewModel", _message.value.toString())
            }
        }
    }

    fun deleteGame(){
        coroutineScope.launch {
            val deleteGameDeferred = API.retrofitService.deleteChessGame(_eventId)
            try {
                val result = deleteGameDeferred.await()
                _message.value = "deleted successful"
                _navigation.value = true;
            }catch (t: Throwable){
                _message.value = "error updating game data: " + t.toString();
                Log.i("UpdateChessGameViewModel", _message.value.toString())
            }
        }
    }

    fun doneNavigating(){
        _navigation.value = false;
    }
}