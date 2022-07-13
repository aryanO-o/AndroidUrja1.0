package com.aryandadhich.urja10.ui.games.individualGames.carrom

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import com.aryandadhich.urja10.ui.games.individualGames.carrom.CarromGame
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CarromFragmentViewModel: ViewModel() {
    private var _carromGames= MutableLiveData<List<CarromGame>?>();
    val carromGames: LiveData<List<CarromGame>?>
        get() = _carromGames;

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status;

    private var _loadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean>
        get() = _loadData;

    private val viewModelJob = Job();
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main);
    init {
        fetchCarromGames();
    }

    private fun fetchCarromGames() {
        coroutineScope.launch {
            val getCarromDeferred = API.retrofitService.getAllCarromGames();
            try {
                val result = getCarromDeferred.await()
                _carromGames.value = result;
                Log.i("CarromFragmentViewModel", _carromGames.value.toString());
                _loadData.value = true;
            }catch (t: Throwable){
                _status.value = t.toString();
                Log.i("CarromFragmentViewModel. yaha wali: ", t.toString());
            }
        }
    }
}