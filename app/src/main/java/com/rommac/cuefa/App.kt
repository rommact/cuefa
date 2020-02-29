package com.rommac.cuefa

import android.app.Application
import androidx.room.Room
import com.rommac.cuefa.di.*


class App: Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }


    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi(){
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()

    }





}