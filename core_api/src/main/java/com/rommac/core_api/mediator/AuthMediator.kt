package com.rommac.core_api.mediator

import android.app.Activity
import android.content.Context

interface AuthMediator {
    companion object{
        const val RC_SIGN_IN = 1
    }
    fun openAuthScreen(context: Activity)
}