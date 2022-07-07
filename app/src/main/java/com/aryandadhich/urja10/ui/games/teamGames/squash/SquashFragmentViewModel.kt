package com.aryandadhich.urja10.ui.games.teamGames.squash

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import com.aryandadhich.urja10.ui.games.teamGames.squash.SquashGame
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SquashFragmentViewModel: ViewModel() {
    private var _squashGames= MutableLiveData<List<SquashGame>?>();
    val squashGames: LiveData<List<SquashGame>?>
        get() = _squashGames;

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status;

    private var _loadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean>
        get() = _loadData;

    private val viewModelJob = Job();
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main);
    init {
        fetchSquashGames();
    }

    private fun fetchSquashGames() {
        coroutineScope.launch {
            val getSquashDeferred = API.retrofitService.getAllSquashGames();
            try {
                val result = getSquashDeferred.await()

                for(game in result){
                    val gettingTeam1data = API.retrofitService.getTeamById(game.teamA)
                    val gettingTeam2data = API.retrofitService.getTeamById(game.teamB)

                    try {
                        val teamA = gettingTeam1data.await()
                        Log.i("SquashFragmentViewModel: ", "yaha bhi nai pohocha??" + teamA.toString());

                        val teamB = gettingTeam2data.await()
                        if(teamB != null){
                            game.teamB = teamB.houseName;
                        }
                        if (teamA != null) {
                            game.teamA = teamA.houseName
                        };

                    }catch (t: Throwable){
                        _status.value = t.toString();
                        Log.i("SquashFragmentViewModel", t.toString());
                    }

                    try {
                        val teamB = gettingTeam2data.await()
                        if(teamB != null){
                            game.teamB = teamB.houseName;
                        }
                    }catch (t: Throwable){
                        _status.value = t.toString();
                        Log.i("SquashFragmentViewModel", t.toString());
                    }

                    if(game.id == result[result.size - 1].id){
                        _squashGames.value = result;
                        _status.value = "fetch successfully";
                    }


                    Log.i("SquashFragmentViewModel in here at: ", game.toString());
                }


                Log.i("SquashFragmentViewModel", _squashGames.value.toString());
                _loadData.value = true;
            }catch (t: Throwable){
                _status.value = t.toString();
                Log.i("SquashFragmentViewModel. yaha wali: ", t.toString());
            }
        }
    }
}