package com.example.carrerap.marvellous.model

import android.widget.ArrayAdapter

data class ComicsInfo (val data: ComicData )
data class ComicData (val count : Int,val results : ArrayList<ComicsResult>)
data class ComicsResult(val id: Int,val title:String,val  description: String,   val isbn : String,  val pageCount: Int, val dates : ArrayList<ComicDate>,val prices : ArrayList<ComicPrice>,val thumbnail: Thumbnail,val creators :ComicCreator, val characters: ComicCharacter)
data class ComicDate(val type:String, val date: String )
data class ComicPrice(val type: String, val price: String)
data class ComicCharacter(val items : ArrayList<Item>)
data class ComicCreator(val items: ArrayList<Item>)