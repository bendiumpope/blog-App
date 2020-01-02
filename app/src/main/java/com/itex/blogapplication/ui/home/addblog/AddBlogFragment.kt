package com.itex.blogapplication.ui.home.addblog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.itex.blogapplication.databinding.AddBlogFragmentBinding
import com.itex.blogapplication.ui.home.blog.Blog
import com.itex.blogapplication.ui.home.blog.BlogViewModel

class AddBlogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = AddBlogFragmentBinding.inflate(inflater, container, false)

        val model = ViewModelProviders.of(this)[BlogViewModel::class.java]


        binding.addBlogBtn.setOnClickListener {

            var author:String = binding.authorName.text.toString()
            var title:String = binding.blogTitle.text.toString()
            var story:String = binding.blogStory.text.toString()


            val imageUrl="https://cdn.vox-cdn.com/thumbor/wI3iu8sNbFJSQB4yMLsoPMNzIHU=/0x0:3368x3368/1200x800/filters:focal(1188x715:1726x1253)/cdn.vox-cdn.com/uploads/chorus_image/image/62994726/AJ_Finn_author_photo_color_photo_courtesy_of_the_author.0.jpg"

            val captureBlog = Blog(
                0,
                imageUrl,
                author,
                title,
                story
            )

            model.setBlog(captureBlog, context!!)

            this.findNavController().navigateUp()
        }

        return binding.root
    }


}
