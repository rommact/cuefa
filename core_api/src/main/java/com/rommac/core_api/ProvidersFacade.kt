package com.rommac.core_api

import com.rommac.core_api.database.DatabaseProvider
import com.rommac.core_api.interactor.GameInteractor
import com.rommac.core_api.interactor.InteractorProvider
import com.rommac.core_api.mediator.AppProvider
import com.rommac.core_api.network.NetworkProvider
import com.rommac.core_api.storage.PlayersStorage
import com.rommac.core_api.storage.StorageProvider

interface ProvidersFacade:  DatabaseProvider, AppProvider, InteractorProvider, StorageProvider, NetworkProvider {
}