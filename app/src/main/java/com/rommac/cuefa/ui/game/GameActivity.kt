package com.rommac.cuefa.ui.game

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rommac.cuefa.App
import com.rommac.cuefa.R
import com.rommac.cuefa.core.GameSession
import com.rommac.cuefa.di.DaggerMainActivityComponent
import com.rommac.cuefa.ui.main.MainActivity
import javax.inject.Inject


public const val ARG_SESSION = "ARG_SESSION"

class GameActivity : AppCompatActivity() {

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
        }
        gameView = GameViewImpl(this)
        gameView.onFinishInflate(presenter)
    }


    private fun inject(){
        MainActivity.mainActivityComponent = DaggerMainActivityComponent
            .builder()
            .appComponent(App.appComponent)
            .build()
        MainActivity.mainActivityComponent!!.inject(this)
    }
}
