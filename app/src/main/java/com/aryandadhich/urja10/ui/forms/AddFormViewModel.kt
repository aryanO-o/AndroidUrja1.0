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

class AddFormViewModel: ViewModel() {
    var toSelect: String = "";
    var isActive: Boolean = true;

    private var _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() = _status
    private var _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun createForm(){
        coroutineScope.launch {
            val postForm = PostForm(toSelect, isActive);
            var getFormDeferred = API.retrofitService.addForm(postForm);
            try {
                val result = getFormDeferred.await()
                _message.value = result.message;
                _status.value = true;
            }catch (t: Throwable){
                _status.value = false
                _message.value = t.message.toString()
                Log.i("AddFormViewModel", "yaha: ${t.message}")
            }
        }
    }
}