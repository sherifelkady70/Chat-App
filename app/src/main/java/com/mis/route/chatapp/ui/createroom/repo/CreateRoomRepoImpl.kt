package com.mis.route.chatapp.ui.createroom.repo

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObjects
import com.mis.route.chatapp.Constants
import com.mis.route.chatapp.UserProvider
import com.mis.route.chatapp.database.Room
import com.mis.route.chatapp.database.RoomMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class CreateRoomRepoImpl : CreateRoomRepo {
    override suspend fun createRoom(name: String, category: String, description: String) {
       val docRef =
           FirebaseFirestore.getInstance().collection(Room.ROOM_COLLECTION_NAME).document()
        val room = Room(docRef.id,name,description,category)
        docRef.set(room).await()
    }

    override suspend fun getAllRoom(): List<Room> {
        val collection = FirebaseFirestore.getInstance().collection(Room.ROOM_COLLECTION_NAME)
        val querySnapshot = collection.get().await()
       return querySnapshot.toObjects(Room::class.java)
    }

    override suspend fun sendMessage(message: String, roomId: String) {
        val roomDoc =
            FirebaseFirestore.getInstance().collection(Room.ROOM_COLLECTION_NAME).document(roomId) //to get doc for each room
        val messageDoc = roomDoc.collection(Constants.ROOM_MESSAGE_KEY).document() //make collection for all chat for each room and make inside it doc for every message
        val roomMessage = RoomMessage(id = roomId,
        senderId = UserProvider.user!!.uid!!,
        senderName = UserProvider.user!!.userName!!,
        content = message,
        Timestamp.now().toDate()
        )
        messageDoc.set(roomMessage).await()
    }

    override suspend fun listeningMessagesChanges(roomId: String) : Flow<List<RoomMessage>> {
        val listMessages = flow {
            FirebaseFirestore.getInstance().collection(Room.ROOM_COLLECTION_NAME).document(roomId)
                .collection(Constants.ROOM_MESSAGE_KEY).orderBy("date").snapshots().collect{
                    emit(it.toObjects(RoomMessage::class.java))
                }
        }
        return listMessages
    }
}