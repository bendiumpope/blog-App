package com.itex.blogapplication.ui.home.blog.blogview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.itex.blogapplication.data.db.OnItemClickListener
import com.itex.blogapplication.databinding.BlogFragmentBinding
import com.itex.blogapplication.ui.home.blog.Blog
import com.itex.blogapplication.ui.home.model.BlogViewModel


class BlogFragment: Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding = BlogFragmentBinding.inflate(inflater, container, false)

        val model = ViewModelProviders.of(this)[BlogViewModel::class.java]


        //binding the recyclerview from the view
        val recyclerView = binding.recycler


        //setting a layout manager for the recyclerview
        recyclerView.layoutManager = LinearLayoutManager(context)


        //parsing onclicklistener to the adapter to navigate
        var adapter =
            BlogAdapter(object :

                OnItemClickListener {

                override fun OnItemClick(blog: Blog) {

                    val action =
                        BlogFragmentDirections.actionBlogFragmentToSpecificBlogFragment2(
                            blog
                        )
                    findNavController().navigate(action)
                }

            }, model.apply {

            }, context!!)

        //setting up the adapter
        recyclerView.adapter = adapter


        //observing changes in the Blog via the viewModel
        model.getBlogs(context!!).observe(this, Observer<List<Blog>>{ blogs ->

            blogs?.let {

                adapter.blogs = blogs

                adapter.notifyDataSetChanged()
            }
        })

        return binding.root


    }


}
