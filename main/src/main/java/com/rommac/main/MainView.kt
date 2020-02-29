package com.rommac.cuefa.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.rommac.core_api.mediator.AuthMediator
import com.rommac.cuefa.mvp.BaseView
import com.rommac.main.MainContract
import com.rommac.main.R
import java.util.*
import javax.inject.Inject

class MainView @Inject constructor(private val activity: AppCompatActivity,
               private val authMediator: AuthMediator) :BaseView(activity),  MainContract.View,
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navView: NavigationView
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var txtEmail: TextView
    private lateinit var layoutEmail: View
    private lateinit var progressBar: View
    private lateinit var progressMain: View
    lateinit var presenter: MainContract.Presenter
    lateinit var txtAuth: TextView

    fun onFinishInflate(presenter: MainContract.Presenter):MainView {
        this.presenter = presenter
        presenter.attachView(this,activity.lifecycle)

        navView = activity.findViewById(R.id.nav_view)
        progressMain = activity.findViewById(R.id.progressMain)
        bottomNavigation = activity.findViewById(R.id.bottomNavigation)
        toolbar = activity.findViewById(R.id.toolbar)
        drawerLayout = activity.findViewById(R.id.drawer_layout)

        initViews()

        presenter.viewIsReady()
        return this
    }

    private fun initViews() {
        val toggle = ActionBarDrawerToggle(
            activity,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        val headerView = navView.getHeaderView(0)
        txtAuth = headerView.findViewById<TextView>(R.id.txtAuth)
        val imageExit = headerView.findViewById<ImageView>(R.id.imageExit)
        layoutEmail = headerView.findViewById<View>(R.id.layoutEmail)

        txtEmail = headerView.findViewById<TextView>(R.id.txtEmail)
        progressBar = headerView.findViewById<TextView>(R.id.progressBar)
        progressBar.visibility = View.GONE
        navController = Navigation.findNavController(
            activity,
            R.id.nav_host_fragment
        )
        txtAuth.setOnClickListener {
            presenter.onAuthClicked()

        }

        imageExit.setOnClickListener {
            presenter.onSignoutClicked()

        }
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.players -> {
                    presenter.onBottomMenuItemClicked(MainContract.BOTTOM_NAV.PLAYERS)
                }
                R.id.sessions -> {
                    presenter.onBottomMenuItemClicked(MainContract.BOTTOM_NAV.SESSIONS)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun toAuth() {
        authMediator.openAuthScreen(activity)
    }

    override fun toProfile() {
        //TODO
//        navController.navigate(R.id.mainFragment);
    }

    override fun toPlayers() {
        navController.navigate(R.id.playersFragment);
    }

    override fun toSessions() {
        navController.navigate(R.id.sessionFragment);
    }


    override fun signout() {
        layoutEmail.visibility = View.GONE
        txtAuth.visibility = View.VISIBLE
    }

    override fun signin(email: String) {
        txtEmail.text = email
        layoutEmail.visibility = View.VISIBLE
        txtAuth.visibility = View.GONE
    }

    override fun showProgressBar() = activity.runOnUiThread {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() = activity.runOnUiThread {
        progressBar.visibility = View.INVISIBLE
    }

    override fun setVisibleProgressMain(isVisible: Boolean) {
        progressBar.visibility = if(isVisible) View.VISIBLE else View.INVISIBLE
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.players -> {
                presenter.onBottomMenuItemClicked(MainContract.BOTTOM_NAV.PLAYERS)
            }
            R.id.sessions -> {
                presenter.onBottomMenuItemClicked(MainContract.BOTTOM_NAV.SESSIONS)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}