package com.rommac.cuefa.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rommac.cuefa.App
import com.rommac.cuefa.R
import com.rommac.cuefa.core.GameSession
import com.rommac.cuefa.core.Player
import com.rommac.cuefa.core.STATUS
import com.rommac.cuefa.db.dto.PlayerItem
import com.rommac.cuefa.di.DaggerMainActivityComponent
import com.rommac.cuefa.di.MainActivityComponent
import com.rommac.cuefa.ui.game.ARG_SESSION
import com.rommac.cuefa.ui.game.GameActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainContract.CommonView {

    companion object{
         var mainActivityComponent:MainActivityComponent? = null
    }

    private lateinit var navController: NavController
    @Inject
    lateinit var mainPresenterFactory: MainPresenterFactory
    lateinit var presenter: MainContract.Presenter
    lateinit var mainView: MainContract.View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        inject()

        presenter = ViewModelProvider(this, mainPresenterFactory).get(MainPresenter::class.java)
        mainView = MainView(this)
            .onFinishInflate(presenter)

    }

    private fun inject(){
         mainActivityComponent = DaggerMainActivityComponent
            .builder()
            .appComponent(App.appComponent)
            .build()
        mainActivityComponent!!.inject(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.onActivityResult(requestCode, resultCode, intent)
    }


    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityComponent = null
    }

    fun toGame(){
        val bundle = Bundle()
        val test = listOf(Player("1", "test", true, false), Player("1", "test", true, true))
        bundle.putParcelable(ARG_SESSION,GameSession(1, test, 0, STATUS.NEW))
        navController.navigate(R.id.gameActivity, bundle)
    }

    override fun setVisibleProgressMain(isVisible: Boolean) {
        mainView.setVisibleProgressMain(isVisible)
    }

}
