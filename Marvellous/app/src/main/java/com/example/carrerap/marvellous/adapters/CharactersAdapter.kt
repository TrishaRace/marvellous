package com.example.carrerap.marvellous.adapters
import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.Character
import com.squareup.picasso.Picasso
//import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item.view.*


class CharactersAdapter(var characters: ArrayList<Character>, val context: Context, val clickListener: (Character) -> Unit): RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersAdapter.ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.character_item,parent, false)
        return ViewHolder(inflate)
    }


    override fun onBindViewHolder(holder: CharactersAdapter.ViewHolder, position: Int) {
        holder.bindItems(characters[position],context,clickListener)
    }

    override fun getItemCount(): Int {
        return characters.size

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(hero: Character, context: Context, clickListener:(Character)->Unit){
            itemView.tv_character_name.text = hero.name
            itemView.tv_character_info.text = hero.info
           // Glide.with(context).load(hero.photoUrl).into(itemView.iv_character_photo)
            Picasso.get().load(hero.photoUrl).into(itemView.iv_character_photo)
            itemView.setOnClickListener { clickListener(hero)}
        }
    }


}


