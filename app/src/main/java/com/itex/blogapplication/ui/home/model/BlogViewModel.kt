package com.itex.blogapplication.ui.home.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itex.blogapplication.data.db.BlogDB
import com.itex.blogapplication.ui.home.blog.Blog

class BlogViewModel: ViewModel() {

    private val blog: MutableLiveData<List<Blog>> by lazy {
        MutableLiveData<List<Blog>>().also {
            loadBlogs()
        }
    }

    fun getBlogs(context: Context): LiveData<List<Blog>> {

        return BlogDB.createDB(context).BlogDao().fetchBlog()
    }

    fun setBlog(blog: Blog, context: Context){

        return BlogDB.createDB(context).BlogDao().insertBlog(blog)
    }

    fun updateBlog(blog: Blog, context: Context){

        return BlogDB.createDB(context).BlogDao().updateBlog(blog)
    }

    fun deleteBlog(blog: Blog, context: Context){
        return BlogDB.createDB(context).BlogDao().deleteBlog(blog)
    }

    private fun loadBlogs() {

    }

}