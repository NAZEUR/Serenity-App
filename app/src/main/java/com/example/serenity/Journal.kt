package com.example.serenity
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import android.os.Parcel

data class Journal(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Journal> {
        override fun createFromParcel(parcel: Parcel): Journal {
            return Journal(parcel)
        }

        override fun newArray(size: Int): Array<Journal?> {
            return arrayOfNulls(size)
        }
    }
}
