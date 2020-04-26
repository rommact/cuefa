package com.rommac.network_api.dto

data class GameSessionItem (
    val id: Long = -1,
    val players:List<PlayerItem> = ArrayList(),
    var timeStart:Long = 0,
    var status: Int = 0

)
