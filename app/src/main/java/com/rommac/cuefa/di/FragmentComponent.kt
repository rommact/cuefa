package com.rommac.cuefa.di

import com.rommac.cuefa.di.scope.FragmentScope
import com.rommac.cuefa.ui.players.PlayersFragment
import com.rommac.cuefa.ui.session.SessionFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [PlayersFragmentModule::class])
interface FragmentComponent {
    fun inject(playersFragment: PlayersFragment)
    fun inject(sessionFragment: SessionFragment)
}