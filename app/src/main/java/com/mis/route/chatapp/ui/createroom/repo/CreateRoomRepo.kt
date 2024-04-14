package com.mis.route.chatapp.ui.createroom.repo

import com.mis.route.chatapp.database.Room
import com.mis.route.chatapp.database.RoomMessage
import kotlinx.coroutines.flow.Flow

interface CreateRoomRepo {
    suspend fun createRoom(name:String,category:String,description:String)

    suspend fun getAllRoom() : List<Room>

    suspend fun sendMessage(message: String , roomId : String)

    suspend fun listeningMessagesChanges(roomId : String) : Flow<List<RoomMessage>>
}