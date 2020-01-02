package com.itex.blogapplication.ui.home.blog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.itex.blogapplication.R
import com.itex.blogapplication.data.db.OnItemClickListener
import com.itex.blogapplication.databinding.BlogFragmentBinding
import kotlinx.android.synthetic.main.add_blog_fragment.*


class BlogFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = BlogFragmentBinding.inflate(inflater, container, false)

        val model = ViewModelProviders.of(this)[BlogViewModel::class.java]

        val recyclerView = binding.recycler

        recyclerView.layoutManager = LinearLayoutManager(context)

        val imageUrl="https://cdn.vox-cdn.com/thumbor/wI3iu8sNbFJSQB4yMLsoPMNzIHU=/0x0:3368x3368/1200x800/filters:focal(1188x715:1726x1253)/cdn.vox-cdn.com/uploads/chorus_image/image/62994726/AJ_Finn_author_photo_color_photo_courtesy_of_the_author.0.jpg"


        var adapter =
            BlogAdapter(object :

                OnItemClickListener{

                override fun OnItemClick(blog: Blog){

                 val action = BlogFragmentDirections.actionBlogFragmentToSpecificBlogFragment2(
                            blog,imageUrl
                 )
                    findNavController().navigate(action)
                }

            }, model.apply {

            },context!!)

        recyclerView.adapter = adapter

        model.getBlogs(context!!).observe(this, Observer<List<Blog>>{ blogs ->

            blogs?.let {

                adapter.blogs = blogs

                adapter.notifyDataSetChanged()
            }

            // Update UI
        })

        return binding.root


    }
}
