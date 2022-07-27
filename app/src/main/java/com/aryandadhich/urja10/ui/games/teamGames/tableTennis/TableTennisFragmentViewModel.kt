package com.aryandadhich.urja10.ui.games.teamGames.tableTennis

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import com.aryandadhich.urja10.ui.games.teamGames.tableTennis.TableTennisGame
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TableTennisFragmentViewModel : ViewModel(){
    private var _tableTennisGames= MutableLiveData<List<TableTennisGame>?>();
    val tableTennisGames: LiveData<List<TableTennisGame>?>
        get() = _tableTennisGames;

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status;
    private var _loadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean>
        get() = _loadData;

    private val viewModelJob = Job();
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main);
    init {
        fetchTableTennisGames();
    }

    fun fetchTableTennisGames() {
        coroutineScope.launch {
            val getTableTennisDeferred = API.retrofitService.getAllTableTennisGames();
            try {
                val result = getTableTennisDeferred.await()

                for(game in result){
                    val gettingTeam1data = API.retrofitService.getTeamById(game.teamA)
                    val gettingTeam2data = API.retrofitService.getTeamById(game.teamB)


                    try {
                        val teamA = gettingTeam1data.await()
                        Log.i("TableTennisFragmentViewModel: ", "yaha bhi nai pohocha??" + teamA.toString());

                        val teamB = gettingTeam2data.await()
                        if(teamB != null){
                            game.teamB = teamB.houseName;
                        }
                        if (teamA != null) {
                            game.teamA = teamA.houseName
                        };

                    }catch (t: Throwable){
                        _status.value = t.toString();
                        Log.i("TableTennisFragmentViewModel", t.toString());
                    }

                    try {
                        val teamB = gettingTeam2data.await()
                        if(teamB != null){
                            game.teamB = teamB.houseName;
                        }
                    }catch (t: Throwable){
                        _status.value = t.toString();
                        Log.i("TableTennisFragmentViewModel", t.toString());
                    }

                    if(game.id == result[result.size - 1].id){
                        _tableTennisGames.value = result;
                        _status.value = "fetch successfully";
                    }


                    Log.i("TableTennisFragmentViewModel in here at: ", game.toString());
                }


                Log.i("TableTennisFragmentViewModel", _tableTennisGames.value.toString());
                _loadData.value = true;
            }catch (t: Throwable){
                _status.value = t.toString();
                Log.i("TableTennisFragmentViewModel. yaha wali: ", t.toString());
            }
        }
    }

    fun doneLoadingData(){
        _loadData.value = false;
    }
}