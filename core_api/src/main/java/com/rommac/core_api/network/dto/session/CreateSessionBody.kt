package com.rommac.core_api.network.dto.session

import com.rommac.core_api.dto.GameSessionItem

data class CreateSessionBody(
    var session: GameSessionItem
)