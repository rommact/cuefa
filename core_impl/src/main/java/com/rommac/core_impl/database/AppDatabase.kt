package com.rommac.core_impl.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rommac.core_api.database.DataBaseContract
import com.rommac.core_api.database.dto.PlayerItem

@Database(entities = [PlayerItem::class], version = 1)
abstract class AppDatabase: RoomDatabase(), DataBaseContract {
}