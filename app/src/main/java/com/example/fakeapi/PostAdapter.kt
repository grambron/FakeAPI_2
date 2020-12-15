package com.example.fakeapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post.view.*

class PostAdapter(
    val onClickDeletePost: (Int) -> Unit
): RecyclerView.Adapter<PostViewHolder>() {
    private var posts: List<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val holder = PostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.post,
                parent,
                false
            )
        )
        holder.root.removeButton.setOnClickListener {
            onClickDeletePost(posts[holder.adapterPosition].id)
        }
        return holder
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.title.text = posts[position].title
        holder.body.text = posts[position].body
    }

    fun setData(newPosts: List<Post>) {
        posts = newPosts
        notifyDataSetChanged()
    }
}