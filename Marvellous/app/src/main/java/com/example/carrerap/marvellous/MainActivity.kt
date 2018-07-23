package com.example.carrerap.marvellous

import android.content.Intent
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.example.carrerap.marvellous.R.layout.activity_character_info
import com.example.carrerap.marvellous.adapters.CharactersAdapter
import com.example.carrerap.marvellous.model.ApiResponse
import com.example.carrerap.marvellous.model.Character
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL


class MainActivity : AppCompatActivity() {

    var BASE_URL = "http://gateway.marvel.com/v1/public/"

    var characterList: ArrayList<Character> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadCharacters()

    }

    private fun loadCharacters() {

        val buildCharacters = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

        val characters = buildCharacters.build()

        val marvelClient = characters.create(MarvelService::class.java!!)
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
                        rv_characters.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayout.VERTICAL, false)

                        // rv_characters.adapter = CharactersAdapter(characterList, {item:Character-> View.OnClickListener(item) } )

                        rv_characters.adapter = CharactersAdapter(characterList, this@MainActivity, { partItem: Character -> characterClicked(partItem) })
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "" + call + "" + t, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun characterClicked(character: Character) {
        Toast.makeText(this, "Clicked: ${character.name}", Toast.LENGTH_LONG).show()
        val intent = Intent(this, activity_character_info::class.java)
        intent.putExtra("CHARACTER_ID", character.id)
        startActivityForResult(intent, 1)

    }

}

