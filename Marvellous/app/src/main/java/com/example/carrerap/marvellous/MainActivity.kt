package com.example.carrerap.marvellous
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.carrerap.marvellous.adapters.CharactersAdapter
import com.example.carrerap.marvellous.model.Character
import kotlinx.android.synthetic.main.activity_main.view.*



class MainActivity : AppCompatActivity() {

    lateinit var characterName: String
    lateinit var characterImageURL: String
    lateinit var characterDescription : String

    var characterList: ArrayList <Character> =ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        addNames()

        rv_characters.layoutManager = LinearLayoutManager (this, LinearLayout.VERTICAL,false)
        ly_activity_main.title.text="MARVEL"


       // rv_characters.adapter = CharactersAdapter(characterList, {item:Character-> View.OnClickListener(item) } )

        rv_characters.adapter = CharactersAdapter(characterList, this)

    }
    fun addNames(){

        characterList.add(Character(1, "Capitan America", "hskahdfklashlñasdjfñlsadhfklajsdh", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04"))
        characterList.add(Character(2, "Iron man", "lsdifasldgfjksadgkjafsgdj", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04"))
        characterList.add(Character(3, "Viuda negra", "lksadhflaskjdfhalkjsdgfhlkashdflkashdflkh", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04"))
        characterList.add(Character(4, "Spiderman", "ñldsjaflñksadjfñlsjadflñjasdlfkjslñdfj", "http://i.annihil.us/u/prod/marvel/i/mg/9/50/4ce18691cbf04"))

    }


}
