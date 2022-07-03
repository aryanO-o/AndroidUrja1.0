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

class AddTeamFragmentViewModel:ViewModel() {

    var teamName: String = ""

    private var _response = MutableLiveData<String>()
    val resposne: LiveData<String>
    get() = _response;

    private var _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
    get() = _navigate;

    private var viewModelJob = Job();
    private var coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main);

    fun addTeam(){
        coroutineScope.launch {
            val getTeamDeferred = API.retrofitService.addTeams(PostTeam(teamName))
            try {
                val result = getTeamDeferred.await()
                _navigate.value = true;
                _response.value = result.toString()
            }
            catch (t: Throwable){
                _response.value = t.toString()
                Log.i("AddTeamFragmentViewModel", "${t.message}");
            }
        }
    }

    fun onNavigationComplete(){
        _navigate.value = false;
    }

}