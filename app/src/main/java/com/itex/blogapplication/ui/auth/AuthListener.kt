package com.itex.blogapplication.ui.auth

import androidx.lifecycle.LiveData
import com.itex.blogapplication.data.db.entities.User

interface AuthListener {

    fun onStarted()

    fun onSuccess(user: User)

    fun onFailureOne()

    fun onFailureTwo()

    fun onFailureThree()

    fun onFailureFour()

    fun onFailureError(massage:String)

}