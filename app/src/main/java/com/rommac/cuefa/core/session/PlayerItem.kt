package com.rommac.cuefa.core.session

import android.os.Parcel
import android.os.Parcelable


data class PlayerItem(
    var uid: String,
    var email: String,
    var isFriend: Boolean,
    var isOwner: Boolean
)


