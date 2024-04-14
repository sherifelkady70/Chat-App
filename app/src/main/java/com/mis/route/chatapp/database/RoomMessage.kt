package com.mis.route.chatapp.database

import java.util.Date

data class RoomMessage(
    val id : String = " ",
    val senderId : String=" ",
    val senderName : String=" ",
    val content : String=" ",
    val date : Date? = null
)
