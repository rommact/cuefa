package com.rommac.cuefa.network

import com.rommac.cuefa.core.Player
import com.rommac.cuefa.network.dto.AuthBody
import com.rommac.cuefa.network.dto.AuthResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface Api {
    @POST("/auth.php")
    fun auth(@Body body: AuthBody): Observable<AuthResponse>
    @GET("/players.php")
    fun getPlayers(@Query("query") query:String, @Query("limit") limit:Int): Observable<List<Player>>
}