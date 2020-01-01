package com.itex.blogapplication.ui.auth

import com.itex.blogapplication.data.db.entities.User

interface AuthListener {

    fun onStarted()

    fun onSuccess(user: User)

    fun onFailureOne()

    fun onFailureTwo()

    fun onFailureThree()

    fun onFailureFour()

    fun onFailureFive()

    fun onFailureSix()

    fun onFailureSeven()

    fun onFailureError(massage:String)

}