package com.aryandadhich.urja10.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import com.aryandadhich.urja10.ui.houseCaptain.HouseCaptain
import kotlinx.coroutines.*

class HomeViewModel : ViewModel() {

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _notices = MutableLiveData<List<Notice>?>()
    val notices: LiveData<List<Notice>?>
        get() = _notices

    private var _loadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean>
        get() = _loadData;

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getNoticesData()
    }

    fun getNoticesData() {
        coroutineScope.launch {
            var getNotices = API.retrofitService.getAllNotices()
            try {
                var listResult = getNotices.await()
                _status.value = "success: ${listResult.size} notices."
                _notices.value = listResult;
                _loadData.value = true;
            }catch (t: Throwable){
                _status.value = "failure + " + t.message
                Log.i("HomeViewModel", "yaha: ${t.message}")
            }
        }
    }
}