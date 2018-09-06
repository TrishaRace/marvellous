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
import com.example.carrerap.marvellous.adapters.ComicsAdapter
import com.example.carrerap.marvellous.model.*
import kotlinx.android.synthetic.main.characters_list_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComicsListFragment : android.support.v4.app.Fragment() {

    var comicsItems: ArrayList<ComicInfo> = ArrayList()
    lateinit var comicsUrl: String
   // var BASE_URL = "http://gateway.marvel.com/v1/public/"

    companion object {
        fun newInstance(comicsUrl: String): ComicsListFragment {
            val fragment = ComicsListFragment()
            val args = Bundle()
            args.putString("comics", comicsUrl)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        comicsUrl = arguments!!.getString("comics")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //iv_title_photo.setImageResource(R.drawable.comics)
        return inflater.inflate(R.layout.characters_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadComics()
    }

    private fun loadComics(){

            val buildComics = Retrofit.Builder()
                    .baseUrl(comicsUrl.substring(0, comicsUrl.length - 6))
                    .addConverterFactory(GsonConverterFactory.create())

            val comics = buildComics.build()

            val marvelClient = comics.create(MarvelService::class.java)
            //aquí se manda el id.
            val call = marvelClient.getComicInfo()

            call.enqueue(object : Callback<ComicsInfo> {
                override fun onResponse(call: Call<ComicsInfo>, response: Response<ComicsInfo>) {
                    val comicsInfo = response.body()

                    if (comicsInfo != null) {
                        for (i in 0..comicsInfo.data.count-1) {
                            val photoUrl: String = comicsInfo.data.results[i].thumbnail.path + "." + comicsInfo.data.results[i].thumbnail.extension
                            comicsItems.add(ComicInfo(comicsInfo.data.results[i].id,comicsInfo.data.results[i].title,comicsInfo.data.results[i].description,comicsInfo.data.results[i].isbn,comicsInfo.data.results[i].pageCount,photoUrl))
                        }
                        rv_characters.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
                        rv_characters.adapter = ComicsAdapter(comicsItems)
                    }
                }

                override fun onFailure(call: Call<ComicsInfo>, t: Throwable) {
                    Toast.makeText(activity, "" + call + "" + t, Toast.LENGTH_LONG).show()
                }
            })
    }


}