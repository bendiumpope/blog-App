package com.itex.blogapplication.ui.home.blog.blogview

import android.app.AlertDialog
import android.app.LauncherActivity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.itex.blogapplication.data.db.OnItemClickListener
import com.itex.blogapplication.databinding.BlogListBinding
import com.itex.blogapplication.ui.home.blog.Blog
import com.itex.blogapplication.ui.home.model.BlogViewModel

class BlogAdapter(
    private val onItemClickListener: OnItemClickListener,
    var model: BlogViewModel,
    var context: Context): RecyclerView.Adapter<BlogAdapter.BlogViewHolder>(){

    var blogs: List<Blog> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {

        val layoutInflater =LayoutInflater.from(parent.context)
        val blogBinding = BlogListBinding.inflate(layoutInflater, parent, false)


        return BlogViewHolder(
            blogBinding
        )
    }

    override fun getItemCount(): Int {

        return blogs.size
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {

        //Binding the delete, update and Blog to recyclerView
        val blog = blogs.get(position)
        holder.bind(blog, onItemClickListener)
        holder.binding.imageView5.setOnClickListener {
            val action =
                BlogFragmentDirections.actionBlogFragmentToAddBlogFragment()
            action.blogs = blogs[position]
            Navigation.findNavController(it).navigate(action)

        }

        holder.binding.imageView4.setOnClickListener {

            AlertDialog.Builder(context).apply {
                setTitle("Are you sure?")
                setMessage("You cannot undo this operation")
                setPositiveButton("Yes"){_, _ ->
                    model.deleteBlog(blog, holder.binding.imageView4.context)
                }
                setNegativeButton("No"){_, _ ->

                }
            }.create().show()


        }

        //Binding the image to the ImageView on recyclerview
        Glide.with(context).load(blog.img_url).into(holder.binding.imageView3)

    }

    //BlogViewHolder
    class BlogViewHolder(var binding: BlogListBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(blog: Blog, onItemClickListener: OnItemClickListener) {
            binding.blogs = blog
            binding.root.setOnClickListener {
                onItemClickListener.OnItemClick(blog)
            }
            binding.executePendingBindings()
        }
    }

}

