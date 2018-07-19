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
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_item.view.*


class CharactersAdapter(var characters: ArrayList<Character>, var context: Context): RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersAdapter.ViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.character_item,parent, false)
        return ViewHolder(inflate)
    }


    override fun onBindViewHolder(holder: CharactersAdapter.ViewHolder, position: Int) {
        holder.bindItems(characters[position])
    }

    override fun getItemCount(): Int {
        return characters.size
        //fhasdlkfhalksdhflkasjdhflkajsdhfklj
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(hero: Character){
            itemView.tv_character_name.text = hero.name
            itemView.tv_character_info.text = hero.info
            Picasso.with(itemView.context).load(hero.photoUrl).into(itemView.iv_character_photo)
        }
    }

    /*
    //class CharactersAdapter(val characters: ArrayList<Character>, val listener: (Character) -> Unit): RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {
    override fun getItemCount() = characters.size

    //  override fun onBindViewHolder( holder: ViewHolder, position: Int)  = holder.bind(characters[position], listener)
    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.bind(characters[position],context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(ListView(parent.context))



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // fun bind(item: Character, listener: (Character) -> Unit) {

        val nameView = itemView.findViewById(R.id.tv_character_name) as TextView
        val infoView = itemView.findViewById(R.id.tv_character_info) as TextView
        val photoView = itemView.findViewById(R.id.iv_character_photo) as ImageView

        fun bind(character: Character, context : Context){

            nameView.text=character.name
            infoView.text=character.info
            Picasso.with(context).load(character.photoUrl).into(photoView)
            //  itemView.setOnClickListener { listener(character) }
        }
    }
    */

}


