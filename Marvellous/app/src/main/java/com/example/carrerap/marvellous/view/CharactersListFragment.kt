package com.example.carrerap.marvellous.view


import android.app.FragmentManager
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
import kotlinx.android.synthetic.main.character_info_fragment.*
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

    private fun loadCharacters(view: View){
        val buildCharacters = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        val characters = buildCharacters.build()

        val marvelClient = characters.create(MarvelService::class.java)
        //aquí se manda el id.
        val call = marvelClient.getCharacters()

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                val apiResponse = response.body()

                if (apiResponse != null) {
                    for (i in 0..apiResponse.data.count - 1) {
                        val photoUrl: String = apiResponse.data.results[i].thumbnail.path + "." + apiResponse.data.results[i].thumbnail.extension
                        characterList.add(Character(apiResponse.data.results[i].id, apiResponse.data.results[i].name, apiResponse.data.results[i].description, photoUrl))
                        // data class Character(val id: Int, val name: String, val info: String, val photoUrl: String)    return charactersArray
                        rv_characters.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

                        // rv_characters.adapter = CharactersAdapter(characterList, {item:Character-> View.OnClickListener(item) } )

                        rv_characters.adapter = CharactersAdapter(characterList, view.context, { partItem: Character -> characterClicked(partItem) })
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(activity, "" + call + "" + t, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun characterClicked(character: Character) {
        Toast.makeText(activity, "Clicked: ${character.name}", Toast.LENGTH_LONG).show()
        val intent = Intent(this.context,MainActivity::class.java)
        activity!!.supportFragmentManager.beginTransaction().replace(R.id.ly_activity_main,CharacterInfoFragment()).addToBackStack(null).commit()


        // intent.putExtra("character_id",character.id)

              //Cambiar de fragment y mandar id
    }
}