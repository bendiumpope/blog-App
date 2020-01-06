package com.itex.blogapplication.ui.auth


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.itex.blogapplication.R
import com.itex.blogapplication.data.db.entities.User
import com.itex.blogapplication.databinding.ActivitySignupBinding
import com.itex.blogapplication.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), AuthListener, KodeinAware {

    override val kodein by kodein()

    private val factory: AuthViewModelFactory by instance()
    lateinit var next_activity:Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        next_activity = Intent(this, HomeActivity::class.java)

        binding.viewmodel = viewModel

        viewModel.authListener = this


        //Remove error massages from EditText on text change
        nameSignup.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s : Editable?){}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sign_error_one.visibility = View.GONE
                sign_error_two.visibility = View.GONE
                sign_error_three.visibility = View.GONE
                sign_error_four.visibility = View.GONE
                sign_error_five.visibility = View.GONE
                sign_error_six.visibility = View.GONE
                sign_error_seven.visibility = View.GONE


                nameSignup.setBackgroundResource(R.drawable.input_drawable)
                emailSignup.setBackgroundResource(R.drawable.input_drawable)
                passwordSignup.setBackgroundResource(R.drawable.input_drawable)
                confirmpasswordSignup.setBackgroundResource(R.drawable.input_drawable)
            }
        })

        //Remove error massages from EditText on text change
        emailSignup.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s : Editable?){}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sign_error_one.visibility = View.GONE
                sign_error_two.visibility = View.GONE
                sign_error_three.visibility = View.GONE
                sign_error_four.visibility = View.GONE
                sign_error_five.visibility = View.GONE
                sign_error_six.visibility = View.GONE
                sign_error_seven.visibility = View.GONE


                nameSignup.setBackgroundResource(R.drawable.input_drawable)
                emailSignup.setBackgroundResource(R.drawable.input_drawable)
                passwordSignup.setBackgroundResource(R.drawable.input_drawable)
                confirmpasswordSignup.setBackgroundResource(R.drawable.input_drawable)
            }
        })

        //Remove error massages from EditText on text change
        passwordSignup.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s : Editable?){}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sign_error_one.visibility = View.GONE
                sign_error_two.visibility = View.GONE
                sign_error_three.visibility = View.GONE
                sign_error_four.visibility = View.GONE
                sign_error_five.visibility = View.GONE
                sign_error_six.visibility = View.GONE
                sign_error_seven.visibility = View.GONE


                nameSignup.setBackgroundResource(R.drawable.input_drawable)
                emailSignup.setBackgroundResource(R.drawable.input_drawable)
                passwordSignup.setBackgroundResource(R.drawable.input_drawable)
                confirmpasswordSignup.setBackgroundResource(R.drawable.input_drawable)
            }
        })


        //Remove error massages from EditText on text change
        confirmpasswordSignup.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s : Editable?){}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sign_error_one.visibility = View.GONE
                sign_error_two.visibility = View.GONE
                sign_error_three.visibility = View.GONE
                sign_error_four.visibility = View.GONE
                sign_error_five.visibility = View.GONE
                sign_error_six.visibility = View.GONE
                sign_error_seven.visibility = View.GONE


                nameSignup.setBackgroundResource(R.drawable.input_drawable)
                emailSignup.setBackgroundResource(R.drawable.input_drawable)
                passwordSignup.setBackgroundResource(R.drawable.input_drawable)
                confirmpasswordSignup.setBackgroundResource(R.drawable.input_drawable)
            }
        })
    }

    override fun onStarted() {

        //show progress bar
        progress_circular2.visibility=View.VISIBLE
    }

    override fun onSuccess(user: User) {
        //hide progress bar
        progress_circular2.visibility=View.GONE

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
        nameSignup.setBackgroundResource(R.drawable.error_drawable)
        sign_error_one.visibility= View.VISIBLE
    }

    override fun onFailureTwo() {
        //Error massage for onFailureTwo
        emailSignup.setBackgroundResource(R.drawable.error_drawable)
        sign_error_two.visibility = View.VISIBLE

    }

    override fun onFailureThree() {
        //Error massage for onFailureThree
        emailSignup.setBackgroundResource(R.drawable.error_drawable)
        sign_error_three.visibility=View.VISIBLE
    }

    override fun onFailureFour() {
        //Error massage for onFailureThree
        passwordSignup.setBackgroundResource(R.drawable.error_drawable)
        sign_error_four.visibility=View.VISIBLE
    }

    override fun onFailureFive() {
        //Error massage for onFailureFour
        passwordSignup.setBackgroundResource(R.drawable.error_drawable)
        sign_error_five.visibility=View.VISIBLE
    }

    override fun onFailureSix() {
        //Error massage for onFailureSix
       confirmpasswordSignup.setBackgroundResource(R.drawable.error_drawable)
        sign_error_six.visibility=View.VISIBLE
    }

    override fun onFailureSeven() {
        //Error massage for onFailureSeven
        confirmpasswordSignup.setBackgroundResource(R.drawable.error_drawable)
        sign_error_seven.visibility=View.VISIBLE
    }

    override fun onFailureError(massage: String) {
        //Error massage for onFailureError
        progress_circular2.visibility=View.GONE
        Toast.makeText(this, "$massage", Toast.LENGTH_LONG).show()
    }


}
