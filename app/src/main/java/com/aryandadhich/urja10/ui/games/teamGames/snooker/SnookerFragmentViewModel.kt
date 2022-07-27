package com.aryandadhich.urja10.ui.games.teamGames.snooker

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SnookerFragmentViewModel: ViewModel() {
    private var _snookerGames= MutableLiveData<List<SnookerGame>?>();
    val snookerGames: LiveData<List<SnookerGame>?>
        get() = _snookerGames;

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status;

    private var _loadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean>
        get() = _loadData;

    private val viewModelJob = Job();
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main);
    init {
        fetchSnookerGames();
    }

    fun fetchSnookerGames() {
        coroutineScope.launch {
            val getSnookerDeferred = API.retrofitService.getAllSnookerGames();
            try {
                val result = getSnookerDeferred.await()

                for(game in result){
                    val gettingTeam1data = API.retrofitService.getTeamById(game.teamA)
                    val gettingTeam2data = API.retrofitService.getTeamById(game.teamB)

                    try {
                        val teamA = gettingTeam1data.await()
                        Log.i("SnookerFragmentViewModel: ", "yaha bhi nai pohocha??" + teamA.toString());

                        val teamB = gettingTeam2data.await()
                        if(teamB != null){
                            game.teamB = teamB.houseName;
                        }
                        if (teamA != null) {
                            game.teamA = teamA.houseName
                        };

                    }catch (t: Throwable){
                        _status.value = t.toString();
                        Log.i("SnookerFragmentViewModel", t.toString());
                    }

                    try {
                        val teamB = gettingTeam2data.await()
                        if(teamB != null){
                            game.teamB = teamB.houseName;
                        }
                    }catch (t: Throwable){
                        _status.value = t.toString();
                        Log.i("SnookerFragmentViewModel", t.toString());
                    }

                    if(game.id == result[result.size - 1].id){
                        _snookerGames.value = result;
                        _status.value = "fetch successfully";
                    }


                    Log.i("SnookerFragmentViewModel in here at: ", game.toString());
                }


                Log.i("SnookerFragmentViewModel", _snookerGames.value.toString());
                _loadData.value = true;
            }catch (t: Throwable){
                _status.value = t.toString();
                Log.i("SnookerFragmentViewModel. yaha wali: ", t.toString());
            }
        }
    }
}