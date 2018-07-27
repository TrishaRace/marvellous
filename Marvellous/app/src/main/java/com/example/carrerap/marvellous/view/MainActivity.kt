package com.example.carrerap.marvellous.view


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.Character


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMainMenu()

    }

    //probar privados
    fun loadMainMenu(){
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_container, MainMenuFragment()).addToBackStack("mainMenu").commit()

    }
    fun loadCharacterList(){
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_container, CharactersListFragment()).addToBackStack("charactersList").commit()
    }
    fun loadCharacterInfo(character : Character){
        supportFragmentManager.beginTransaction().replace(R.id.ly_activity_container, CharacterInfoFragment.newInstance(character)).addToBackStack("charactersInfo").commit()
    }

}

