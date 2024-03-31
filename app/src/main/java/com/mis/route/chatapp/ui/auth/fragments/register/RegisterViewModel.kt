package com.mis.route.chatapp.ui.auth.fragments.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.database.ViewMessage
import com.mis.route.chatapp.ui.auth.fragments.repo.ResgisterRepo
import com.mis.route.chatapp.ui.auth.fragments.repo.ResgisterRepoImpl
import kotlinx.coroutines.launch

class RegisterViewModel : BaseViewModel() {
    val registerRepo : ResgisterRepo = ResgisterRepoImpl()
    val userNameLiveData = MutableLiveData<String>()
    val userNameError = MutableLiveData<String?>()
    val emailLiveData = MutableLiveData<String>()
    val emailError = MutableLiveData<String?>()
    val passwordLiveData = MutableLiveData<String>()
    val passwordError = MutableLiveData<String?>()
    val passwordConfirmLiveData = MutableLiveData<String>()
    val passwordConfirmError = MutableLiveData<String?>()
    val isRegistering = MutableLiveData(false)
    val events = MutableLiveData<RegisterViewEvents>()



    fun register() {
        if (isRegistering.value == true)return
        if (!validateInputs())return
        isRegistering.value = true
        viewModelScope.launch {
            isLoading.value=true
            try {
                registerRepo.register(
                    userNameLiveData.value!!,
                    emailLiveData.value!!,
                    passwordLiveData.value!!
                )
                isLoading.value = false
            }catch (e : Exception){
                isLoading.value = false
                viewMessage.value = ViewMessage(
                    title = "Error",
                    message = e.localizedMessage
                )
            }

        }
    }


    fun validateInputs(): Boolean {
        var isValid = true
        if (userNameLiveData.value.isNullOrBlank()) {
            userNameError.value = "please Enter User Name"
            isValid = false
        } else {
            userNameError.value = null
        }
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
        if (passwordConfirmLiveData.value.isNullOrBlank()) {
            passwordConfirmError.value = "please Enter password confirmation"
            isValid = false
        } else if (passwordLiveData.value!! != passwordConfirmLiveData.value!!) {
            passwordConfirmError.value = "password doesn't match"
            isValid = false
        } else {
            passwordConfirmError.value = null
        }
        return isValid
    }
}
