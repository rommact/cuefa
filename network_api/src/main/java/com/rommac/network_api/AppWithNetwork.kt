package com.rommac.network_api

interface AppWithNetwork {
    fun getNetworkFacade(): NetworkFacade
}