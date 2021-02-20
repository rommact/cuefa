package com.rommac.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.main.di.MainComponent
import com.rommac.mvp.CommonView
import com.rommac.network_api.AppWithNetwork
import com.rommac.sessions.SessionsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), CommonView {

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var mainView: MainView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        inject()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        mainView.onFinishInflate(viewModel, lifecycle)

    }

    private fun inject() {
        MainComponent.create(
                (application as AppWithFacade).getFacade(),
                (application as AppWithNetwork).getNetworkFacade(),
                this
            ).inject(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        viewModel.onActivityResult(requestCode, resultCode, intent)
    }


    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun setVisibleProgressMain(isVisible: Boolean) {
        mainView.setVisibleProgressMain(isVisible)
    }

}
