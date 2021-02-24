package com.rommac.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.rommac.auth.databinding.ActivityAuthBinding
import com.rommac.auth.di.AuthComponent
import com.rommac.core_api.mediator.AppWithFacade
import com.rommac.network_api.AppWithNetwork
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {

    @Inject
    lateinit var authView: AuthView
    @Inject
    lateinit var binding:ActivityAuthBinding

    @Inject
    lateinit var authViewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        setContentView(binding.root)
        authView.onFinishInflate(authViewModel, lifecycle)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        authViewModel.onActivityResult(requestCode, resultCode, intent)
    }

    private fun inject() {
        AuthComponent.create(
            (application as AppWithFacade).getFacade(),
            (application as AppWithNetwork).getNetworkFacade(),
            this
        ).inject(this)
    }

    override fun onBackPressed() {

    }

}