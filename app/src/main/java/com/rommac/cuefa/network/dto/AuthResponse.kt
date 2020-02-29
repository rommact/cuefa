package com.rommac.cuefa.network.dto

import com.google.gson.annotations.Expose

data class AuthResponse (
    @Expose
    val status:Boolean,
    @Expose
    val balance:Int
)