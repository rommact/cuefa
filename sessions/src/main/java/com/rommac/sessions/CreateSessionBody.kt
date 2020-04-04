package com.rommac.core_api.network.dto.session

import com.rommac.network_api.dto.GameSessionItem

data class CreateSessionBody(
    var session: com.rommac.network_api.dto.GameSessionItem
)