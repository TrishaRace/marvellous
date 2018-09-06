package com.example.carrerap.marvellous.view


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
import com.example.carrerap.marvellous.model.Characters
import com.example.carrerap.marvellous.model.Character
import kotlinx.android.synthetic.main.characters_list_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CharactersListFragment : android.support.v4.app.Fragment() {

    var BASE_URL = "http://gateway.marvel.com/v1/public/"
    var characterList: ArrayList<Character> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.characters_list_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadCharacters(view)
    }

    private fun loadCharacters(view: View) {
        val buildCharacters = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        val characters = buildCharacters.build()

        val marvelClient = characters.create(MarvelService::class.java)
        //aquí se manda el id.
        val call = marvelClient.getCharacters()

        call.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                val characters = response.body()

                if (characters != null) {
                    for (i in 0..characters.data.count - 1) {
                        val photoUrl: String = characters.data.results[i].thumbnail.path + "." + characters.data.results[i].thumbnail.extension
                        characterList.add(Character(characters.data.results[i].id, characters.data.results[i].name, characters.data.results[i].description, photoUrl, characters.data.results[i].comics,characters.data.results[i].series,characters.data.results[i].stories,characters.data.results[i].events))
                        // data class Character(val id: Int, val name: String, val info: String, val photoUrl: String)    return charactersArray
                        rv_characters.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
                        rv_characters.adapter = CharactersAdapter(characterList, view.context, { partItem: Character -> characterClicked(partItem) })
                    }
                }
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                Toast.makeText(activity, "" + call + "" + t, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun characterClicked(character: Character) {
        Toast.makeText(activity, "Clicked: ${character.name}", Toast.LENGTH_LONG).show()
        (activity as MainActivity).loadComments(character)
    }

}