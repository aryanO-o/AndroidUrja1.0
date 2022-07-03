package com.aryandadhich.urja10.ui.games.teamGames.teams

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TeamFragmentViewModel:ViewModel() {
    private var _teams = MutableLiveData<List<Team>>()
    val teams: LiveData<List<Team>>
    get() = _teams;

    private var _response = MutableLiveData<String>()
    val response: LiveData<String>
    get() = _response

    private var viewModelJob = Job()
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main);

    init{
        getTeamsData();
    }

    fun getTeamsData(){
        coroutineScope.launch {
            var getDeferredTeam = API.retrofitService.getAllTeams()
            try {
                var result = getDeferredTeam.await()
                _response.value = "successfull"
                _teams.value = result
            }catch (t: Throwable){
                _response.value = "failure"
                Log.i("TeamFragmentViewModel", "yaha: ${t.message}");
            }
        }
    }

    fun deleteTeam(teamId: String){
        coroutineScope.launch {
            var getTeamDeferred  = API.retrofitService.deleteTeam(teamId)
            try {
                var result = getTeamDeferred.await()
                getTeamsData()
                _response.value = "successfull"
            }catch (t: Throwable){
                _response.value = "failure"
                Log.i("TeamFragmentViewModel", "${t.message}");
            }
        }
    }

}