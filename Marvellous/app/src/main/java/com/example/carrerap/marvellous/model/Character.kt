package com.example.carrerap.marvellous.model

import android.os.Parcel
import android.os.Parcelable


data class Character(val id: Int, val name: String, val info: String, val photoUrl: String, val comics: Comics) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Comics::class.java.classLoader)
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeInt(id)
            writeString(name)
            writeString(info)
            writeString(photoUrl)
            writeParcelable(comics,1)
        }
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object CREATOR : Parcelable.Creator<Character> {
        override fun createFromParcel(parcel: Parcel): Character {
            return Character(parcel)
        }

        override fun newArray(size: Int): Array<Character?> {
            return arrayOfNulls(size)
        }
    }

}

