package com.rommac.main.di

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.rommac.main.ViewModelFactory
import com.rommac.core_api.scope.ActivityScope
import com.rommac.main.MainView
import com.rommac.main.MainViewModel
import com.rommac.main.databinding.ActivityMainBinding
import dagger.Module
import dagger.Provides

@Module
abstract class MainModule {
    @Module
    object MainModuleProviders {

        @Provides
        fun provideMainView(
            activity: AppCompatActivity,activityMainBinding: ActivityMainBinding,
            rootView: View, lifecycleOwner: LifecycleOwner
        ): MainView {
            return MainView(
                activity,
                activityMainBinding,
                rootView,
                lifecycleOwner
            )
        }

        @JvmStatic
        @Provides
        fun provideAuthViewModel(viewModelStoreOwner: ViewModelStoreOwner,
                                 viewModelFactory: ViewModelFactory
        ): MainViewModel {
            return ViewModelProvider(viewModelStoreOwner, viewModelFactory).get(MainViewModel::class.java)
        }
        @JvmStatic
        @Provides
        fun provideViewModelStoreOwner(activity: AppCompatActivity
        ): ViewModelStoreOwner {
            return activity
        }
        @ActivityScope
        @JvmStatic
        @Provides
        fun provideActivityMainBinding(activity: AppCompatActivity
        ): ActivityMainBinding {
            return ActivityMainBinding.inflate(activity.layoutInflater)
        }

    }



}