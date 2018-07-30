package com.example.carrerap.marvellous.view

import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.carrerap.marvellous.MarvelService
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.ComicInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_info_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ComicInfoFragment : android.support.v4.app.Fragment() {

    lateinit var comicInfoUri: String

    companion object {
        fun newInstance(comicInfoUri: String): ComicInfoFragment {
            val fragment = ComicInfoFragment()
            val args = Bundle()
            args.putString("comicInfoUri", comicInfoUri)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        comicInfoUri = arguments!!.getString("comicInfoUri")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.comic_info_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadInfoComics(view)

    }

    private fun loadInfoComics(view: View) {

        val buildCharacters = Retrofit.Builder()
                .baseUrl(comicInfoUri+"/")
                .addConverterFactory(GsonConverterFactory.create())

        val characters = buildCharacters.build()

        val marvelClient = characters.create(MarvelService::class.java)
        //aquí se manda el id.
        val call = marvelClient.getComicInfo()

        call.enqueue(object : Callback<ComicInfo> {
            override fun onResponse(call: Call<ComicInfo>, response: Response<ComicInfo>) {
                val comicInfo = response.body()

                if (comicInfo != null) {

                    val photoUrl: String = comicInfo.thumbnail.path + "." + comicInfo.thumbnail.extension
                    Picasso.get().load(photoUrl).into(iv_comic_photo)
                    tv_comic_title.text = comicInfo.title
                    tv_comic_description.text = comicInfo.description

                }
            }

            override fun onFailure(call: Call<ComicInfo>, t: Throwable) {
                Toast.makeText(activity, "" + call + "" + t, Toast.LENGTH_LONG).show()
            }
        })
    }


}


