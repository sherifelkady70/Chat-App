package com.mis.route.chatapp.ui.auth.fragments.repo

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mis.route.chatapp.database.User
import kotlinx.coroutines.tasks.await

class AuthRepoImpl : AuthRepo {
    override suspend fun register(userName:String, email:String, password:String) : User {
        val authResult =
        Firebase.auth.createUserWithEmailAndPassword(email,password).await()
        val user = User(uid = authResult.user?.uid,userName,email)
        val myDoc =
            Firebase.firestore.collection(User.COLLECTION_NAME).document(authResult.user?.uid!!)
        myDoc.set(user)
        return user
    }

    override suspend fun login(email: String, password: String): User {
       val authResult =
           Firebase.auth.signInWithEmailAndPassword(email,password).await()
        val docRef =
            Firebase.firestore.collection(User.COLLECTION_NAME).document(authResult.user!!.uid)
        val snapshot = docRef.get().await()
       return snapshot.toObject(User::class.java)!!
    }
}