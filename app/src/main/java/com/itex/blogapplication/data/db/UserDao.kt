package com.itex.blogapplication.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.itex.blogapplication.data.db.entities.CURRENT_USER_ID
import com.itex.blogapplication.data.db.entities.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun userin(user: User): Long

    @Delete
    fun userout(user: User)

    @Query("SELECT * FROM user WHERE uid = $CURRENT_USER_ID")
    fun getuser(): LiveData<User>
}