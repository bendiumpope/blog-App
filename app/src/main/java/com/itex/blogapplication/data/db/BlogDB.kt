package com.itex.blogapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itex.blogapplication.ui.home.blog.Blog

@Database(entities = [Blog::class], version = 1)

abstract class BlogDB: RoomDatabase() {

    abstract fun BlogDao(): BlogDao

    companion object{

        private var instance:BlogDB?=null

        fun createDB(context: Context):BlogDB{

            if(instance == null){

                instance = Room.databaseBuilder(context, BlogDB::class.java, "MyBlog")
                    .allowMainThreadQueries()
                    .build()

                return instance!!
            }

            return instance!!
        }
    }
}