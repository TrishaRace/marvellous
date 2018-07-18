package com.example.carrerap.marvellous
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.bumptech.glide.Glide
import com.example.carrerap.marvellous.R.id.*
import com.example.carrerap.marvellous.R.layout.character_item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item.view.*

//class MainAdapter(val items: List<Item>, val listener: (Item) -> Unit)

class MainAdapter(val items: List<Item>, val listener: (Item) -> Unit): RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun getItemCount()= items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.bind(items[position], listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ListView(parent.context))



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item, listener: (Item) -> Unit) {
            itemView.tv_character_name.text=item.name
            itemView.tv_character_info.text=item.info
            Picasso.with(character_item.ctx).load(item.photoUrl).into(iv_character_photo)
          //  15 Picasso.with(itemView.ctx).load(iconUrl).into(iconView)
            setOnClickListener { listener(item) }
           // Glide.with(this.context).asBitmap().load(item.photoUrl).into(val photo)
          //  itemView.iv_character_photo.setImageResource(item.photo)

        }
    }

}


