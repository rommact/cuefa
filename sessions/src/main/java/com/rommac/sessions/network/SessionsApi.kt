package com.rommac.sessions.network


import com.rommac.core_api.dto.GameSessionState
import com.rommac.network_api.dto.GameSessionItem
import com.rommac.network_api.dto.GameSessionStateItem
import com.rommac.sessions.network.dto.JoinResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SessionsApi {
    @GET("session/sessions")
    fun getSessions(@Query("status") status: Int? = null,
                    @Query("my") my: Boolean = false): Single<List<GameSessionItem>>
    @GET("session/sessions/active")
    fun getActiveSessions(): Single<List<GameSessionItem>>
    @POST("session/create")
    fun createSession(): Single<GameSessionItem>

    @POST("session/join")
    fun join(@Body body: GameSessionItem): Single<JoinResponse>

    @POST("session/state")
    fun state(): Single<GameSessionStateItem>
}