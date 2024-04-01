package com.mis.route.chatapp.ui.auth.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.mis.route.chatapp.UserProvider
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.database.ViewMessage
import com.mis.route.chatapp.ui.auth.fragments.repo.AuthRepo
import com.mis.route.chatapp.ui.auth.fragments.repo.AuthRepoImpl
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel : BaseViewModel() {
    val emailLiveData = MutableLiveData<String>()
    val emailError = MutableLiveData<String?>()
    val passwordLiveData = MutableLiveData<String>()
    val passwordError = MutableLiveData<String?>()
    val events = MutableLiveData<LoginViewEvent>()
    val loginAuthRepo : AuthRepo = AuthRepoImpl()



    fun login(){
        if(!validateInputs())return
        viewModelScope.launch {
            isLoading.value = true
            try {
                val user = loginAuthRepo.login(emailLiveData.value!!, passwordLiveData.value!!)
                UserProvider.user = user
                isLoading.value = false
                events.value = LoginViewEvent.NavigateToHome(UserProvider.user!!)
            }catch (e:Exception){
                isLoading.value = false
                viewMessage.value = ViewMessage(
                    "Error",
                    e.localizedMessage
                )
            }
        }

    }
    fun gotoRegister(){
        events.value = LoginViewEvent.NavigateToRegister
    }
    fun validateInputs(): Boolean {
        var isValid = true
        if (emailLiveData.value.isNullOrBlank()) {
            emailError.value = "please Enter Email"
            isValid = false
        } else {
            emailError.value = null
        }
        if (passwordLiveData.value.isNullOrBlank()) {
            passwordError.value = "please Enter password"
            isValid = false
        } else if (passwordLiveData.value!!.length < 6) {
            passwordError.value = "password must be at least 6 chars"
            isValid = false
        } else {
            passwordError.value = null
        }
        return isValid
    }
}
