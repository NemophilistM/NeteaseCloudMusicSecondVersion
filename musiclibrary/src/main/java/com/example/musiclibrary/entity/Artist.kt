package com.example.musiclibrary.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.TypeConverters
import com.example.musiclibrary.util.ArtistConverter
import java.io.Serializable

@TypeConverters(ArtistConverter::class)
data class Artist(
    val id: Long = 0,
    val name: String = "noArtistName",
    val alias: String = "noAlias",
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(alias)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Artist> {
        override fun createFromParcel(parcel: Parcel): Artist {
            return Artist(parcel)
        }

        override fun newArray(size: Int): Array<Artist?> {
            return arrayOfNulls(size)
        }
    }
}
