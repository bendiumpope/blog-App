package com.itex.blogapplication.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.itex.blogapplication.data.repositories.UserRepository

class AuthViewModel : ViewModel(){

    var email: String? = null
    var password: String?=null


    var authListener: AuthListener?=null

    fun onLoginButtonClick(view:View){
        authListener?.onStarted()


        if(email.isNullOrEmpty() && password.isNullOrEmpty()){

            authListener?.onFailureFour()

            return

        }else if(email.isNullOrEmpty()){

            authListener?.onFailureTwo()

            return
        }else if(password.isNullOrEmpty()){
            authListener?.onFailureThree()

            return
        }

        val loginResponse = UserRepository().userLogin(email!!, password!!)

        authListener?.onSuccess(loginResponse)

    }

}