package com.mis.route.chatapp.ui.auth.fragments.register.repo

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mis.route.chatapp.database.User
import kotlinx.coroutines.tasks.await

class ResgisterRepoImpl : ResgisterRepo {
    override suspend fun register(userName:String, email:String, password:String) : User {
        val authResult =
        Firebase.auth.createUserWithEmailAndPassword(email,password).await()
        val user = User(uid = authResult.user?.uid,userName,email)
        val myDoc =  Firebase.firestore.collection(User.COLLECTION_NAME).document()
        myDoc.set(myDoc)
        return user
    }

    override suspend fun login(email: String, password: String): User {
        TODO("Not yet implemented")
    }
}