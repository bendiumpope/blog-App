package com.itex.blogapplication.ui.home.addblog

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.itex.blogapplication.R

class AddBlogFragment : Fragment() {

    companion object {
        fun newInstance() = AddBlogFragment()
    }

    private lateinit var viewModel: AddBlogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_blog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddBlogViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
