package com.mis.route.chatapp.ui.auth.fragments.repo

import com.mis.route.chatapp.database.User

interface AuthRepo {
    suspend fun register(userName:String,email:String,password:String) : User

    suspend fun login(email: String,password: String):User
}