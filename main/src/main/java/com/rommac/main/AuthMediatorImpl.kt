package com.rommac.main

import android.app.Activity
import android.content.Context
import com.firebase.ui.auth.AuthUI
import com.rommac.core_api.mediator.AuthMediator
import com.rommac.core_api.mediator.AuthMediator.Companion.RC_SIGN_IN
import java.util.*

class AuthMediatorImpl: AuthMediator {
    override fun openAuthScreen(context: Activity) {
        val providers = Arrays.asList(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()

        )

        context.startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )
    }
}