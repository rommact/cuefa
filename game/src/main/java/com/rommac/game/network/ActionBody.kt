package com.rommac.game.network

import com.rommac.core_api.dto.ACTION_TYPE


data class ActionBody (
    val sessionId:String,
    val actionType: Int
)