package com.aryandadhich.urja10.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AddNoticeViewModel:ViewModel() {

    var heading:String = ""
    var notice: String = ""

    private var _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() = _status
    private var _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message



    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun addNotice(){
        coroutineScope.launch {
            val addedNoticeDeferred = API.retrofitService.addNotice(PostNotice(heading, notice, ""))
            try {
                val result = addedNoticeDeferred.await();
                _status.value = true;
                _message.value = "Notice added."
            }catch (t:Throwable){
                _message.value = "error occured"
                Log.i("AddNoticeViewModel", "error: " + t.toString());
            }
        }
    }
}