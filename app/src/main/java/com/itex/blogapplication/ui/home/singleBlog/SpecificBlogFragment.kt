package com.itex.blogapplication.ui.home.singleBlog


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.itex.blogapplication.R
import com.itex.blogapplication.databinding.SpecificFragmentBinding

/**
 * A simple [Fragment] subclass.
 */
class SpecificBlogFragment : Fragment() {


    val args:SpecificBlogFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = SpecificFragmentBinding.inflate(inflater, container, false)

        binding.blogs = args.blog

        return binding.root
    }

}
