package com.rommac.cuefa

import android.app.Application
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.cuefa.di.AppComponent
import com.rommac.cuefa.di.FacadeComponent
import com.rommac.network.NetworkProviderFactory
import com.rommac.network_api.AppWithNetwork
import com.rommac.network_api.NetworkFacade
import com.rommac.network_api.NetworkProvider


class App : Application(), AppWithFacade, AppWithNetwork {

    companion object {

        private var facadeComponent: FacadeComponent? = null
        private var netWorkComponent: NetworkFacade? = null
    }

    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(this).also {
            facadeComponent = it
        }
    }

    override fun getNetworkFacade(): NetworkFacade {
        return netWorkComponent ?: NetworkProviderFactory.createNetworkBuilder(AppComponent.create(this)).also {
            netWorkComponent = it
        }
    }

    override fun onCreate() {
        super.onCreate()
        (getFacade() as FacadeComponent).inject(this)
    }
}