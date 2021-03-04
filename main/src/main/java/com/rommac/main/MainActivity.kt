package com.rommac.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.core_api.mediator.GameMediator
import com.rommac.core_api.mediator.MediatorsOwner
import com.rommac.core_api.mediator.MediatorsProvider
import com.rommac.main.databinding.ActivityMainBinding
import com.rommac.main.di.MainComponent
import com.rommac.mvp.CommonView
import com.rommac.network_api.AppWithNetwork
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), CommonView, MediatorsOwner {

    private lateinit var component: MainComponent

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var binding: ActivityMainBinding
    private lateinit var _navController: NavController

    @Inject
    lateinit var mainView: MainView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContentView(binding.root)
        setSupportActionBar(toolbar)
        _navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        mainView.onFinishInflate(viewModel, lifecycle)
    }

    private fun inject() {
        component = MainComponent.create(
            (application as AppWithFacade).getFacade(),
            (application as AppWithNetwork).getNetworkFacade(),
            this
        )
        component.inject(this)
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

    override fun getNavController(): NavController {
        return _navController;
    }

    override fun getMediatorsProvider(): MediatorsProvider{
        return component
    }

}
