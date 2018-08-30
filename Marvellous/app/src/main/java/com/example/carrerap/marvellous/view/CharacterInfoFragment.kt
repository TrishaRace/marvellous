package com.example.carrerap.marvellous.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.Character
import com.example.carrerap.marvellous.model.Comment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_info_fragment.*
import java.util.*

class CharacterInfoFragment : android.support.v4.app.Fragment() {

    lateinit var character: Character
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var mDatabase: DatabaseReference = database.getReference("comment")

    companion object {
        fun newInstance(character: Character): CharacterInfoFragment {
            val fragment = CharacterInfoFragment()
            val args = Bundle()
            args.putParcelable("character", character)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = arguments!!.getParcelable("character")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.character_info_fragment, container, false)

        
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadInfoCharacter(view)
        val comicsButton = b_comics

        b_send_comment.setOnClickListener {
            sendComment()
        }
        comicsButton.setOnClickListener {
            (activity as MainActivity).loadComicsList(character.comics)
        }
        val et_comment_username = et_comment_username
        val et_comment_body = et_comment_body
        val b_send_comment = b_send_comment

        et_comment_body.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (et_comment_body.text.toString() != "" && et_comment_username.text.toString() != "") {
                    b_send_comment.visibility = View.VISIBLE
                } else {
                    b_send_comment.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }
        })

        et_comment_username.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (et_comment_body.text.toString() != "" && et_comment_username.text.toString() != "") {
                    b_send_comment.visibility = View.VISIBLE
                } else {
                    b_send_comment.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }
        })


    }

    private fun loadInfoCharacter(view: View) {

        Picasso.get().load(character.photoUrl).into(iv_character_photo)
        tv_character_name.text = character.name
        tv_character_info.text = character.info

    }

    private fun sendComment() {
        val random = Random()
        val name: String = et_comment_username.text.toString()
        val body: String = et_comment_body.text.toString()
        val commentId = random.nextInt(999999999).toString()
        // si solo se quiere pasar la view como parametro: view.setOnClickListener { doSomething(it) }
        // si solo se quiere pasar la view como parametro: view.setOnClickListener { doSomething(it) }

        val comment = Comment(name, body)
        mDatabase.child(commentId).setValue(comment)

        et_comment_body.setText("")
        et_comment_username.setText("")
        b_send_comment.visibility = View.GONE
        Toast.makeText(context, getString(R.string.comment_send_toast), Toast.LENGTH_SHORT).show()
    }

}
