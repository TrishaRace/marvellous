package com.example.carrerap.marvellous
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup

class MainAdapter(val namesList: ArrayList<String>,val infoList : ArrayList<String>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): {
        val v =LayoutInflater.from(parent.context).inflate(R.layout.activity_main,parent,false)
        return ViewHolder(v)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(namesList[position])    }

    override fun getItemCount(): Int {
        return namesList.size    }




}

class ViewHolder(itemView: RecyclerView) : RecyclerView.ViewHolder(itemView){

    fun bindItems(user: Character){
        tv_name.text=user.name
        tv_info.text=user.des
        iv_photo.setImageResource(user.image)
    }
}