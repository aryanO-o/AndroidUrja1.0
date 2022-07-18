package com.aryandadhich.urja10.ui.games.individualGames.carrom

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class UpdateCarromGameViewModel(eventId: String): ViewModel() {

    private val _eventId: String

    lateinit var playerA :String
    lateinit var playerB :String
    lateinit var winner :String

    private var _game = MutableLiveData<CarromGame>()
    val game: LiveData<CarromGame>
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
        getCarromGame()
    }

    private fun getCarromGame() {
        coroutineScope.launch {
            val getCarromGameByIdDeferred = API.retrofitService.getCarromGameById(_eventId);
            try {
                val result = getCarromGameByIdDeferred.await()
                _game.value = result;
                _message.value = "fetch Successful"
            }
            catch (t: Throwable){
                _message.value = "error loading game data: " + t.toString();
                Log.i("UpdateCarromGameViewModel", _message.value.toString())
            }
        }
    }

    fun updateCarromGameScores(){
        Log.i("UpdateCarromGameViewModel", _eventId)
        coroutineScope.launch {
            val postCarromUpdateDeferred = API.retrofitService.updateCarromGame(_eventId, PostCarromGame(playerA, playerB, winner))
            try {
                val result = postCarromUpdateDeferred.await()
                _game.value = result;
                _message.value = "update successful"
                _navigation.value = true;
            }catch (t: Throwable){
                _message.value = "error updating game data: " + t.toString();
                Log.i("UpdateCarromGameViewModel", _message.value.toString())
            }
        }
    }

    fun deleteGame(){
        coroutineScope.launch {
            val deleteGameDeferred = API.retrofitService.deleteCarromGame(_eventId)
            try {
                val result = deleteGameDeferred.await()
                _message.value = "deleted successful"
                _navigation.value = true;
            }catch (t: Throwable){
                _message.value = "error updating game data: " + t.toString();
                Log.i("UpdateCarromGameViewModel", _message.value.toString())
            }
        }
    }

    fun doneNavigating(){
        _navigation.value = false;
    }
}