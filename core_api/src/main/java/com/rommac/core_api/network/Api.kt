package com.rommac.core_api.network


import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.GameSessionItem
import com.rommac.core_api.dto.Player
import com.rommac.core_api.network.dto.AuthBody
import com.rommac.core_api.network.dto.AuthResponse
import com.rommac.core_api.network.dto.session.ActionBody
import io.reactivex.Observable
import retrofit2.http.*


interface Api {
    @POST("auth.php")
    fun auth(@Body body: AuthBody): Observable<AuthResponse>

    @GET("players.php")
    fun getPlayers(@Query("query") query: String,
                   @Query("limit") limit: Int): Observable<List<Player>>

    @GET("sessions.php")
    fun getSessions(@Query("status") status: Int? = null,
                    @Query("my") my: Boolean = false): Observable<List<GameSessionItem>>

    @POST("create.php")
    fun createSession(): Observable<GameSessionItem>

    @POST("join.php")
    fun join(@Body body: GameSession): Observable<GameSession>

    @POST("exit.php")
    fun exit(@Query("sessionId") sessionId: String): Observable<Void>

    @POST("action.php")
    fun action(@Body body: ActionBody): Observable<Void>

    @GET("state.php")
    fun getState(@Query("sessionId") sessionId: String): Observable<GameSession>
}