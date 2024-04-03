package com.mis.route.chatapp.ui.createroom.repo

import com.mis.route.chatapp.database.Room

interface CreateRoomRepo {
    suspend fun createRoom(name:String,category:String,description:String)

    suspend fun getAllRoom() : List<Room>
}