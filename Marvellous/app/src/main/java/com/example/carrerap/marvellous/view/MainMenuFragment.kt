package com.example.carrerap.marvellous.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carrerap.marvellous.R
import kotlinx.android.synthetic.main.main_menu_fragment.*

class MainMenuFragment : android.support.v4.app.Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.main_menu_fragment, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      /*  val comicsButton = b_comics
        val charactersButton = b_characters
        val eventsButton = b_events
        val storiesButton = b_stories
        val seriesButton = b_series

        comicsButton.setOnClickListener{

        }
        charactersButton.setOnClickListener{
            (activity as MainActivity).loadCharacterList()
        }
        eventsButton.setOnClickListener{
            (activity as MainActivity).loadEventsList()
        }
        storiesButton.setOnClickListener{
            (activity as MainActivity).loadStoriesList()
        }
        seriesButton.setOnClickListener{
            (activity as MainActivity).loadSeriesList()
        }
        seriesButton.setOnClickListener{
            (activity as MainActivity).loadComicsList()
        }*/
    }




}