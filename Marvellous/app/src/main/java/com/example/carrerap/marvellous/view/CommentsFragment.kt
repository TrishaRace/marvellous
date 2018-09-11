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
import com.example.carrerap.marvellous.adapters.CommentsAdapter
import com.example.carrerap.marvellous.model.Character
import com.example.carrerap.marvellous.model.Comment
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.character_info_fragment.*
import kotlinx.android.synthetic.main.comment_fragment.*

class CommentsFragment : android.support.v4.app.Fragment() {

    lateinit var character: Character
    var commentList: ArrayList<Comment> = ArrayList()
    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var mDatabase: DatabaseReference = database.getReference("comment")

    companion object {
        fun newInstance(character: Character): CommentsFragment {
            val fragment = CommentsFragment()
            val args = Bundle()
            args.putParcelable("character", character)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = arguments!!.getParcelable("character")

        mDatabase.child(character.name.toString()).addChildEventListener(object : ChildEventListener {
            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                commentList.remove(Comment(dataSnapshot.key.toString(), dataSnapshot.child("username").getValue().toString(), dataSnapshot.child("body").getValue().toString()))
                if (rv_comments != null) {
                    rv_comments.adapter = CommentsAdapter(mDatabase.child(character.name.toString()), commentList)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, p1: String?) {

                commentList.add(Comment(dataSnapshot.key.toString(), dataSnapshot.child("username").getValue().toString(), dataSnapshot.child("body").getValue().toString()))
                if (rv_comments != null) {
                    rv_comments.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
                    rv_comments.adapter = CommentsAdapter(mDatabase.child(character.name.toString()), commentList)
                }
            }

        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.comment_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        b_send_comment.setOnClickListener {
            sendComment()
        }

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

    private fun sendComment() {

        val readWriteMap: HashMap<String, String> = hashMapOf()
        readWriteMap.put("username", et_comment_username.text.toString())
        readWriteMap.put("body", et_comment_body.text.toString())


        mDatabase.child(character.name.toString()).push().setValue(readWriteMap)

        et_comment_body.setText("")
        et_comment_username.setText("")
        b_send_comment.visibility = View.GONE
        Toast.makeText(context, getString(R.string.comment_send_toast), Toast.LENGTH_SHORT).show()
    }
}



