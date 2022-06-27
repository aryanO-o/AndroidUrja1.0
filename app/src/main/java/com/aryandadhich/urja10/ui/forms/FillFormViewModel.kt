package com.aryandadhich.urja10.ui.forms

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FillFormViewModel: ViewModel() {
    var name: String = ""
    var collegeId:String = ""
    var year: Int = 0
    var branch: String = ""
    var whatsappCountryCode: Int = 91
    var whatsappNumber: String = ""
    var mobileNumberCountryCode: Int = 91
    var mobileNumber: String= ""

    private var _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() = _status
    private var _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun insertIntoParticipantsInfo(formId: Int){
        coroutineScope.launch{
            val fillForm = FillForm(collegeId, branch, year,whatsappCountryCode, whatsappNumber, mobileNumberCountryCode, mobileNumber, name);
            val getFormDeferred = API.retrofitService.insertDataInParticipantsInfo(collegeId, fillForm)
            val getParticipantsInfo = API.retrofitService.getParticipantsInfo(collegeId)
            try {
                val alreadyPresent = getParticipantsInfo.await()
                if(alreadyPresent == "0"){
                    val result = getFormDeferred.await()
                    _message.value = result.message
                }
                applyUsingApplyBtn(formId)
            }catch (t: Throwable){
                _status.value = false
                _message.value = t.message.toString()
                Log.i("FillFormViewModel", "yaha-bhi: ${t.message}")
            }
        }
    }

    fun applyUsingApplyBtn(formId: Int){
        coroutineScope.launch {
            val getAppliedForm = API.retrofitService.applyToForm(formId, ApplyForm(collegeId))
            try {
                val result = getAppliedForm.await();
                _message.value = result.message
                _status.value = true
            }catch (t: Throwable){
                _status.value = false
                _message.value = t.message.toString()
                Log.i("FillFormViewModel", "yaha: ${t.message}")
            }
        }
    }


}
