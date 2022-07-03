package com.aryandadhich.urja10.ui.games.common.players

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

class AddGamePlayersFragmentViewModel: ViewModel() {
    var jerseyNo:Int = 87
    var name: String = ""


    private var _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    private val _properties = MutableLiveData<Team>()
    val properties: LiveData<Team>
        get() = _properties

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun addPlayer(teamId: String){
        coroutineScope.launch {
            val getPlayerDeferred = API.retrofitService.addGamePlayer(teamId, PostPlayer(jerseyNo, name));

            try {
                val result = getPlayerDeferred.await()
                _properties.value = result
                _navigate.value = true;
            }catch (t: Throwable){
                Log.i("AddFamePlayerFragmentViewModel", "${t.message}")
            }
        }
    }

    fun doneNavigating(){
        _navigate.value = false;
    }
}