package com.rommac.auth.network

import com.google.gson.annotations.SerializedName


data class AuthBody(

    val email: String,

    val name: String,

    val token: String,

    @SerializedName("device_id")
    val deviceId: String,

    val uid: String,

    val adid: String
)