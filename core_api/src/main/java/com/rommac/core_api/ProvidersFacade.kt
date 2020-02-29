package com.rommac.core_api

import com.rommac.core_api.database.DatabaseProvider
import com.rommac.core_api.mediator.AppProvider

interface ProvidersFacade:  DatabaseProvider, AppProvider {
}