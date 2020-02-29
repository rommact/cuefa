package com.rommac.cuefa.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rommac.cuefa.db.dto.PlayerDao
import com.rommac.cuefa.db.dto.PlayerItem

@Database(entities = [PlayerItem::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun playerDao(): PlayerDao
}