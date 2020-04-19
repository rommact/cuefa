package com.rommac.core_api

import com.rommac.core_api.database.DatabaseProvider
import com.rommac.core_api.mediator.AppProvider
import com.rommac.core_api.mediator.MediatorsProvider
import com.rommac.core_api.storage.StorageProvider

interface ProvidersFacade:  DatabaseProvider, AppProvider, StorageProvider, MediatorsProvider {
}