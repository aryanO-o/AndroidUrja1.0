package com.aryandadhich.urja10.ui.eventCoordinator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import com.aryandadhich.urja10.ui.coordinator.Coordinator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EventCoordinatorViewModel: ViewModel() {
    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _properties = MutableLiveData<List<EventCoordinator>?>()
    val properties: LiveData<List<EventCoordinator>?>
        get() = _properties

    private var _loadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean>
        get() = _loadData;

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getEventCoordinatorData()
    }

    private fun getEventCoordinatorData() {
        coroutineScope.launch {
            var getEventCoordinatorDeffered = API.retrofitService.getEventCoordinators("event-coordinator")
            try {
                var listResult = getEventCoordinatorDeffered.await()
                _status.value = "success: ${listResult.size} EventCoordinator."
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