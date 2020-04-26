package com.rommac.core_api.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    val uid: String,
    val email: String,
    val isFriend: Boolean,
    val isOwner: Boolean
)