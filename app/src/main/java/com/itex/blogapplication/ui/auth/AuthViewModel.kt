package com.itex.blogapplication.ui.auth

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import com.itex.blogapplication.R
import kotlinx.android.synthetic.main.activity_login.*

class AuthViewModel : ViewModel(){

    var email: String? = null
    var password: String?=null


    var authListener: AuthListener?=null

    val emailPattern = ("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+").toRegex()

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
        }else if(email!!.trim().matches(emailPattern)){

            authListener?.onSuccess()
        }else{

            authListener?.onFailureOne()
        }



    }

}