package com.example.carrerap.marvellous.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.ComicInfo
import com.example.carrerap.marvellous.model.Items
import kotlinx.android.synthetic.main.generic_item.view.*

class ComicsAdapter(var comicsInfo: ArrayList<ComicInfo>) : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.generic_item, parent, false))
    }

    override fun getItemCount(): Int {
        return comicsInfo.size
    }

    override fun onBindViewHolder(holder: ComicsAdapter.ViewHolder, position: Int) {
        holder.bindItems(comicsInfo[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(comicInfo: ComicInfo ){
            itemView.tv_generic_item.text = comicInfo.title

        }

    }


}