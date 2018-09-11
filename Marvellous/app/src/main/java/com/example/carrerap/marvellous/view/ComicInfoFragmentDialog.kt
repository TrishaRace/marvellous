package com.example.carrerap.marvellous.view

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.example.carrerap.marvellous.R
import com.example.carrerap.marvellous.model.ComicInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.comic_info_fragment.*

class ComicInfoFragmentDialog  : DialogFragment() {

    lateinit var comicInfo: ComicInfo

    companion object {
        fun newInstance(comicInfo: ComicInfo?): ComicInfoFragmentDialog {
            val fragment = ComicInfoFragmentDialog()
            val args = Bundle()
            args.putParcelable("comics", comicInfo)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        comicInfo = arguments!!.getParcelable("comics")
       tv_comic_name.text = comicInfo.title
        tv_comic_description.text= comicInfo.description
        tv_comic_characters.text = comicInfo.comicCharacters
        tv_comic_creators.text = comicInfo.comicCreators
        tv_comic_pages.text = comicInfo.pages.toString()
        tv_comic_price.text = comicInfo.price
        Picasso.get().load(comicInfo.photoUrl).into(iv_comic_photo)

        return inflater!!.inflate(R.layout.comic_info_fragment, container, false)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }



}