package com.rommac.cuefa.di

import com.rommac.cuefa.di.scope.ActivityScope
import com.rommac.cuefa.repository.PlayersRepository
import com.rommac.cuefa.ui.game.GameActivity
import com.rommac.cuefa.ui.main.MainActivity
import com.rommac.cuefa.ui.main.MainContract
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class],modules = [MainActivityModule::class])
interface MainActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(gameActivity: GameActivity)
}