package com.mis.route.chatapp.ui.auth.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.mis.route.chatapp.database.User

class LoginViewModel : ViewModel() {
    val emailLiveData = MutableLiveData<String>()
    val emailError = MutableLiveData<String?>()
    val passwordLiveData = MutableLiveData<String>()
    val passwordError = MutableLiveData<String?>()
    val isLoading = MutableLiveData(false)
    val authService = Firebase.auth
    val events = MutableLiveData<LoginViewEvent>()


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
