package com.example.carrerap.marvellous.view


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat.startActivityForResult
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.Character
import com.example.carrerap.marvellous.model.Comics
import java.util.Arrays.asList


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadCharacterList()

    }

    //probar privados

    fun loadCharacterList() {
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_container, CharactersListFragment()).addToBackStack("charactersList").commit()
    }

    fun loadComicsList(comics: Comics) {
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_container, ComicsListFragment.newInstance(comics)).addToBackStack("comicsList").commit()
    }


    fun loadCharacterInfo(character: Character) {
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_container, CharacterInfoFragment.newInstance(character)).addToBackStack("charactersInfo").commit()
    }

    fun loadComicInfo(comicInfoUri: String) {
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_container, ComicInfoFragment.newInstance(comicInfoUri)).addToBackStack("comicInfo").commit()
    }


}

