package com.mis.route.chatapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mis.route.chatapp.models.ViewMessage

open class BaseViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val viewMessage = MutableLiveData<ViewMessage>()
}