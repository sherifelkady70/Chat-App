package com.mis.route.chatapp.ui.auth.fragments.register.repo

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.mis.route.chatapp.database.User
import kotlinx.coroutines.tasks.await

class ResgisterRepoImpl : ResgisterRepo {
    override suspend fun register(userName:String, email:String, password:String) : User {
        val authResult =
        Firebase.auth.createUserWithEmailAndPassword(email,password).await()
        return User(uid = authResult.user?.uid,userName,email)
    }
}