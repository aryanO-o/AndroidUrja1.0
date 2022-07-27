package com.aryandadhich.urja10.ui.houseCaptain

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HouseCaptainViewModel: ViewModel() {

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _properties = MutableLiveData<List<HouseCaptain>?>()
    val properties: LiveData<List<HouseCaptain>?>
        get() = _properties

    private var _loadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean>
        get() = _loadData;

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getHouseCaptainData()
    }

     fun getHouseCaptainData() {
        coroutineScope.launch {
            var getPlayersDeffered = API.retrofitService.getRolePlayers("house-captain")
            try {
                var listResult = getPlayersDeffered.await()
                _status.value = "success: ${listResult.size} Players."
                _properties.value = listResult;
                _loadData.value = true;
            }catch (t: Throwable){
                _status.value = "failure + " + t.message
                Log.i("HouseCaptainViewModel", "yaha: ${t.message}")
            }
        }
    }

}