package com.rommac.core_api.mediator

import com.rommac.core_api.ProvidersFacade

interface AppWithFacade {

    fun getFacade(): ProvidersFacade
}