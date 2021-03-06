package com.rommac.core_api.database

import androidx.room.*
import com.rommac.core_api.database.dto.PlayerItem
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface PlayerDao {
    @Insert
    fun add(playerItem: PlayerItem)

    @Delete
    fun remove(purchase: PlayerItem)

    @Update
    fun update(purchase: PlayerItem)

    @Query("SELECT * FROM PlayerItem")
    fun getAll(): Flowable<List<PlayerItem>>

    @Query("SELECT * FROM PlayerItem WHERE email like '%' || :query || '%' limit :limit")
    fun get(query:String, limit:Int): Observable<List<PlayerItem>>
}