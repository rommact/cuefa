package com.rommac.core_api.storage

interface StorageProvider {
    fun getPlayerStorage(): PlayersStorage
}