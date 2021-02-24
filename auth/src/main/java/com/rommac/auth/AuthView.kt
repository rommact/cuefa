package com.rommac.auth

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.rommac.auth.databinding.ActivityAuthBinding
import com.rommac.core_api.EventObserver
import com.rommac.core_api.mediator.AuthMediator
import com.rommac.mvp.BaseView

class AuthView(
    private val activity: AppCompatActivity,
    private val binding: ActivityAuthBinding,
    private val authMediator: AuthMediator, lifecycleOwner: LifecycleOwner
) :
    BaseView<AuthViewModel>(binding.root, lifecycleOwner) {

    override fun initViews() {
        binding.buttonAuth.setOnClickListener {
            viewModel.onAuthClicked()
        }
    }

    override fun bindViewModel() {
        viewModel.inProgress.observe(lifecycleOwner, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.INVISIBLE
            binding.txtButtonAuth.visibility = if (!it) View.VISIBLE else View.INVISIBLE
        })

        viewModel.signIn.observe(lifecycleOwner, EventObserver {
            toAuth()
        })
        viewModel.signInSuccess.observe(lifecycleOwner, EventObserver {
            activity.finish()
        })
    }

    private fun toAuth() {
        authMediator.openAuthScreen(activity)
    }
}