package com.itex.blogapplication.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer{user ->

            if(user !=null){

                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                    startActivity(it)
                }
            }
        })


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

    }

    override fun onSuccess(user: User) {

        Toast.makeText(this, "Welcome TO MIND BLOGS", Toast.LENGTH_LONG).show()
    }

    override fun onFailureOne() {

        emailLogin.setBackgroundResource(R.drawable.error_drawable)
        error_one.visibility=View.VISIBLE
        error_two.visibility = View.GONE
        error_three.visibility=View.GONE

    }

    override fun onFailureTwo() {
        emailLogin.setBackgroundResource(R.drawable.error_drawable)
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

    override fun onFailureFive() {
    }

    override fun onFailureSix() {
    }

    override fun onFailureSeven() {
    }

    override fun onFailureError(massage:String) {

        Toast.makeText(this, "$massage", Toast.LENGTH_LONG).show()
    }


}
