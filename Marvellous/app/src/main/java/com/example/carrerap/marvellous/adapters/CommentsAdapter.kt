package com.example.carrerap.marvellous.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.R.id.b_delete
import com.example.carrerap.marvellous.model.Character
import com.example.carrerap.marvellous.model.Comment
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.comment_item.view.*

class CommentsAdapter(var mDatabase: DatabaseReference, var comments: ArrayList<Comment>) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsAdapter.ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.comment_item, parent, false)
        return ViewHolder(inflate)
    }


    override fun onBindViewHolder(holder: CommentsAdapter.ViewHolder, position: Int) {
        holder.bindItems(mDatabase, comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(mDatabase: DatabaseReference, comment: Comment) {
            itemView.tv_comment_username.text = comment.user
            itemView.tv_comment_body.text = comment.comment
            itemView.b_delete.setOnClickListener({
                mDatabase.child(comment.id).removeValue()
            })
            itemView.b_edit.setOnClickListener({

            })
        }
    }
}

