package com.itex.blogapplication.ui.auth

import androidx.lifecycle.LiveData

interface AuthListener {

    fun onStarted()

    fun onSuccess(loginResponse: LiveData<String>)

    fun onFailureOne()

    fun onFailureTwo()

    fun onFailureThree()

    fun onFailureFour()

}