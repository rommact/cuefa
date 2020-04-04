package com.rommac.core_api.dto

data class AuthData (
    val status:Boolean,
    val balance:Int,
    val email:String,
    val uid:String
)