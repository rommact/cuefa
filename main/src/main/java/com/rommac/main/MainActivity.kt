package com.rommac.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.mvp.CommonView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), CommonView {

    private lateinit var navController: NavController
    @Inject
    lateinit var presenter: MainContract.Presenter
    @Inject
    lateinit var mainView: MainContract.View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        inject()
        mainView.onFinishInflate(presenter)

    }

    private fun inject() {
        MainComponent.create((application as AppWithFacade).getFacade(),this).inject(this)
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


    override fun setVisibleProgressMain(isVisible: Boolean) {
        mainView.setVisibleProgressMain(isVisible)
    }

}
