package com.mis.route.chatapp.ui.auth.fragments.repo

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mis.route.chatapp.models.User
import kotlinx.coroutines.tasks.await

class ResgisterRepoImpl : ResgisterRepo {
    override suspend fun register(userName:String, email:String, password:String) : User {
        val authResult =
        Firebase.auth.createUserWithEmailAndPassword(email,password).await()
        val user = User(uid = authResult.user?.uid,userName,email)
        val myDoc =  Firebase.firestore.collection(User.COLLECTION_NAME).document(authResult.user?.uid!!)
        myDoc.set(user)
        return user
    }

    override suspend fun login(email: String, password: String): User {
        TODO("Not yet implemented")
    }
}