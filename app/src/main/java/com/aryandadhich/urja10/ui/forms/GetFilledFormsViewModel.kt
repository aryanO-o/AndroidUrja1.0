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

class GetFilledFormsViewModel: ViewModel() {
    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _properties = MutableLiveData<List<FillForm>?>()
    val properties: LiveData<List<FillForm>?>
        get() = _properties

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun getFilledFormsData(formId: Int) {
        coroutineScope.launch {
            var getFilledFormDeffered = API.retrofitService.getFilledForms(formId)
            try {
                var listResult = getFilledFormDeffered.await()
                _status.value = "success: ${listResult.size} FilledForm."
                _properties.value = listResult;
                Log.i("GetFilledFormsViewModel", "$listResult");
            }catch (t: Throwable){
                _status.value = "failure + " + t.message
                Log.i("GetFilledFormsViewModel", "yaha: ${t.message}")
            }
        }
    }
}