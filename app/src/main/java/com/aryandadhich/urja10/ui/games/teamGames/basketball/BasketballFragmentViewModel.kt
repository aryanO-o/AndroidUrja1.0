package com.aryandadhich.urja10.ui.games.teamGames.basketball

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BasketballFragmentViewModel: ViewModel() {

    private var _basketballGames= MutableLiveData<List<BasketballGame>?>();
    val basketballGames: LiveData<List<BasketballGame>?>
    get() = _basketballGames;

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
    get() = _status;
    private var _loadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean>
    get() = _loadData;

    private val viewModelJob = Job();
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main);
    init {
        fetchBasketballGames();
    }

    fun fetchBasketballGames() {
        coroutineScope.launch {
            val getBasketballDeferred = API.retrofitService.getAllBasketballGames();
            try {
                val result = getBasketballDeferred.await()

                for(game in result){
                    val gettingTeam1data = API.retrofitService.getTeamById(game.teamA)
                    val gettingTeam2data = API.retrofitService.getTeamById(game.teamB)


                    try {
                        val teamA = gettingTeam1data.await()
                        Log.i("BasketballFragmentViewModel: ", "yaha bhi nai pohocha??" + teamA.toString());

                        val teamB = gettingTeam2data.await()
                        if(teamB != null){
                            game.teamB = teamB.houseName;
                        }
                        if (teamA != null) {
                            game.teamA = teamA.houseName
                        };

                    }catch (t: Throwable){
                        _status.value = t.toString();
                        Log.i("BasketballFragmentViewModel", t.toString());
                    }

                    try {
                        val teamB = gettingTeam2data.await()
                        if(teamB != null){
                            game.teamB = teamB.houseName;
                        }
                    }catch (t: Throwable){
                        _status.value = t.toString();
                        Log.i("BasketballFragmentViewModel", t.toString());
                    }

                    if(game.id == result[result.size - 1].id){
                        _basketballGames.value = result;
                        _status.value = "fetch successfully";
                    }


                    Log.i("BasketballFragmentViewModel in here at: ", game.toString());
                }


                Log.i("BasketballFragmentViewModel", _basketballGames.value.toString());
                _loadData.value = true;
            }catch (t: Throwable){
                _status.value = t.toString();
                Log.i("BasketballFragmentViewModel. yaha wali: ", t.toString());
            }
        }
    }

    fun doneLoadingData(){
        _loadData.value = false;
    }
}