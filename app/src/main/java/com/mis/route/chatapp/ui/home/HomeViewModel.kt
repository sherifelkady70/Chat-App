package com.mis.route.chatapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.database.Room
import com.mis.route.chatapp.database.ViewMessage
import com.mis.route.chatapp.ui.createroom.repo.CreateRoomRepo
import com.mis.route.chatapp.ui.createroom.repo.CreateRoomRepoImpl
import kotlinx.coroutines.launch

class HomeViewModel : BaseViewModel() {

    var listOfRooms = MutableLiveData<List<Room>>()
    var roomRepo : CreateRoomRepo = CreateRoomRepoImpl()


    fun getList(){
        viewModelScope.launch {
            try {
                isLoading.value=true
                listOfRooms.value = roomRepo.getAllRoom()
                isLoading.value= false

            }catch (e:Exception){
                isLoading.value = true
                viewMessage.value = ViewMessage("Error",e.localizedMessage)
                isLoading.value = false
            }
        }
    }
}