package com.example.musiclibrary.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.TypeConverters


@TypeConverters(Album::class)
data class Album(
    val id: Long = 0,
    val name: String? = "noAlbumName",
    val picUrl: String? = "noPic"
) :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(picUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Album> {
        override fun createFromParcel(parcel: Parcel): Album {
            return Album(parcel)
        }

        override fun newArray(size: Int): Array<Album?> {
            return arrayOfNulls(size)
        }
    }
}
