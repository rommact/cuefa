package com.rommac.game.network

import com.rommac.core_api.dto.GameSessionState
import com.rommac.network_api.dto.GameSessionItem
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GameApi {
    @POST("session/exit")
    fun exit(@Query("sessionId") sessionId: String): Completable

    @POST("session/action")
    fun action(@Body body: ActionBody): Single<GameSessionState>

    @GET("state")
    fun getState(@Query("sessionId") sessionId: String): Single<GameSessionItem>

}