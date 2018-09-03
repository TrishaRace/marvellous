package com.example.carrerap.marvellous.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.adapters.CharactersAdapter
import com.example.carrerap.marvellous.adapters.CommentsAdapter
import com.example.carrerap.marvellous.model.Character
import com.example.carrerap.marvellous.model.Comment
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_info_fragment.*
import kotlin.collections.HashMap
import kotlinx.android.synthetic.main.characters_list_fragment.*


class CharacterInfoFragment : android.support.v4.app.Fragment() {

    lateinit var character: Character
    var commentList: ArrayList<Comment> = ArrayList()
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

        mDatabase.child(character.name).addChildEventListener(object : ChildEventListener {
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {

               commentList.add(Comment(dataSnapshot.child("username").getValue().toString(),dataSnapshot.child("body").getValue().toString()))
                rv_comments.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
                rv_comments.adapter = CommentsAdapter(commentList)
            }

            //val hashMap : HashMap <String,String>  = HashMap()
            //val comments = Comment(dataSnapshot.getValue(),dataSnapshot.child("body").getValue().toString())

            //   commentList.add(comments)
            // rv_comments.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            //rv_comments.adapter = CommentsAdapter(commentList)


        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.character_info_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadInfoCharacter()
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

    private fun loadInfoCharacter() {

        Picasso.get().load(character.photoUrl).into(iv_character_photo)
        tv_character_name.text = character.name
        tv_character_info.text = character.info

    }

    private fun sendComment() {

        val readWriteMap: HashMap<String, String> = hashMapOf()
        readWriteMap.put("username", et_comment_username.text.toString())
        readWriteMap.put("body", et_comment_body.text.toString())

        mDatabase.child(character.name).push().setValue(readWriteMap)

        et_comment_body.setText("")
        et_comment_username.setText("")
        b_send_comment.visibility = View.GONE
        Toast.makeText(context, getString(R.string.comment_send_toast), Toast.LENGTH_SHORT).show()
    }

}
