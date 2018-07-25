package com.example.carrerap.marvellous.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carrerap.marvellous.MarvelService
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.Character
import retrofit2.Retrofit


import retrofit2.converter.gson.GsonConverterFactory

class CharacterInfoFragment : android.support.v4.app.Fragment() {

    var BASE_URL = "http://gateway.marvel.com/v1/public/"

    var characterList: ArrayList<Character> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.character_info_fragment, container, false)
        //val id =getStringExtra("valor")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadInfoCharacter(view)
    }

    fun loadInfoCharacter(view: View) {

        val buildCharacters = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        val characters = buildCharacters.build()

        val marvelClient = characters.create(MarvelService::class.java)
        //aquí se manda el id.
        val call = marvelClient.getCharacterInfoByID(id)

    }
}