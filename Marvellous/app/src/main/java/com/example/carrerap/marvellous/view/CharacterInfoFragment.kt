package com.example.carrerap.marvellous.view

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.Character
import com.example.carrerap.marvellous.model.Comment
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_info_fragment.*
import java.math.BigInteger
import java.util.*


class CharacterInfoFragment : android.support.v4.app.Fragment() {

    lateinit var character: Character
    lateinit var mDatabase :DatabaseReference


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

        mDatabase = FirebaseDatabase.getInstance().getReference()

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                val post = dataSnapshot.getValue<Comment>(Comment::class.java)
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        //mPostReference.addValueEventListener(postListener)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.character_info_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadInfoCharacter(view)
        val comicsButton = b_comics


        comicsButton.setOnClickListener {
            (activity as MainActivity).loadComicsList(character.comics)
        }
        val et_comment_username = et_comment_username
        val et_comment_body = et_comment_body
        val b_send_comment = b_send_comment


        et_comment_username.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                if (et_comment_body.text != null && et_comment_username != null) {
                    b_send_comment.visibility = View.VISIBLE
                } else {
                    b_send_comment.visibility = View.GONE
                }
            }
        })



    }

    private fun loadInfoCharacter(view: View) {

        Picasso.get().load(character.photoUrl).into(iv_character_photo)
        tv_character_name.text = character.name
        tv_character_info.text = character.info

    }

    private fun sendComment() {
        val random:Random = Random(20)
        val name: String = et_comment_username.text.toString()
        val body: String = et_comment_body.text.toString()
        val userId: String = BigInteger(50, random).toString()
        // si solo se quiere pasar la view como parametro: view.setOnClickListener { doSomething(it) }
        // si solo se quiere pasar la view como parametro: view.setOnClickListener { doSomething(it) }

        val comment: Comment = Comment(name, body)
        mDatabase.child("comments").child(userId).setValue(comment)
    }


}
