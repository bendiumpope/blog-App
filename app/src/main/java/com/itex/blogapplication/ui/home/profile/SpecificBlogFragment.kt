package com.itex.blogapplication.ui.home.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.itex.blogapplication.R

/**
 * A simple [Fragment] subclass.
 */
class SpecificBlogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.specific_fragment, container, false)
    }


}
