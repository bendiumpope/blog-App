package com.itex.blogapplication.data.repositories

import com.itex.blogapplication.data.db.AppDatabase
import com.itex.blogapplication.data.db.entities.User
import com.itex.blogapplication.data.network.MyApi
import com.itex.blogapplication.data.network.SafeApiRequest
import com.itex.blogapplication.data.network.responses.AuthResponse
import retrofit2.Response



class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase): SafeApiRequest() {

    suspend fun userLogin(email:String, password: String): AuthResponse{

        return apiRequest { api.userLogin(email,password) }
    }

    suspend fun userSignup(

        name:String,
        email:String,
        password: String
    ): AuthResponse{

        return apiRequest{ api.userSignup(name, email, password)}
    }

    suspend fun saveUser(user: User) = db.getUserDao().userin(user)

    fun getUser() = db.getUserDao().getuser()
}