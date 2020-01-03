package com.itex.blogapplication.ui.home.addblog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.itex.blogapplication.R
import com.itex.blogapplication.databinding.AddBlogFragmentBinding
import com.itex.blogapplication.ui.home.blog.Blog
import com.itex.blogapplication.ui.home.blog.BlogViewModel
import kotlinx.android.synthetic.main.add_blog_fragment.*


class AddBlogFragment : Fragment() {

    private var blog:Blog?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.add_blog_fragment, container, false)
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


        addBlogBtn.setOnClickListener {

            var author:String = authorName.text.toString().trim()
            var title:String = blogTitle.text.toString().trim()
            var story:String = blogStory.text.toString().trim()


            val imageUrl="https://cdn.vox-cdn.com/thumbor/wI3iu8sNbFJSQB4yMLsoPMNzIHU=/0x0:3368x3368/1200x800/filters:focal(1188x715:1726x1253)/cdn.vox-cdn.com/uploads/chorus_image/image/62994726/AJ_Finn_author_photo_color_photo_courtesy_of_the_author.0.jpg"

            val captureBlog = Blog(
                0,
                imageUrl,
                author,
                title,
                story
            )

            if(blog==null){
                model.setBlog(captureBlog, context!!)
            }else{

                captureBlog.id = blog!!.id
                model.updateBlog(captureBlog, context!!)

            }

            this.findNavController().navigateUp()
        }

    }


}
