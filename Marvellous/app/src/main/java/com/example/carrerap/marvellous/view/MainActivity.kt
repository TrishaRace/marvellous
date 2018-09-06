package com.example.carrerap.marvellous.view


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.Character
import com.example.carrerap.marvellous.model.Comics


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadCharacterList()

    }

    //probar privados

    fun loadCharacterList() {
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_container_1, CharactersListFragment()).addToBackStack("charactersList").commit()
    }

    fun loadComments(character: Character) {
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_container_1, CharacterInfoFragment.newInstance(character)).addToBackStack("charactersInfo").commit()
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_container_2, CommentsFragment.newInstance(character)).addToBackStack("comments").commit()
    }

    fun loadComicList(comicInfoUri: String) {
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_container_2, ComicsListFragment.newInstance(comicInfoUri)).addToBackStack("comicInfo").commit()
    }


}

