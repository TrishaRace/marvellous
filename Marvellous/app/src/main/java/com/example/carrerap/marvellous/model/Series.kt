package com.example.carrerap.marvellous.model

import android.os.Parcel
import android.os.Parcelable


data class Series(val available: Int, val CollectionURI: String, val items:List <Items>, val returned: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.createTypedArrayList(Items),
            parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(available)
        parcel.writeString(CollectionURI)
        parcel.writeTypedList(items)
        parcel.writeInt(returned)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Series> {
        override fun createFromParcel(parcel: Parcel): Series {
            return Series(parcel)
        }

        override fun newArray(size: Int): Array<Series?> {
            return arrayOfNulls(size)
        }
    }
}


