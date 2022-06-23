package com.aryandadhich.urja10.ui.signIn

import android.text.BoringLayout
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aryandadhich.urja10.network.API
import com.aryandadhich.urja10.utils.stringUtils.Companion.JWT
import com.aryandadhich.urja10.utils.stringUtils.Companion.isLoggedIn
import com.aryandadhich.urja10.utils.stringUtils.Companion.role
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignInViewModel: ViewModel() {
    lateinit var loginId: String

    lateinit var password: String

    lateinit var loginMessage: String

    private var _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean>
        get() = _login


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    fun login(){
        coroutineScope.launch {
            val organizersPostSignIn = OrganizersPostSignIn(loginId, password);
            var getSignUpDeffered =API.retrofitService.organizersSignIn(organizersPostSignIn);
            try {
                var organizersGetSignIn = getSignUpDeffered.await()
                JWT = organizersGetSignIn.token
                role = organizersGetSignIn.role
                isLoggedIn = true
                loginMessage = organizersGetSignIn.message
                if(loginMessage != "sign in successfully")
                    _login.value = false
                else
                    _login.value = true

            }catch (t: Throwable){
                loginMessage =   t.toString();
                _login.value = false
                Log.i("SignInViewModel", "${t.toString()}")
            }
        }
    }

    fun onLoginComplete(){
        _login.value = false;
    }
}