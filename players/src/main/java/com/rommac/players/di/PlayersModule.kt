package com.rommac.players.di

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.rommac.core_api.scope.ActivityScope
import com.rommac.core_api.scope.FragmentScope
import com.rommac.players.*
import com.rommac.players.databinding.PlayersFragmentBinding
import com.rommac.players.network.PlayersApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class PlayersModule {
    @Provides
    fun providePlayerInteractor(api: PlayersApi): PlayerInteractor {
        return PlayerInteractorImpl(api)
    }

    @Provides
    fun providePlayersView( binding: PlayersFragmentBinding,
       lifecycleOwner: LifecycleOwner
    ): PlayersViewImpl {
        return PlayersViewImpl(
            binding,
            lifecycleOwner
        )
    }

    @Provides
    fun getPlayersApi(retrofit: Retrofit): PlayersApi {
        return retrofit.create(PlayersApi::class.java)
    }


    @Provides
    fun provideAuthViewModel(
        viewModelStoreOwner: ViewModelStoreOwner,
        viewModelFactory: ViewModelFactory
    ): PlayersViewModel {
        return ViewModelProvider(
            viewModelStoreOwner,
            viewModelFactory
        ).get(PlayersViewModel::class.java)
    }

    @Provides
    fun provideViewModelStoreOwner(
        fragment: Fragment
    ): ViewModelStoreOwner {
        return fragment
    }

    @FragmentScope
    @Provides
    fun providePlayersFragmentBinding(
        fragment: Fragment
    ): PlayersFragmentBinding {
        return PlayersFragmentBinding.inflate(fragment.layoutInflater)
    }

}