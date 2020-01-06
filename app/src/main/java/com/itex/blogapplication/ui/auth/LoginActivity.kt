package com.itex.blogapplication.ui.auth

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.itex.blogapplication.R
import com.itex.blogapplication.data.db.entities.User
import com.itex.blogapplication.databinding.ActivityLoginBinding
import com.itex.blogapplication.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

open class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {


    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()
    lateinit var next_activity:Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        //get user from sharedPreferences
        val sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE)

        val isloggedin = sharedPreferences.getBoolean("ISLOGGEDIN", false)

        Toast.makeText(this, "$isloggedin", Toast.LENGTH_LONG).show()

        //check if user is logged in from sharedPreference state
        if (isloggedin)
        {
            val main = Intent(this, HomeActivity::class.java)
            main.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(main)
        }

        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        next_activity = Intent(this, HomeActivity::class.java)



        binding.viewmodel = viewModel

        viewModel.authListener = this


        //Remove error massage from EditText on text change
        emailLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s : Editable?){}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                error_two.visibility = View.GONE
                error_three.visibility = View.GONE
                error_one.visibility = View.GONE

                emailLogin.setBackgroundResource(R.drawable.input_drawable)
                passwordLogin.setBackgroundResource(R.drawable.input_drawable)
            }
        })

        //Remove error massage from EditText on text change
        passwordLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s : Editable?){}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                error_two.visibility = View.GONE
                error_three.visibility = View.GONE
                error_one.visibility = View.GONE

                emailLogin.setBackgroundResource(R.drawable.input_drawable)
                passwordLogin.setBackgroundResource(R.drawable.input_drawable)
            }
        })


    }


    override fun onStarted() {
        //show progress bar
        progress_circular.visibility=View.VISIBLE
    }

    override fun onSuccess(user: User) {

        //hide progress bar
        progress_circular.visibility=View.GONE

        //save user detail in sharedPreference and set its loggedin state to true
        val sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("EMAIL", user.email)
        editor.putBoolean("ISLOGGEDIN", true)
        editor.apply()

        //prevent access to previous activities
        next_activity.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(next_activity)


    }

    override fun onFailureOne() {

        //Error massage for onFailureOne
        emailLogin.setBackgroundResource(R.drawable.error_drawable)
        error_one.visibility=View.VISIBLE
        error_two.visibility = View.GONE
        error_three.visibility=View.GONE

    }

    override fun onFailureTwo() {

        //Error massage for onFailureTwo
        emailLogin.setBackgroundResource(R.drawable.error_drawable)
        error_one.visibility = View.GONE
        error_three.visibility = View.GONE
        error_two.visibility = View.VISIBLE


    }

    override fun onFailureThree() {

        //Error massage for onFailureThree
        passwordLogin.setBackgroundResource(R.drawable.error_drawable)
        error_three.visibility = View.VISIBLE
        error_two.visibility = View.GONE
        error_one.visibility=View.GONE
    }

    override fun onFailureFour(){

        //Error massage for onFailureFour
        emailLogin.setBackgroundResource(R.drawable.error_drawable)
        passwordLogin.setBackgroundResource(R.drawable.error_drawable)
        error_two.visibility = View.VISIBLE
        error_one.visibility=View.GONE
        error_three.visibility=View.VISIBLE
    }

    override fun onFailureFive() {
        passwordLogin.setBackgroundResource(R.drawable.error_drawable)
        error_four.visibility = View.VISIBLE
        error_three.visibility = View.GONE
        error_two.visibility = View.GONE
        error_one.visibility=View.GONE
    }

    override fun onFailureSix() {
    }

    override fun onFailureSeven() {
    }

    override fun onFailureError(massage:String) {

        //Error massage for onFailureError
        progress_circular.visibility=View.GONE
        Toast.makeText(this, "$massage", Toast.LENGTH_LONG).show()
    }


}
