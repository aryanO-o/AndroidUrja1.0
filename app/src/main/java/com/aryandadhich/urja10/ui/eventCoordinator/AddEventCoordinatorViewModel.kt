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

class AddEventCoordinatorViewModel: ViewModel() {
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

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun addEventCoordinatorFromAddBtn(){
        Log.i("AddEventCoordinatorViewModel", "I atleast reach here.")
        coroutineScope.launch {
            val postEventCoordinator = PostEventCoordinator(loginId, collegeId, branch, year, "event-coordinator", password, whatsappCountryCode, whatsappNumber, mobileNumberCountryCode, mobileNumber, name );
            var getEventCoordinatorDeffered = API.retrofitService.addEventCoordinator(postEventCoordinator)
            try {
                var listResult = getEventCoordinatorDeffered.await()
                _message.value = listResult.message
                _status.value = true
            }catch (t: Throwable){
                _status.value = false
                _message.value = t.message.toString()
                Log.i("AddEventCoordinatorViewModel", "yaha: ${t.message}")
            }
        }
    }
}