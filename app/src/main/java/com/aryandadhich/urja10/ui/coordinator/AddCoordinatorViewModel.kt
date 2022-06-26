package com.aryandadhich.urja10.ui.coordinator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import com.aryandadhich.urja10.ui.houseCaptain.PostHouseCaptain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddCoordinatorViewModel: ViewModel() {

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

    fun addCoordinatorFromAddBtn(){
        Log.i("AddCoordinatorViewModel", "I atleast reach here.")
        coroutineScope.launch {
            val postCoordinator = PostCoordinator(loginId, collegeId, branch, year, "coordinator", password, whatsappCountryCode, whatsappNumber, mobileNumberCountryCode, mobileNumber, name );
            var getPlayersDeffered = API.retrofitService.addCoordinator(postCoordinator)
            try {
                var listResult = getPlayersDeffered.await()
                _message.value = listResult.message
                _status.value = true
            }catch (t: Throwable){
                _status.value = false
                _message.value = t.message.toString()
                Log.i("AddCoordinatorViewModel", "yaha: ${t.message}")
            }
        }
    }

}