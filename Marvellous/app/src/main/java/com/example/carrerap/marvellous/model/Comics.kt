package com.example.carrerap.marvellous.model

import android.os.Parcel
import android.os.Parcelable

data class Comics(val available: Int, val collectionURI: String, val items: ArrayList<Item>, val returned: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            arrayListOf<Item>().apply {
                parcel.readArrayList( Item::class.java.classLoader)
            },
            parcel.readInt())


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(available)
        parcel.writeString(collectionURI)
        parcel.writeInt(returned)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comics> {
        override fun createFromParcel(parcel: Parcel): Comics {
            return Comics(parcel)
        }

        override fun newArray(size: Int): Array<Comics?> {
            return arrayOfNulls(size)
        }
    }
}
