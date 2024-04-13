package com.mis.route.chatapp.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.database.Room
import com.mis.route.chatapp.database.ViewMessage
import com.mis.route.chatapp.ui.createroom.repo.CreateRoomRepo
import com.mis.route.chatapp.ui.createroom.repo.CreateRoomRepoImpl
import kotlinx.coroutines.launch

class ChatViewModel : BaseViewModel() {
    lateinit var room : Room
    val roomMessage = MutableLiveData("")
    var repo : CreateRoomRepo = CreateRoomRepoImpl()
    fun sendMessage(){
        viewModelScope.launch {
            try {
                repo.sendMessage(roomMessage.value!!,room.id!!)
            }catch (t:Throwable){
                viewMessage.value = ViewMessage(
                    "Error",
                    t.localizedMessage
                )
            }
        }
    }
}