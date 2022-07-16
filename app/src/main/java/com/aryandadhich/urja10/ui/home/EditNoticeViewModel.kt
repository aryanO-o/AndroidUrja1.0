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

class EditNoticeViewModel(val noticeId: String) : ViewModel(){
    val _noticeId: String;
    lateinit var heading: String;
    lateinit var message: String;

    private var _navigation = MutableLiveData<Boolean>()
    val navigation: LiveData<Boolean>
        get() = _navigation;

    private var _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() = _status;

    private var job = Job()
    private var coroutineScope = CoroutineScope(job + Dispatchers.Main);

    init{
        _noticeId = noticeId;
        loadNoticeData();
    }

    private fun loadNoticeData() {
        coroutineScope.launch {
            val getNoticeDeferred = API.retrofitService.getNoticeById(_noticeId);
            try{
                val result = getNoticeDeferred.await();
                heading = result.heading;
                message = result.message;
                _status.value = true;
            }catch (t: Throwable){
                Log.i("EditNoticeViewModel", "eroor getting data" + t.toString());
            }
        }
    }

    fun deleteNotice(){
        coroutineScope.launch {
            val getDeletedNoticeDeferred = API.retrofitService.deleteNotice(_noticeId);
            try {
                val result = getDeletedNoticeDeferred.await();
                _status.value = true;
                _navigation.value = true;
            }catch (t: Throwable){
                Log.i("EditNoticeViewModel", "eroor deleting notice" + t.toString());
            }
        }
    }

    fun updateNotice(){
        coroutineScope.launch {
            val getUpdatedNoticeDeferred = API.retrofitService.updateNotice(_noticeId, PostNotice(heading, message, ""));
            try {
                val result = getUpdatedNoticeDeferred.await();
                heading = result.heading;
                message = result.message;
                _navigation.value = true;
                _status.value = true
            }catch (t: Throwable){
                Log.i("EditNoticeViewModel", "eroor updating notice" + t.toString());
            }
        }
    }
    fun settingDataComplete(){
        _status.value = false;
    }

    fun navigationComplete(){
        _navigation.value = false;
    }
}