package com.rommac.sessions.di

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rommac.core_api.mediator.GameMediator
import com.rommac.core_api.scope.ActivityScope
import com.rommac.core_api.scope.FragmentScope
import com.rommac.mvp.CommonView
import com.rommac.sessions.*
import com.rommac.sessions.databinding.FragmentSessionBinding
import com.rommac.sessions.network.SessionsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SessionModule {
    @Provides
    fun provideSessionsInteractor(api: SessionsApi): SessionInteractor {
        return SessionInteractorImpl(api)
    }

    @Provides
    fun provideSessionView( binding: FragmentSessionBinding,
       lifecycleOwner: LifecycleOwner, navController: NavController,
        gameMediator: GameMediator
    ): SessionsViewImpl {
        return SessionsViewImpl(
            binding,
            lifecycleOwner,
            navController,
            gameMediator
        )
    }

    @Provides
    fun getSessionApi(retrofit: Retrofit): SessionsApi {
        return retrofit.create(SessionsApi::class.java)
    }


    @Provides
    fun provideSessionsViewModel(viewModelStoreOwner: ViewModelStoreOwner,
                             viewModelFactory: ViewModelFactory
    ): SessionsViewModel {
        return ViewModelProvider(viewModelStoreOwner, viewModelFactory).get(SessionsViewModel::class.java)
    }


    @Provides
    fun provideViewModelStoreOwner(fragment: Fragment
    ): ViewModelStoreOwner {
        return fragment
    }

    @FragmentScope
    @Provides
    fun provideFragmentSessionBinding(fragment: Fragment
    ): FragmentSessionBinding {
        return FragmentSessionBinding.inflate(fragment.layoutInflater)
    }

    @Provides
    fun provideNavController(commonView: CommonView):NavController{
        return commonView.getNavController()
    }

}