package com.itex.blogapplication.ui.home.blog

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.itex.blogapplication.R

class BlogFragment : Fragment() {

    companion object {
        fun newInstance() = BlogFragment()
    }

    private lateinit var viewModel: BlogViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.blog_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BlogViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
