package com.aryandadhich.urja10.ui.coordinator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CoordinatorViewModel: ViewModel() {

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _properties = MutableLiveData<List<Coordinator>?>()
    val properties: LiveData<List<Coordinator>?>
        get() = _properties

    private var _loadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean>
        get() = _loadData;

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getCoordinatorData()
    }

    private fun getCoordinatorData() {
        coroutineScope.launch {
            var getCoordinatorDeffered = API.retrofitService.getCoordinators("coordinator")
            try {
                var listResult = getCoordinatorDeffered.await()
                _status.value = "success: ${listResult.size} Coordinator."
                _properties.value = listResult;
                _loadData.value = true;
                Log.i("CoordinatorViewModel", "$listResult");
            }catch (t: Throwable){
                _status.value = "failure + " + t.message
                Log.i("CoordinatorViewModel", "yaha: ${t.message}")
            }
        }
    }
}