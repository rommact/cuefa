package com.rommac.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rommac.core_api.dto.GameSession
import com.rommac.core_api.dto.GameSessionState
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.network_api.AppWithNetwork
import javax.inject.Inject




class GameActivity : AppCompatActivity() {

    companion object{
        private const val ARG_SESSION = "ARG_SESSION"
        private const val ARG_SESSION_STATE = "ARG_SESSION_STATE"
        fun putParams(
            intent: Intent,
            gameSession: GameSession,
            state: GameSessionState
        ){
             intent.putExtra(ARG_SESSION, gameSession)
             intent.putExtra(ARG_SESSION_STATE, state)
        }
    }
    private lateinit var gameView: GameViewImpl
    private lateinit var presenter: GameContract.Presenter
    @Inject
    lateinit var presenterFactory: PresenterFactory



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        inject()
        presenter = ViewModelProvider(this, presenterFactory).get(GamePresenterImpl::class.java)

        intent.let {
            presenter.gameSession = it.getParcelableExtra(ARG_SESSION)
            presenter.gameSessionState = it.getSerializableExtra(ARG_SESSION_STATE) as GameSessionState
        }
        gameView = GameViewImpl(this)
        gameView.onFinishInflate(presenter)
    }


    private fun inject(){
        GameComponent.create(
            (application as AppWithFacade).getFacade(),
            (application as AppWithNetwork).getNetworkFacade()
        ).inject(this)
    }
}
