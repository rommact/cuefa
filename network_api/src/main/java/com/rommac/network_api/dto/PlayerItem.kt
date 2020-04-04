package com.rommac.network_api.dto

data class PlayerItem(
    val uid: String,
    val email: String,
    val isFriend: Boolean,
    val isOwner: Boolean
)