package com.rommac.core_api.database

interface DatabaseProvider {
    fun databaseContract(): DataBaseContract
    fun playerDao(): PlayerDao
}