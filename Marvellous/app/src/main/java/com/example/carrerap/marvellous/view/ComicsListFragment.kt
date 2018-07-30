package com.example.carrerap.marvellous.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.adapters.ComicsAdapter
import com.example.carrerap.marvellous.model.Comics
import com.example.carrerap.marvellous.model.Items
import kotlinx.android.synthetic.main.characters_list_fragment.*

class ComicsListFragment : android.support.v4.app.Fragment() {

    lateinit var comics: Comics

    companion object {
        fun newInstance(comics: Comics): ComicsListFragment {
            val fragment = ComicsListFragment()
            val args = Bundle()
            args.putParcelable("comics", comics)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        comics = arguments!!.getParcelable("comics")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.characters_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadComics(view)
    }

    fun loadComics(view: View) {

        var comicsItems: ArrayList<Items> = ArrayList()

        for (i in 0 until comics.returned - 1) {
            comicsItems.add(comics.items[i])
        }

        rv_characters.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)

        rv_characters.adapter = ComicsAdapter(comicsItems, { partItem: Items -> comicClicked(partItem.resourceURI) })

    }

    fun comicClicked(comicInfoUri : String ){


        (activity as MainActivity).loadComicInfo(comicInfoUri)

    }

}