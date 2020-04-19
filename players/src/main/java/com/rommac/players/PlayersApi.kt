package com.rommac.players

import com.rommac.network_api.dto.PlayerItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PlayersApi {
    @GET("players")
    fun getPlayers(@Query("query") query: String,
                   @Query("limit") limit: Int): Single<List<PlayerItem>>
}