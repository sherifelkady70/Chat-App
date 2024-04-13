package com.mis.route.chatapp.database

data class RoomMessage(
    val id : String,
    val senderId : String,
    val senderName : String,
    val content : String
)
