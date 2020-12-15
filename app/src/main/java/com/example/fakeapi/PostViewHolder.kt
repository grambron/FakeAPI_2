package com.example.fakeapi

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post.view.*

class PostViewHolder(val root: View): RecyclerView.ViewHolder(root) {
    val title = root.title
    val body = root.body
}
