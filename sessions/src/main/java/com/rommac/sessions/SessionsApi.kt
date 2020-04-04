package com.rommac.sessions


import com.rommac.network_api.dto.GameSessionItem
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SessionsApi {
    @GET("sessions.php")
    fun getSessions(@Query("status") status: Int? = null,
                    @Query("my") my: Boolean = false): Single<List<GameSessionItem>>

    @POST("create.php")
    fun createSession(): Single<GameSessionItem>

    @POST("join.php")
    fun join(@Body body: GameSessionItem): Single<GameSessionItem>
}