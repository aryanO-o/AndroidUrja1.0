package com.aryandadhich.urja10.ui.houseCaptain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HouseCaptainEditViewModel(loginId: String): ViewModel() {
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
        getHouseCaptainInfo()
    }

    fun initialiseDetails(houseCaptain: HouseCaptain) {
        name = houseCaptain.name
        collegeId = houseCaptain.collegeId
        year = houseCaptain.year
        branch = houseCaptain.branch
        whatsappCountryCode = houseCaptain.whatsappCountryCode
        whatsappNumber = houseCaptain.whatsappNumber
        mobileNumberCountryCode = houseCaptain.mobileCountryCode
        mobileNumber = houseCaptain.mobileNumber
        loginId = houseCaptain.loginId

        _status.value = true
    }

    fun getHouseCaptainInfo(){
        coroutineScope.launch {
            var getPlayersDeffered = API.retrofitService.getRolePlayer(loginId)
            try {
                var listResult = getPlayersDeffered.await()
                _message.value = "fetch successfull"
                Log.i("HouseCaptainEditViewModel", "${listResult.toString()}")
                _status.value = true
                initialiseDetails(listResult)
            }catch (t: Throwable){
                _status.value = false
                _message.value = t.message.toString()
                Log.i("FirstViewModel", "yaha: ${t.message}")
            }
        }
    }

    fun updateHouseCaptainInfo(){
        coroutineScope.launch {
            val postHouseCaptain = PostHouseCaptain(loginId, collegeId, branch, year, "house-captain", password, whatsappCountryCode, whatsappNumber, mobileNumberCountryCode, mobileNumber, name );
            var getPlayersDeffered = API.retrofitService.updateHouseCaptain(loginId, postHouseCaptain)
            try {
                var listResult = getPlayersDeffered.await()
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

    fun deleteHouseCaptain(){
        coroutineScope.launch {
            var getPlayersDeffered = API.retrofitService.deleteHouseCaptain(loginId)
            try {
                var listResult = getPlayersDeffered.await()
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