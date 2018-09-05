package com.example.carrerap.marvellous.view

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import com.example.carrerap.marvellous.R
import kotlinx.android.synthetic.main.dialog_comment_change.*

class CommentChangeDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater: LayoutInflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.dialog_comment_change, null))

        b_ok.setOnClickListener({

            b_ok.text = "PULSADO"

        })

        return builder.create()
    }


}