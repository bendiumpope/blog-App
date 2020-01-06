package com.itex.blogapplication.ui.auth

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.itex.blogapplication.data.repositories.UserRepository
import com.itex.blogapplication.ui.home.HomeActivity
import com.itex.blogapplication.util.ApiException
import com.itex.blogapplication.util.Coroutines
import com.itex.blogapplication.util.NoInternetException

class AuthViewModel(
    //injecting UserRepository
    private val repository: UserRepository
) : ViewModel(){


    var name:String?=null
    var email: String? = null
    var password: String?=null
    var passwordconfirm: String?=null


    var authListener: AuthListener?=null


//    fun getLoggedInUser() = repository.getUser()

    //onButtonClick function
    fun onLoginButtonClick(view:View){

        //checking if the email and password field is empty or null
        if(email.isNullOrEmpty() && password.isNullOrEmpty()){

            //calling the onFaliurFour function from the AuthListener Interface
            authListener?.onFailureFour()

            return

            //checking if the email is empty or null
        }else if(email.isNullOrEmpty()){

            //calling the onFaliureTwo function from the AuthListener Interface
            authListener?.onFailureTwo()

            return

            //checking if the password is empty or null
        }else if(password.isNullOrEmpty()){

            //calling the onFaliureThree function from the AuthListener Interface
            authListener?.onFailureThree()

            return
        }


        Coroutines.main{
            try{

                //calling the onStarted function from the AuthListener Interface
                authListener?.onStarted()

                //passing the user email and password to UserRepository to check if user exists
                val authResponse = repository.userLogin(email!!,password!!)

                authResponse.user?.let {
                    //calling the onSuccess function from the AuthListener Interface if user exists
                        authListener?.onSuccess(it)

                }

                //calling the onFaliureError function from the AuthListener Interface if there is an error
                authListener?.onFailureError(authResponse.message!!)

            }catch(e: ApiException){

                //calling the onFaliureError function from the AuthListener Interface if User dont exist
                authListener?.onFailureError("Please Signup")
            }catch (e: NoInternetException){

                //calling the onFaliureError function from the AuthListener Interface if there is no network
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

    //onButtonClick function
    fun OnSignupButtonClick(view:View){


        //Checking if user provided a name

        if(name.isNullOrEmpty()){

            //calling the onFailureOne function in Authlistener if user dont provide name
            authListener?.onFailureOne()

            return

        }//Checking if user provided a email
        if(email.isNullOrEmpty()){

            //calling the onFailureTwo function in Authlistener if user dont provide email

            authListener?.onFailureTwo()

            return

        }//Checking if user provided a password
        if(password.isNullOrEmpty()){

            //calling the onFailureThree function in Authlistener if user dont provide Password
            authListener?.onFailureThree()

            return
        }//Checking if user provided a confirmPassword
        if(passwordconfirm.isNullOrEmpty()){

            //calling the onFailureFour function in Authlistener if user dont provide confirmPassword

            authListener?.onFailureFour()

            return
        }//Checking if password and confirmPassword matches
        if(password != passwordconfirm){

            //calling the onFailureseven function in Authlistener if password and confirmPassword dont match

            authListener?.onFailureSeven()

            return
        }

//
        Coroutines.main{
            try{

                //calling the onStarted function from the AuthListener Interface
                authListener?.onStarted()

                //passing the user email and password to UserRepository to create user
                val authResponse = repository.userSignup(name!!, email!!,password!!)

                authResponse.user?.let {

                    //calling the onSuccess function from the AuthListener Interface if user is saved
                    authListener?.onSuccess(it)
                }

                //calling the onFaliureError function from the AuthListener Interface if there is an error

                authListener?.onFailureError(authResponse.message!!)

            }catch(e: ApiException){

                //calling the onFaliureError function from the AuthListener Interface if there is an error
                authListener?.onFailureError("An Error Occured")

            }catch (e: NoInternetException){

                //calling the onFaliureError function from the AuthListener Interface if there is no network
                authListener?.onFailureError(e.message!!)
            }


        }

    }

}