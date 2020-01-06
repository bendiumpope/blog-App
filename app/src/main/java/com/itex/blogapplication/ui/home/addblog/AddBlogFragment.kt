package com.itex.blogapplication.ui.home.addblog

import android.Manifest
import android.app.Activity
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
import com.itex.blogapplication.ui.home.blog.Blog
import com.itex.blogapplication.ui.home.model.BlogViewModel
import kotlinx.android.synthetic.main.add_blog_fragment.*


class AddBlogFragment : Fragment(){

    private var blog: Blog?=null
    var imageaddress: Uri?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.add_blog_fragment, container, false)

        //Handling the onclick on image selection from gallery
        val gallerButton = view.findViewById<Button>(R.id.blogbtn)
           gallerButton.setOnClickListener {

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                //Checking if permission to Read external storage is denied
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

    //Getting the user response from the permission popup
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


    //Getting the Image and setting it to the blogImage ImageView and as image URI
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode==Activity.RESULT_OK && requestCode== IMAGE_PICK_CODE){
             imageaddress=data?.data
            blogImage.setImageURI(data?.data)
        }
    }

    //Handling Blog Update
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val model = ViewModelProviders.of(this)[BlogViewModel::class.java]

        //Repopulating the editText
        arguments?.let {
            blog = AddBlogFragmentArgs.fromBundle(it).blogs
            authorName.setText(blog?.author)
            blogTitle.setText(blog?.title)
            blogStory.setText(blog?.story)

        }

        //Removing error massage from authorName EditText on textchange
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


        //Removing error massage from blogTitle EditText on textchange
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


        //Removing error massage from blogStory EditText on textchange
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


        //Adding Blog Post
        addBlogBtn.setOnClickListener {

            //Getting the user input from the editText
            var author: String = authorName.text.toString().trim()
            var title: String = blogTitle.text.toString().trim()
            var story: String = blogStory.text.toString().trim()


            //Checking if the author editText is empty or null
            if (author.isNullOrEmpty()) {

                //setting error massage on author editText
                authorName.setBackgroundResource(R.drawable.error_drawable)
                blog_error_one.visibility = View.VISIBLE
                blog_error_two.visibility = View.GONE
                blog_error_three.visibility = View.GONE

                //Checking if the title editText is empty or null
            } else if (title.isNullOrEmpty()) {
                //setting error massage on title editText
                blogTitle.setBackgroundResource(R.drawable.error_drawable)
                blog_error_one.visibility = View.GONE
                blog_error_two.visibility = View.VISIBLE
                blog_error_three.visibility = View.GONE

                //Checking if the blogstory editText is empty or null
            } else if (story.isNullOrEmpty()) {

                //setting error massage on blogstory editText
                blogStory.setBackgroundResource(R.drawable.error_drawable)
                blog_error_one.visibility = View.GONE
                blog_error_two.visibility = View.GONE
                blog_error_three.visibility = View.VISIBLE
            } else {

                //Converting the image URl to string
                val imageUrl = imageaddress.toString()

                //passing the user input to Blog data class
                val captureBlog = Blog(
                    0,
                    imageUrl,
                    author,
                    title,
                    story
                )


                //checking if the blog object holding the repopulated data is empty
                if (blog == null) {

                    //seting the blog post to the viewmodel to insert/save in the database
                    model.setBlog(captureBlog, context!!)
                    Toast.makeText(context, "Post Saved", Toast.LENGTH_SHORT).show()

                } else {

                    //seting the updated blog to the viewmodel to insert/save in the database
                    captureBlog.id = blog!!.id
                    model.updateBlog(captureBlog, context!!)
                    Toast.makeText(context, "Post Updated", Toast.LENGTH_SHORT).show()
                }

                //return navigation to the blogFragment
                this.findNavController().navigateUp()
            }
        }
    }

}
