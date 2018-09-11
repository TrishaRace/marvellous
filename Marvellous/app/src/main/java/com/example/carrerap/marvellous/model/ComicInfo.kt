package com.example.carrerap.marvellous.model

import android.os.Parcel
import android.os.Parcelable

data class ComicInfo(val id: Int, val title: String?, val description: String?, val isbn: String?, val comicCharacters: String, val comicCreators: String, val pages: Int, val price: String, val photoUrl: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(title)
            writeString(description)
            writeString(isbn)
            writeString(comicCharacters)
            writeString(comicCreators)
            writeInt(pages)
            writeString(price)
            writeString(photoUrl)

        }


    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ComicInfo> {
        override fun createFromParcel(parcel: Parcel): ComicInfo {
            return ComicInfo(parcel)
        }

        override fun newArray(size: Int): Array<ComicInfo?> {
            return arrayOfNulls(size)
        }
    }
}