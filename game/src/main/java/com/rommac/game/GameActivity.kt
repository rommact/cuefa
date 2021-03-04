package com.rommac.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.GameSessionState
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.game.databinding.ActivityGameBinding
import com.rommac.game.di.GameComponent
import com.rommac.network_api.AppWithNetwork
import javax.inject.Inject




class GameActivity : AppCompatActivity() {

    companion object{
        private const val ARG_SESSION = "ARG_SESSION"
        private const val ARG_SESSION_STATE = "ARG_SESSION_STATE"
        fun putParams(
            bundle: Bundle,
            gameSession: GameSession,
            state: GameSessionState
        ){
            bundle.putParcelable(ARG_SESSION, gameSession)
            bundle.putSerializable(ARG_SESSION_STATE, state)
        }
    }
    @Inject
    lateinit var gameView: GameViewImpl
    @Inject
    lateinit var viewModel: GameViewModelImpl
    @Inject
    lateinit var binding: ActivityGameBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContentView(binding.root)
        intent.let {
            viewModel.gameSession = it.getParcelableExtra(ARG_SESSION)
            viewModel.gameSessionState = it.getSerializableExtra(ARG_SESSION_STATE) as GameSessionState
        }
        gameView.onFinishInflate(viewModel, lifecycle)

    }


    private fun inject(){

        GameComponent.create(this,
            (application as AppWithFacade).getFacade(),
            (application as AppWithNetwork).getNetworkFacade()
        ).inject(this)
    }
}
