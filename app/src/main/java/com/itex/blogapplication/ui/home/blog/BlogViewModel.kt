package com.itex.blogapplication.ui.home.blog

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.itex.blogapplication.data.db.BlogDB

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

    fun deleteBlogs(blog: Blog, context: Context){
        return BlogDB.createDB(context).BlogDao().deleteBlog(blog)
    }

    private fun loadBlogs() {
        // Do an asynchronous operation to fetch users.
    }
}