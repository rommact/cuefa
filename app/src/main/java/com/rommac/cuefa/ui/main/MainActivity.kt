package com.rommac.cuefa.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.rommac.cuefa.App
import com.rommac.cuefa.R
import com.rommac.cuefa.di.DaggerMainActivityComponent
import com.rommac.cuefa.di.MainActivityComponent
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    companion object{
         var mainActivityComponent:MainActivityComponent? = null
    }

    @Inject
    lateinit var mainPresenterFactory: MainPresenterFactory
    lateinit var presenter: MainContract.Presenter
    lateinit var mainView: MainContract.View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
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

}
