package com.mis.route.chatapp.ui.createroom.repo

interface CreatRoomRepo {
    suspend fun createRoom(name:String,category:String,description:String)
}