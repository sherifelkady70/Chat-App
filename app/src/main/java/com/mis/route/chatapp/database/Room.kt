package com.mis.route.chatapp.database

import java.io.Serializable

data class Room(
    var id : String?=null ,
    var title : String?=null,
    var description : String?=null ,
    var category : String?=null
) : Serializable{
    companion object{
        const val ROOM_COLLECTION_NAME = "ROOMCOLLECTIONNAME"
    }
}
