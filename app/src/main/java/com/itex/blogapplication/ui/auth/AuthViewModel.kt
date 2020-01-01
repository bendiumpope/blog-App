package com.itex.blogapplication.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.itex.blogapplication.data.repositories.UserRepository
import com.itex.blogapplication.util.ApiException
import com.itex.blogapplication.util.Coroutines
import com.itex.blogapplication.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel(){


    var name:String?=null
    var email: String? = null
    var password: String?=null
    var passwordconfirm: String?=null


    var authListener: AuthListener?=null


    fun getLoggedInUser() = repository.getUser()

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


        Coroutines.main{
            try{

                val authResponse = repository.userLogin(email!!,password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }

                authListener?.onFailureError(authResponse.message!!)

            }catch(e: ApiException){

                authListener?.onFailureError(e.message!!)
            }catch (e: NoInternetException){

                authListener?.onFailureError(e.message!!)
            }


        }

    }

    fun OnLogin(view: View){

        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }


    fun OnSignup(view: View){

        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun OnSignupButtonClick(view:View){
        authListener?.onStarted()


        if(name.isNullOrEmpty()){

            authListener?.onFailureOne()

        }
        if(email.isNullOrEmpty()){

            authListener?.onFailureTwo()

            return

        }
        if(password.isNullOrEmpty()){

            authListener?.onFailureThree()

            return
        }
        if(passwordconfirm.isNullOrEmpty()){
            authListener?.onFailureFour()

            return
        }
        if(password != passwordconfirm){
            authListener?.onFailureSeven()

            return
        }

//
        Coroutines.main{
            try{

                val authResponse = repository.userSignup(name!!, email!!,password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }

                authListener?.onFailureError(authResponse.message!!)

            }catch(e: ApiException){

                authListener?.onFailureError(e.message!!)
            }catch (e: NoInternetException){

                authListener?.onFailureError(e.message!!)
            }


        }

    }

}