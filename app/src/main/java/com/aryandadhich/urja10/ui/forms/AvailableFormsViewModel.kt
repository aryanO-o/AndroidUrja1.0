package com.aryandadhich.urja10.ui.forms

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

class AvailableFormsViewModel: ViewModel() {
    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _properties = MutableLiveData<List<Form>?>()
    val properties: LiveData<List<Form>?>
        get() = _properties

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getFormsData()
    }

    fun getFormsData() {
        coroutineScope.launch {
            var getFormDeffered = API.retrofitService.getAllForms()
            try {
                var listResult = getFormDeffered.await()
                _status.value = "success: ${listResult.size} Coordinator."
                _properties.value = listResult;
                Log.i("AvailableFormsViewModel", "$listResult");
            }catch (t: Throwable){
                _status.value = "failure + " + t.message
                Log.i("AvailableFormsViewModel", "yaha: ${t.message}")
            }
        }
    }

    fun toggleByViewModel(form: Form){
        coroutineScope.launch {
            val postForm = PostForm(form.toSelect, !form.isActive);
            var getFormDeferred = API.retrofitService.toggleFormIsActive(form.id, postForm);
            try{
                var result = getFormDeferred.await();
                Log.i("AvailableFormsViewModel", "yaha: $result")
                getFormsData();
            }catch (t: Throwable){
                _status.value = "failure + " + t.message
                Log.i("AvailableFormsViewModel", "yaha: ${t.message}")
            }
        }
    }

    fun deleteFormDeleteBtn(form: Form){
        coroutineScope.launch {
            var getFormDeferred = API.retrofitService.deleteForm(form.id);
            try{
                var result = getFormDeferred.await();
                Log.i("AvailableFormsViewModel", "yaha: $result")
                getFormsData();
            }catch (t: Throwable){
                _status.value = "failure + " + t.message
                Log.i("AvailableFormsViewModel", "yaha: ${t.message}")
            }
        }
    }

}