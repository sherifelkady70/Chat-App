package com.mis.route.chatapp.ui.createroom

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.database.ViewMessage
import com.mis.route.chatapp.ui.createroom.repo.CreatRoomRepo
import com.mis.route.chatapp.ui.createroom.repo.CreateRoomRepoImpl
import kotlinx.coroutines.launch

class RoomCreationViewModel : BaseViewModel() {
    var nameLiveData = MutableLiveData<String>()
    var desLiveData = MutableLiveData<String>()
    var categoryLiveData = MutableLiveData<String>()
    var nameError = MutableLiveData<String?>()
    var desError = MutableLiveData<String?>()
    var categoryError = MutableLiveData<String?>()
    var createRoomRepo : CreatRoomRepo = CreateRoomRepoImpl()


    fun createRoom(){
        if(!validate()) return
        viewModelScope.launch {
            isLoading.value = true
            try {
                createRoomRepo.createRoom(name = nameLiveData.value!! ,
                    category = categoryLiveData.value!!, description = desLiveData.value!!)
                isLoading.value = false
            }catch (e:Throwable){
                isLoading.value = false
                viewMessage.value = ViewMessage(
                    "Error",
                    e.localizedMessage
                )
            }


        }
    }

    private fun validate() : Boolean{
        var isValid = true

        if(nameLiveData.value.isNullOrBlank()){
            nameError.value="Please Write Name to Room"
            isValid=false
        }else{
            nameError.value=null
        }
        if(desLiveData.value.isNullOrBlank()){
            desError.value="Please Write Description to Room"
            isValid=false
        }else{
            desError.value = null
        }
        if(categoryLiveData.value.isNullOrBlank()){
            categoryError.value = " Please Choose Category to Room"
            isValid=false
        }else{
            categoryError.value = null
        }

        return isValid
    }
}