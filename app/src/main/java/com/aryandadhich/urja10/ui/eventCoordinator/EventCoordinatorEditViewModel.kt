package com.aryandadhich.urja10.ui.eventCoordinator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EventCoordinatorEditViewModel(loginId: String): ViewModel() {

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
        getEventCoordinatorInfo()
    }

    fun getEventCoordinatorInfo() {
        coroutineScope.launch {
            var getEventCoordinatorDeffered = API.retrofitService.getEventCoordinator(loginId)
            try {
                var listResult = getEventCoordinatorDeffered.await()
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

    fun initialiseDetails(eventCoordinator: EventCoordinator) {
        name = eventCoordinator.name
        collegeId = eventCoordinator.collegeId
        year = eventCoordinator.year
        branch = eventCoordinator.branch
        whatsappCountryCode = eventCoordinator.whatsappCountryCode
        whatsappNumber = eventCoordinator.whatsappNumber
        mobileNumberCountryCode = eventCoordinator.mobileCountryCode
        mobileNumber = eventCoordinator.mobileNumber
        loginId = eventCoordinator.loginId

        _status.value = true
    }


    fun updateEventCoordinatorInfo(){
        coroutineScope.launch {
            val postEventCoordinator = PostEventCoordinator(loginId, collegeId, branch, year, "event-coordinator", password, whatsappCountryCode, whatsappNumber, mobileNumberCountryCode, mobileNumber, name );
            var getEventCoordinatorDeffered = API.retrofitService.updateEventCoordinator(loginId, postEventCoordinator)
            try {
                var listResult = getEventCoordinatorDeffered.await()
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

    fun deleteEventCoordinator(){
        coroutineScope.launch {
            var getEventCoordinatorDeffered = API.retrofitService.deleteEventCoordinator(loginId)
            try {
                var listResult = getEventCoordinatorDeffered.await()
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