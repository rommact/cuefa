package com.rommac.cuefa.db.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null,
    val uid: String,
    val email: String,
    val isFriend: Boolean
)