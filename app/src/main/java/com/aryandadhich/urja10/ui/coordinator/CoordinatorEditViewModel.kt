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

class CoordinatorEditViewModel(loginId: String): ViewModel() {

    var name: String = ""
    var collegeId:String = ""
    var year: Int = 0
    var branch: String = ""
    var whatsappCountryCode: Int = 91
    var whatsappNumber: String = ""
    var mobileNumberCountryCode: Int = 91
    var mobileNumber: String= ""
    var loginId: String = ""
    var password: String =""

    private var _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() = _status
    private var _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private var _navigate = MutableLiveData<Boolean>()
    val navigate: LiveData<Boolean>
        get() = _navigate

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    init {
        this.loginId = loginId
        _navigate.value = false;
        getCoordinatorInfo()
    }

    fun getCoordinatorInfo() {
        coroutineScope.launch {
            var getCoordinatorDeffered = API.retrofitService.getCoordinator(loginId)
            try {
                var listResult = getCoordinatorDeffered.await()
                _message.value = "fetch successfull"
                Log.i("CoordinatorEditViewModel", "${listResult.toString()}")
                _status.value = true
                initialiseDetails(listResult)
            }catch (t: Throwable){
                _status.value = false
                _message.value = t.message.toString()
                Log.i("CoordinatorEditViewModel", "yaha: ${t.message}")
            }
        }
    }

    fun initialiseDetails(coordinator: Coordinator) {
        name = coordinator.name
        collegeId = coordinator.collegeId
        year = coordinator.year
        branch = coordinator.branch
        whatsappCountryCode = coordinator.whatsappCountryCode
        whatsappNumber = coordinator.whatsappNumber
        mobileNumberCountryCode = coordinator.mobileCountryCode
        mobileNumber = coordinator.mobileNumber
        loginId = coordinator.loginId

        _status.value = true
    }


    fun updateCoordinatorInfo(){
        coroutineScope.launch {
            val postCoordinator = PostCoordinator(loginId, collegeId, branch, year, "coordinator", password, whatsappCountryCode, whatsappNumber, mobileNumberCountryCode, mobileNumber, name );
            var getCoordinatorDeffered = API.retrofitService.updateCoordinator(loginId, postCoordinator)
            try {
                var listResult = getCoordinatorDeffered.await()
                _message.value = listResult.message
                _status.value = true
                _navigate.value = true;
            }catch (t: Throwable){
                _status.value = true
                _message.value = t.message.toString()
                Log.i("FirstViewModel", "yaha: ${t.message}")
            }
        }
    }

    fun deleteCoordinator(){
        coroutineScope.launch {
            var getCoordinatorDeffered = API.retrofitService.deleteCoordinator(loginId)
            try {
                var listResult = getCoordinatorDeffered.await()
                _message.value = listResult.message
                _status.value = true
                _navigate.value = true;
            }catch (t: Throwable){
                _status.value = true
                _message.value = t.message.toString()
                Log.i("FirstViewModel", "yaha: ${t.message}")
            }
        }
    }


    fun onNavigationComplete(){
        _navigate.value = false;
    }
}