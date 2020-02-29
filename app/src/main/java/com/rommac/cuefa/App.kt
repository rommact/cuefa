package com.rommac.cuefa

import android.app.Application
import com.rommac.core_api.ProvidersFacade
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.cuefa.di.FacadeComponent


class App : Application(), AppWithFacade {

    companion object {

        private var facadeComponent: FacadeComponent? = null
    }

    override fun getFacade(): ProvidersFacade {
        return facadeComponent ?: FacadeComponent.init(this).also {
            facadeComponent = it
        }
    }

    override fun onCreate() {
        super.onCreate()
        (getFacade() as FacadeComponent).inject(this)
    }
}