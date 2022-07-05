package com.aryandadhich.urja10.ui.games.teamGames.basketball

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import com.aryandadhich.urja10.ui.games.teamGames.teams.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class UpdateBasketballGameViewModel(eventId: String, teamAName: String, teamBName: String): ViewModel() {

    private val _eventId: String

    var editTextTeamAScore by Delegates.notNull<Int>()
    var editTextTeamBScore by Delegates.notNull<Int>()

    var _teamAName = teamAName;
    var _teamBName = teamBName;

    private var _game = MutableLiveData<BasketballGame>()
    val game: LiveData<BasketballGame>
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
        getBasketballGame()
    }

    private fun getBasketballGame() {
        coroutineScope.launch {
            val getBasketballGameByIdDeferred = API.retrofitService.getBasketballGameById(_eventId);
            try {
                val result = getBasketballGameByIdDeferred.await()
                _game.value = result;
                _message.value = "fetch Successful"
            }
            catch (t: Throwable){
                _message.value = "error loading game data: " + t.toString();
                Log.i("UpdateBasketballGameViewModel", _message.value.toString())
            }
        }
    }

    fun updateBasketballGameScores(){
        coroutineScope.launch {
            val postBasketballUpdateDeferred = API.retrofitService.updateBasketballGame(_eventId, PostBasketballScoreUpdates(editTextTeamAScore, editTextTeamBScore))
            try {
                val result = postBasketballUpdateDeferred.await()
                _game.value = result;
                _message.value = "update successful"
                _navigation.value = true;
            }catch (t: Throwable){
                _message.value = "error loading game data: " + t.toString();
                Log.i("UpdateBasketballGameViewModel", _message.value.toString())
            }
        }
    }

    fun doneNavigating(){
        _navigation.value = false;
    }
}