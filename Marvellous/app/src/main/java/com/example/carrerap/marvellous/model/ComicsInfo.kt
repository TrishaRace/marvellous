package com.example.carrerap.marvellous.model

data class ComicsInfo (val data: ComicData )
data class ComicData (val count : Int,val results : ArrayList<ComicsResult>)
data class ComicsResult(val id: Int,val title:String,val  description: String,   val isbn : String,  val pageCount: Int, val thumbnail: Thumbnail)
