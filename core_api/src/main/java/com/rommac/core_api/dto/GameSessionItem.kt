package com.rommac.core_api.dto

import android.os.Parcel
import android.os.Parcelable

data class GameSessionItem (
    val id: Long = -1,
    val players:List<PlayerItem> = ArrayList(),
    var timeStart:Long = 0,
    var status: Int = 0

)