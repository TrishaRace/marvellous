package com.example.carrerap.marvellous

import com.example.carrerap.marvellous.model.ApiResponse
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Path

interface MarvelService {
    @GET("characters?ts=1&apikey=bb002cae0025452e0e19ba71896a9b11&hash=75772836ecf8d7ad6adabe834692d8fb")
    fun getCharacters(): Call<ApiResponse>
    @GET("id={id}?ts=1&apikey=bb002cae0025452e0e19ba71896a9b11&hash=75772836ecf8d7ad6adabe834692d8fb")
    fun getCharacterInfoByID(@Path("id")id : Int): Call<ApiResponse>
}