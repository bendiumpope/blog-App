package com.itex.blogapplication.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.itex.blogapplication.R
import com.itex.blogapplication.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.authListener = this


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

    }


    override fun onStarted() {

    }

    override fun onSuccess() {
        Toast.makeText(this, "Logged in successful", Toast.LENGTH_LONG).show()
    }

    override fun onFailureOne() {

        emailLogin.setBackgroundResource(R.drawable.error_drawable)
        error_two.visibility = View.GONE
        error_one.visibility=View.VISIBLE
        error_three.visibility=View.GONE

    }

    override fun onFailureTwo() {
        passwordLogin.setBackgroundResource(R.drawable.error_drawable)
        error_one.visibility = View.GONE
        error_three.visibility = View.GONE
        error_two.visibility = View.VISIBLE


    }

    override fun onFailureThree() {
        passwordLogin.setBackgroundResource(R.drawable.error_drawable)
        error_three.visibility = View.VISIBLE
        error_two.visibility = View.GONE
        error_one.visibility=View.GONE
    }

    override fun onFailureFour(){
        emailLogin.setBackgroundResource(R.drawable.error_drawable)
        passwordLogin.setBackgroundResource(R.drawable.error_drawable)
        error_two.visibility = View.VISIBLE
        error_one.visibility=View.GONE
        error_three.visibility=View.VISIBLE
    }


}
