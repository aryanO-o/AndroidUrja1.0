package com.aryandadhich.urja10.ui.games.individualGames.chess

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ChessFragmentViewModel: ViewModel() {
    private var _chessGames= MutableLiveData<List<ChessGame>?>();
    val chessGames: LiveData<List<ChessGame>?>
        get() = _chessGames;

    private var _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status;

    private var _loadData = MutableLiveData<Boolean>()
    val loadData: LiveData<Boolean>
        get() = _loadData;

    private val viewModelJob = Job();
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main);
    init {
        fetchChessGames();
    }

    private fun fetchChessGames() {
        coroutineScope.launch {
            val getChessDeferred = API.retrofitService.getAllChessGames();
            try {
                val result = getChessDeferred.await()
                _chessGames.value = result;
                Log.i("ChessFragmentViewModel", _chessGames.value.toString());
                _loadData.value = true;
            }catch (t: Throwable){
                _status.value = t.toString();
                Log.i("ChessFragmentViewModel. yaha wali: ", t.toString());
            }
        }
    }
}