package com.aryandadhich.urja10.ui.games.teamGames.badminton

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import com.aryandadhich.urja10.ui.games.common.gameInfo.PostGameInfo
import com.aryandadhich.urja10.ui.games.teamGames.teams.Team
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddBadmintonGameViewModel: ViewModel() {

    private var _teams = MutableLiveData<List<Team>?>()
    val team: LiveData<List<Team>?>
        get() = _teams

    private var _fetchTeamStauts= MutableLiveData<String>()
    val fetchTeamStatus: LiveData<String>
        get() = _fetchTeamStauts;

    private var _navigation = MutableLiveData<Boolean>()
    val navigation: LiveData<Boolean>
        get() = _navigation;

    private var _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message;

    lateinit var teamAId: String
    lateinit var teamBId: String


    private var job = Job();
    private var coroutineScope = CoroutineScope(job + Dispatchers.Main);

    init {
        fetchTeams()
    }

    private fun fetchTeams() {
        coroutineScope.launch {
            val getTeamsDeferred = API.retrofitService.getAllTeams();
            try {
                val result = getTeamsDeferred.await()
                _teams.value = result;
                _fetchTeamStauts.value = "successfull"
            }catch (t: Throwable){
                _fetchTeamStauts.value = "error fetching teams data: " + t.toString();
                Log.i("AddBadmintonGameViewModel", fetchTeamStatus.toString());
            }
        }
    }

    fun addBadmintonGame(){
        coroutineScope.launch {
            val getBadmintonGameDeferred = API.retrofitService.addBadmintonGame( PostBadmintonGame(teamAId, teamBId) )
            try {
                val result = getBadmintonGameDeferred.await()
                _message.value = "game added successfully";
                createGameDetails(result.id)
                Log.i("AddBadmintonGameViewModel", result.toString());
            }catch (t: Throwable){
                _message.value = "error: " + t.toString();
                Log.i("AddBadmintonGameViewModel", message.value!!);
            }
        }
    }

    private fun createGameDetails(eventId: String){

        Log.i("AddBadmintonGameViewModel", "here i am");
        coroutineScope.launch {
            val getGameInfoDeferred = API.retrofitService.createGameDetails(PostGameInfo("Badminton", "", "2021-01-01T00:00:00.000Z","", "", "", eventId));
            try {
                val result = getGameInfoDeferred.await()
                _navigation.value = true;
                _message.value = "initial game details created"
            }catch (t: Throwable){
                _message.value = "error: " + t.toString()
                Log.i("AddBadmintonGameViewModel. hereeeeeeeeee: ", message.value!!);
            }
        }
    }

    fun doneNavigating(){
        _navigation.value = false;
    }
}