package com.rommac.main

import android.app.Activity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.rommac.core_api.EventObserver
import com.rommac.core_api.mediator.AuthMediator
import com.rommac.mvp.BaseView
import javax.inject.Inject

class MainView @Inject constructor(
    private val activity: Activity,
    private val authMediator: AuthMediator,
    rootView: View,
    lifecycleOwner: LifecycleOwner
) : BaseView<MainViewModel>(rootView, lifecycleOwner),
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
    lateinit var txtAuth: TextView



    override fun initViews() {
        navView = rootView.findViewById(R.id.nav_view)
        progressMain = rootView.findViewById(R.id.progress_main)
        bottomNavigation = rootView.findViewById(R.id.bottom_navigation)
        toolbar = rootView.findViewById(R.id.toolbar)
        drawerLayout = rootView.findViewById(R.id.drawer_layout)

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
        progressBar = headerView.findViewById(R.id.progressBar)
        progressBar.visibility = View.GONE
        navController = Navigation.findNavController(
            activity,
            R.id.nav_host_fragment
        )
        txtAuth.setOnClickListener {
            viewModel.onAuthClicked()

        }

        imageExit.setOnClickListener {
            viewModel.onSignoutClicked()

        }
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.players -> {
                    viewModel.onBottomMenuItemClicked(MainContract.BOTTOM_NAV.PLAYERS)
                }
                R.id.sessions -> {
                    viewModel.onBottomMenuItemClicked(MainContract.BOTTOM_NAV.SESSIONS)
                }
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun bindViewModel() {
        viewModel.toSession.observe(lifecycleOwner, EventObserver {
            toSessions()
        })
        viewModel.toPlayers.observe(lifecycleOwner, EventObserver {
            toPlayers()
        })

        viewModel.signOut.observe(lifecycleOwner, EventObserver {
            signout()
        })

        viewModel.signIn.observe(lifecycleOwner, EventObserver {
            signin(it)
        })

        viewModel.inProgress.observe(lifecycleOwner, Observer {
            if (it) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        })
    }

    fun toAuth() {

        authMediator.openAuthScreen(activity)
    }

    fun toProfile() {
        //TODO
//        navController.navigate(R.id.mainFragment);
    }

    fun toPlayers() {
        navController.navigate(R.id.playersFragment);
    }

    fun toSessions() {
        navController.navigate(R.id.sessionFragment);
    }


    fun signout() {
        layoutEmail.visibility = View.GONE
        txtAuth.visibility = View.VISIBLE
    }

    fun signin(email: String) {
        txtEmail.text = email
        layoutEmail.visibility = View.VISIBLE
        txtAuth.visibility = View.GONE
    }

    fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    fun setVisibleProgressMain(isVisible: Boolean) {
        progressBar.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.players -> {
                viewModel.onBottomMenuItemClicked(MainContract.BOTTOM_NAV.PLAYERS)
            }
            R.id.sessions -> {
                viewModel.onBottomMenuItemClicked(MainContract.BOTTOM_NAV.SESSIONS)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}