package com.mis.route.chatapp.ui.createroom.repo

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.mis.route.chatapp.database.Room
import kotlinx.coroutines.tasks.await

class CreateRoomRepoImpl : CreatRoomRepo {
    override suspend fun createRoom(name: String, category: String, description: String) {
       val docRef =
           Firebase.firestore.collection(Room.ROOM_COLLECTION_NAME).document()
        val room = Room(docRef.id,name,description,category)
        docRef.set(room).await()
    }
}