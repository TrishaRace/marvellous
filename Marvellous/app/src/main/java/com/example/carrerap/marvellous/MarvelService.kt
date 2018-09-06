package com.example.carrerap.marvellous

import com.example.carrerap.marvellous.model.Characters
import com.example.carrerap.marvellous.model.ComicsInfo
import retrofit2.http.GET
import retrofit2.Call

interface MarvelService {
    @GET("characters?ts=1&apikey=bb002cae0025452e0e19ba71896a9b11&hash=75772836ecf8d7ad6adabe834692d8fb")
    fun getCharacters(): Call<Characters>

    @GET("comics?ts=1&apikey=bb002cae0025452e0e19ba71896a9b11&hash=75772836ecf8d7ad6adabe834692d8fb")
    fun getComicInfo() : Call<ComicsInfo>

}