package com.rommac.game.di

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavController
import com.rommac.core_api.scope.ActivityScope
import com.rommac.core_api.scope.FragmentScope
import com.rommac.game.*
import com.rommac.game.network.GameApi
import com.rommac.game.databinding.ActivityGameBinding
import com.rommac.mvp.CommonView
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class GameModule {
    @Provides
    fun provideGameInteractor(api: GameApi): GameInteractor {
        return GameInteractorImpl(api)
    }

    @Provides
    fun provideGameView(
        binding: ActivityGameBinding, lifecycleOwner: LifecycleOwner
    ): GameViewImpl {
        return GameViewImpl(
            binding,
            lifecycleOwner
        )
    }

    @Provides
    fun getGamesApi(retrofit: Retrofit): GameApi {
        return retrofit.create(GameApi::class.java)
    }

    @Provides
    fun provideViewModelStoreOwner(activity: AppCompatActivity
    ): ViewModelStoreOwner {
        return activity
    }

    @Provides
    fun provideGameViewModel(viewModelStoreOwner: ViewModelStoreOwner,
                                 viewModelFactory: ViewModelFactory
    ): GameViewModelImpl {
        return ViewModelProvider(viewModelStoreOwner, viewModelFactory).get(GameViewModelImpl::class.java)
    }

    @ActivityScope
    @Provides
    fun provideActivityGameBinding(activity: AppCompatActivity
    ): ActivityGameBinding {
        return ActivityGameBinding.inflate(activity.layoutInflater)
    }

    @Provides
    fun provideNavController(commonView: CommonView): NavController {
        return commonView.getNavController()
    }
}