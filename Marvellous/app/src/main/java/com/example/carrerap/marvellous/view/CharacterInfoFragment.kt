package com.example.carrerap.marvellous.view

import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.carrerap.marvellous.MarvelService
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.adapters.CharactersAdapter
import com.example.carrerap.marvellous.model.ApiResponse
import com.example.carrerap.marvellous.model.Character
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_info_fragment.*
import kotlinx.android.synthetic.main.characters_list_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


import retrofit2.converter.gson.GsonConverterFactory

class CharacterInfoFragment : android.support.v4.app.Fragment() {

    var BASE_URL = "http://gateway.marvel.com/v1/public/"
    var characterList: ArrayList<Character> = ArrayList()
    lateinit var character: Character

    companion object {
        fun newInstance(character: Character): CharacterInfoFragment {
            val fragment = CharacterInfoFragment()
            val args = Bundle()
            args.putParcelable("character", character)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = arguments!!.getParcelable("character")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.character_info_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadInfoCharacter(view)
    }

    private fun loadInfoCharacter(view: View) {

        Picasso.get().load(character.photoUrl).into(iv_character_photo)
        tv_character_name.text = character.name
        tv_character_info.text = character.info

    }
}
