package com.rommac.game

import com.rommac.network_api.dto.GameSessionItem
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GameApi {
    @POST("exit.php")
    fun exit(@Query("sessionId") sessionId: String): Single<Void>

    @POST("action.php")
    fun action(@Body body: ActionBody): Single<Void>

    @GET("state.php")
    fun getState(@Query("sessionId") sessionId: String): Single<GameSessionItem>

}