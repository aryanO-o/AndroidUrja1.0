package com.aryandadhich.urja10.ui.games.common.gameInfo

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

class GameInfoViewModel(eventId: String): ViewModel(){
    var gameName = ""
    var gameTitle = ""
    var dateAndTime = ""
    var scorer = ""
    var refree = ""
    var venue = ""
    var inGameDetails = ""
    private var _eventId: String
    private lateinit var _gameId: String

    private var _gameInfo = MutableLiveData<GetGameInfo>()
    val gameInfo: LiveData<GetGameInfo>
        get() = _gameInfo

    private var _navigation = MutableLiveData<Boolean>()
    val navigation: LiveData<Boolean>
        get() = _navigation;

    private var _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message;

    private var _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() = _status;

    private var job = Job()
    private var coroutineScope = CoroutineScope(job + Dispatchers.Main);


    init {
        _eventId = eventId;
        getGameDetails()
    }

    private fun getGameDetails() {
        coroutineScope.launch {
            val getGameDetailsDeferred = API.retrofitService.getGameDetails(eventId = _eventId);
            try {
                val result = getGameDetailsDeferred.await()
                _gameInfo.value = result;
                setGameNameAndTitle()
                _gameId = _gameInfo.value?.id!!
                getGameInfoDetails(_gameInfo.value?.gameInfoId!!)

            }catch (t: Throwable){
                _message.value = "error getting initial details: "+ t.toString();
                Log.i("GameInfoViewModel", _message.value.toString());
            }
        }
    }

    private fun getGameInfoDetails(gameInfoId: String) {
        coroutineScope.launch {
            val getGameInfoOtherDetailsDeferred = API.retrofitService.getGameInfoOtherDetails(gameInfoId)
            try {
                val result = getGameInfoOtherDetailsDeferred.await()
                _message.value = "fetch successful";
                setData(result)
                _status.value = true;
            }catch (t: Throwable){
                _message.value = "error getting game info details: " + t.toString();
                Log.i("GameInfoViewModel", _message.value.toString());
            }
        }
    }

    private fun setData(result: GetGameInfoOtherDetails) {
        dateAndTime = result.dateAndTime
        scorer = result.scorer
        refree = result.referee
        venue = result.venue
        inGameDetails = result.inGameDetails
    }

    private fun setGameNameAndTitle() {
        gameName = gameInfo.value?.gameName.toString()
        gameTitle = gameInfo.value?.gameTitle.toString()
    }

    fun updateGameInfoDetails(){
        coroutineScope.launch {
            val updateGameInfoDeferred = API.retrofitService.updateGameDetails(_gameId, PostGameInfo(gameName, gameTitle, dateAndTime, scorer, refree, venue, _eventId, inGameDetails))
            try {
                val result = updateGameInfoDeferred.await()
                _message.value = "update successful"
                _navigation.value = true;

            }catch (t: Throwable){
                _message.value = "error updating game info details: " + t.toString();
                Log.i("GameInfoViewModel", _message.value.toString());
            }
        }
    }

    fun settingDataComplete(){
        _status.value = false;
    }

    fun navigationComplete(){
        _navigation.value = false;
    }
}