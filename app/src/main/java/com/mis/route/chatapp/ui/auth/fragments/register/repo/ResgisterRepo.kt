package com.mis.route.chatapp.ui.auth.fragments.register.repo

import com.mis.route.chatapp.database.User

interface ResgisterRepo {
    suspend fun register(userName:String,email:String,password:String) : User

    suspend fun login(email: String,password: String):User
}