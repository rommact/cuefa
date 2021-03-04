package com.rommac.mvp

import androidx.navigation.NavController

interface CommonView{
    fun setVisibleProgressMain(isVisible:Boolean)
    //todo refact
    fun getNavController(): NavController
}