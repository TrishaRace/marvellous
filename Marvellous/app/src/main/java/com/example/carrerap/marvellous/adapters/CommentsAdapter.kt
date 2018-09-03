package com.example.carrerap.marvellous.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.Comment
import kotlinx.android.synthetic.main.comment_item.view.*

class CommentsAdapter(var comments: ArrayList<Comment>) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return ViewHolder(inflate)
    }


    override fun onBindViewHolder(holder: CommentsAdapter.ViewHolder, position: Int) {
        holder.bindItems(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(comment: Comment) {
            itemView.tv_comment_username.text = comment.user
            itemView.tv_comment_body.text = comment.comment
        }
    }
}





