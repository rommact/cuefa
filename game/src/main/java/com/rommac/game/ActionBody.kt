package com.rommac.game

import com.rommac.core_api.dto.ACTION_TYPE


data class ActionBody (
    val sessionId:String,
    val actionType: ACTION_TYPE
)