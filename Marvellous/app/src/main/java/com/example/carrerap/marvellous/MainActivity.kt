package com.example.carrerap.marvellous
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.carrerap.marvellous.R.layout.character_item
import kotlinx.android.synthetic.main.activity_main.view.*



class MainActivity : AppCompatActivity() {

    lateinit var characterName: String
    lateinit var characterImageURL: String
    lateinit var characterDescription : String

    var al_names: ArrayList <String> =ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val itemView :  = character_item
        ly_activity_main.title.text="MARVEL"
        addNames()


    }

    fun addNames(){

        al_names.add("Capitan America")
        al_names.add("Iron man")
        al_names.add("Viuda negra")
        al_names.add("Spiderman")

    }


}
