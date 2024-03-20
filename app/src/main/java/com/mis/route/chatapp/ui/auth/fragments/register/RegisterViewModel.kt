package com.mis.route.chatapp.ui.auth.fragments.register

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.mis.route.chatapp.ViewMessage
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.database.MyDatabase
import com.mis.route.chatapp.database.User

class RegisterViewModel : BaseViewModel() {
    val userNameLiveData = MutableLiveData<String>()
    val userNameError = MutableLiveData<String?>()
    val emailLiveData = MutableLiveData<String>()
    val emailError = MutableLiveData<String?>()
    val passwordLiveData = MutableLiveData<String>()
    val passwordError = MutableLiveData<String?>()
    val passwordConfirmLiveData = MutableLiveData<String>()
    val passwordConfirmError = MutableLiveData<String?>()
    val isRegistering = MutableLiveData(false)
    val authService = Firebase.auth
    val events = MutableLiveData<RegisterViewEvents>()
    fun register() {
        if (isRegistering.value == true)return
        if (!validateInputs())return
        isRegistering.value = true
        authService.createUserWithEmailAndPassword(emailLiveData.value!!, passwordLiveData.value!!,)
            .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = task.result.user
                registerUserInDB(user!!.uid)
            } else {
                isRegistering.value = false
                viewMessage.value = ViewMessage(message = task.exception?.localizedMessage
                    ?: "something went wrong",)
            }
        }
    }

    private fun registerUserInDB(uid: String) {
        val user = User(
            uid,
            userNameLiveData.value!!,
            emailLiveData.value!!,
        )
        MyDatabase.createUser(user) { task ->
            isRegistering.value = false
            if (task.isSuccessful) {
                events.postValue(
                    RegisterViewEvents.NavigateToHome(user),
                )
            } else {
                viewMessage.value = ViewMessage(
                    message = task.exception?.localizedMessage ?: "",
                    posActionName = "ok",
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
