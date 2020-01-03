package com.itex.blogapplication.ui.home.blog

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.itex.blogapplication.data.db.OnItemClickListener
import com.itex.blogapplication.databinding.BlogListBinding

class BlogAdapter(
    val onItemClickListener: OnItemClickListener,
    var model: BlogViewModel,
    var context: Context): RecyclerView.Adapter<BlogAdapter.BlogViewHolder>(){

    var blogs: List<Blog> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {

        val layoutInflater =LayoutInflater.from(parent.context)
        val blogBinding = BlogListBinding.inflate(layoutInflater, parent, false)


        return BlogViewHolder(blogBinding)
    }

    override fun getItemCount(): Int {

        return blogs.size
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {

        val blog = blogs.get(position)
        holder.bind(blog, onItemClickListener)
        holder.binding.imageView5.setOnClickListener {
            val action = BlogFragmentDirections.actionBlogFragmentToAddBlogFragment()
            action.blogs = blogs[position]
            Navigation.findNavController(it).navigate(action)

        }
        holder.binding.imageView4.setOnClickListener {

            model.deleteBlogs(blog, holder.binding.imageView4.context)
        }

    }

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