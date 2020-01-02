package com.itex.blogapplication.ui.home.blog

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "blogTable")
@Parcelize
data class Blog(

    @PrimaryKey(autoGenerate = true)
    var id: Int=0,

    @ColumnInfo(name = "img_url")
    var img_url:String,

    @ColumnInfo(name = "author")
    var author:String,

    @ColumnInfo(name = "title")
    var title:String,

    @ColumnInfo(name = "story")
    var story:String

):Parcelable