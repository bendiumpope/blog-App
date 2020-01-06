package com.itex.blogapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.itex.blogapplication.R
import com.itex.blogapplication.ui.auth.LoginActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    lateinit var next_activity:Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Toast.makeText(this, "Welcome TO MIND BLOGS", Toast.LENGTH_LONG).show()


        next_activity = Intent(this, LoginActivity::class.java)

        //setting toolbar for the home activity
        setSupportActionBar(toolbar)

        //setting up navigation controller
        val navController = Navigation.findNavController(this, R.id.fragment)

        NavigationUI.setupWithNavController(nav_view, navController)

        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu):Boolean {
        // Inflate the menu; this adds items to the action bar
        MenuInflater(this).inflate(R.menu.signout, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //get the user from shared preference
        val sharedPreferences = getSharedPreferences("USER_CREDENTIALS", MODE_PRIVATE)
        when (item.getItemId()) {

            //sign user out by erasing user from sharedPreferences
            R.id.signout -> {
                sharedPreferences.edit().putBoolean("ISLOGGEDIN", false).apply()
                startActivity(next_activity)
                finish()
                return (true)


            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.fragment), drawer_layout
        )
    }
}
