package com.mis.route.chatapp.ui.chat

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.database.Room
import com.mis.route.chatapp.database.RoomMessage
import com.mis.route.chatapp.database.ViewMessage
import com.mis.route.chatapp.ui.createroom.repo.CreateRoomRepo
import com.mis.route.chatapp.ui.createroom.repo.CreateRoomRepoImpl
import kotlinx.coroutines.launch

class ChatViewModel : BaseViewModel() {
    lateinit var room : Room
    val roomMessage = MutableLiveData("")
    var repo : CreateRoomRepo = CreateRoomRepoImpl()
    var messagesLiveData = MutableLiveData<List<RoomMessage>>()
    fun sendMessage(){
        viewModelScope.launch {
            try {
                repo.sendMessage(roomMessage.value!!,room.id!!)
                roomMessage.value = ""
            }catch (t:Throwable){
                viewMessage.value = ViewMessage(
                    "Error",
                    t.localizedMessage
                )
            }
        }
    }

    fun listeningChanges(){
        viewModelScope.launch {
            try {
                repo.listeningMessagesChanges(room.id!!).collect{
                    messagesLiveData.value = it
                }
            }catch (t:Throwable){
                viewMessage.value = ViewMessage("Error",t.localizedMessage)
            }
        }
    }
}