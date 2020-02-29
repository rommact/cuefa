package com.rommac.core_api.dto

import android.os.Parcel
import android.os.Parcelable

data class GameSession (
    val id: Long = -1,
    val players:List<Player> = ArrayList(),
    var timeStart:Long = 0,
    var status: STATUS = STATUS.NEW

):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.createTypedArrayList(Player)!!,
        parcel.readLong(),
        STATUS.values()[parcel.readInt()]
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeTypedList(players)
        parcel.writeLong(timeStart)
        parcel.writeInt(status.ordinal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GameSession> {
        override fun createFromParcel(parcel: Parcel): GameSession {
            return GameSession(parcel)
        }

        override fun newArray(size: Int): Array<GameSession?> {
            return arrayOfNulls(size)
        }
    }

}