package com.aryandadhich.urja10.ui.games.common.players

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PlayerFragmentViewModel(teamId: String): ViewModel() {
    private var _status = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _status

    private val _properties = MutableLiveData<List<Player>>()
    val properties: LiveData<List<Player>>
        get() = _properties

    private var _loadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean>
        get() = _loadData;

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val teamId = teamId

    init {
        getPlayersData()
    }

    private fun getPlayersData() {
        Log.i("PlayerFragmentViewModel", "I atleast reach here.")
        coroutineScope.launch {
            var getPlayersDeffered = API.retrofitService.getAllGamePlayers(teamId = teamId)
            try {
                var listResult = getPlayersDeffered.await()
                _status.value = "success: ${listResult.size} Players."
                _properties.value = listResult;
                _loadData.value = true;
            }catch (t: Throwable){
                _status.value = "failure + " + t.message
                Log.i("PlayerFragmentViewModel", "yaha: ${t.message}")
            }
        }
    }

    fun deletePlayer(teamId: String, playerId: String){
        coroutineScope.launch {
            var getPlayersDeffered = API.retrofitService.deleteGamePlayer(teamId, playerId)
            Log.i("PlayerFragmentViewModel", "team: $teamId , player: $playerId")
            try {
                var listResult = getPlayersDeffered.await()
                _status.value = "success deleted $listResult."
                getPlayersData()
            }catch (t: Throwable){
                _status.value = "failure + " + t.message
                Log.i("PlayerFragmentViewModel", "yaha: ${t.message}")
            }
        }
    }


}