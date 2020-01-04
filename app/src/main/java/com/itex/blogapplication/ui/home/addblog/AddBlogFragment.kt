package com.itex.blogapplication.ui.home.addblog

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.itex.blogapplication.R
import com.itex.blogapplication.databinding.AddBlogFragmentBinding
import com.itex.blogapplication.ui.home.blog.Blog
import com.itex.blogapplication.ui.home.blog.BlogViewModel
import kotlinx.android.synthetic.main.add_blog_fragment.*


class AddBlogFragment : Fragment(){

    private var blog:Blog?=null
    var imageaddress: Uri?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.add_blog_fragment, container, false)

        val bot = view.findViewById<Button>(R.id.blogbtn)
           bot.setOnClickListener {

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                if(checkSelfPermission(context!!, Manifest.permission.READ_EXTERNAL_STORAGE)==
                    PermissionChecker.PERMISSION_DENIED){

                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                }else{
                    //permission already granted
                    pickImageFromGallery()

                }
            }else{

                // system OS is < Marshmallow
                pickImageFromGallery()
            }
        }

        return view

    }

    private fun pickImageFromGallery() {

        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent,"select a picture"), IMAGE_PICK_CODE)

    }

    companion object{
        //Image pick code
        private val IMAGE_PICK_CODE = 1000

        //Permission code
        private val PERMISSION_CODE = 1001

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when(requestCode){
            PERMISSION_CODE ->{
                if(grantResults.size>0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){

                    //permission from popup granted
                    pickImageFromGallery()
                }else{

                    //permission from popup denied
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode==Activity.RESULT_OK && requestCode== IMAGE_PICK_CODE){
             imageaddress=data?.data
            blogImage.setImageURI(data?.data)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val model = ViewModelProviders.of(this)[BlogViewModel::class.java]

        arguments?.let {
            blog = AddBlogFragmentArgs.fromBundle(it).blogs
            authorName.setText(blog?.author)
            blogTitle.setText(blog?.title)
            blogStory.setText(blog?.story)

        }

        authorName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s : Editable?){}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                blog_error_two.visibility = View.GONE
                blog_error_three.visibility = View.GONE
                blog_error_one.visibility = View.GONE

                authorName.setBackgroundResource(R.drawable.input_drawable)
                blogTitle.setBackgroundResource(R.drawable.input_drawable)
                blogStory.setBackgroundResource(R.drawable.input_drawable)
            }
        })

        blogTitle.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s : Editable?){}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                blog_error_two.visibility = View.GONE
                blog_error_three.visibility = View.GONE
                blog_error_one.visibility = View.GONE

                authorName.setBackgroundResource(R.drawable.input_drawable)
                blogTitle.setBackgroundResource(R.drawable.input_drawable)
                blogStory.setBackgroundResource(R.drawable.input_drawable)
            }
        })

        blogStory.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s : Editable?){}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                blog_error_two.visibility = View.GONE
                blog_error_three.visibility = View.GONE
                blog_error_one.visibility = View.GONE

                authorName.setBackgroundResource(R.drawable.input_drawable)
                blogTitle.setBackgroundResource(R.drawable.input_drawable)
                blogStory.setBackgroundResource(R.drawable.input_drawable)
            }
        })


        addBlogBtn.setOnClickListener {


            var author: String = authorName.text.toString().trim()
            var title: String = blogTitle.text.toString().trim()
            var story: String = blogStory.text.toString().trim()

            if (author.isNullOrEmpty()) {

                authorName.setBackgroundResource(R.drawable.error_drawable)
                blog_error_one.visibility = View.VISIBLE
                blog_error_two.visibility = View.GONE
                blog_error_three.visibility = View.GONE

            } else if (title.isNullOrEmpty()) {
                blogTitle.setBackgroundResource(R.drawable.error_drawable)
                blog_error_one.visibility = View.GONE
                blog_error_two.visibility = View.VISIBLE
                blog_error_three.visibility = View.GONE
            } else if (story.isNullOrEmpty()) {
                blogStory.setBackgroundResource(R.drawable.error_drawable)
                blog_error_one.visibility = View.GONE
                blog_error_two.visibility = View.GONE
                blog_error_three.visibility = View.VISIBLE
            } else {

                val imageUrl = imageaddress.toString()
                val captureBlog = Blog(
                    0,
                    imageUrl,
                    author,
                    title,
                    story
                )


                if (blog == null) {
                    model.setBlog(captureBlog, context!!)
                } else {

                    captureBlog.id = blog!!.id
                    model.updateBlog(captureBlog, context!!)

                }

                this.findNavController().navigateUp()
            }
        }
    }

}
