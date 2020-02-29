package com.rommac.cuefa.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthBody(
    @Expose
    val email:String,
    @Expose
    val name:String,
    @Expose
    val token:String,
    @SerializedName("device_id")
    @Expose
    val deviceId:String,
    @Expose
    val uid:String,
    @Expose
    val adid:String
)