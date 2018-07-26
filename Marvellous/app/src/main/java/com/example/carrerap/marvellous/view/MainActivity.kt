package com.example.carrerap.marvellous.view


import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.Character


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadCharacterList()

    }

    //probar privados
    fun loadCharacterList(){
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_main, CharactersListFragment()).addToBackStack("charactersList").commit()
    }
    fun loadCharacterInfo(character : Character){
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_main, CharacterInfoFragment.newInstance(character)).addToBackStack("charactersInfo").commit()
    }

}

