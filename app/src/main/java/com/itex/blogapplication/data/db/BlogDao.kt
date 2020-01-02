package com.itex.blogapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itex.blogapplication.ui.home.blog.Blog


@Dao
interface BlogDao {

    @Insert
    fun insertBlog(blog: Blog)

    @Update
    fun updateBlog(blog: Blog)

    @Query("SELECT * FROM blogTable")
    fun fetchBlog(): LiveData<List<Blog>>

    @Delete
    fun deleteBlog(blog: Blog)
}