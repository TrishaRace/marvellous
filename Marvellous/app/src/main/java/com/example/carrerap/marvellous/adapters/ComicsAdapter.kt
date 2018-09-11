package com.example.carrerap.marvellous.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.Character
import com.example.carrerap.marvellous.model.ComicInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_info_fragment.view.*
import kotlinx.android.synthetic.main.comic_item.view.*

class ComicsAdapter(var comicsInfo: ArrayList<ComicInfo>,val clickListener: (ComicInfo) -> Unit) : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.comic_item, parent, false))
    }

    override fun getItemCount(): Int {
        return comicsInfo.size
    }

    override fun onBindViewHolder(holder: ComicsAdapter.ViewHolder, position: Int) {
        holder.bindItems(comicsInfo[position],clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(comicInfo: ComicInfo,clickListener:(ComicInfo)->Unit ){
            itemView.tv_comicItem_name.text = comicInfo.title
            Picasso.get().load(comicInfo.photoUrl).into(itemView.iv_comicItem_photo)
            itemView.setOnClickListener { clickListener(comicInfo)}
        }


    }


}