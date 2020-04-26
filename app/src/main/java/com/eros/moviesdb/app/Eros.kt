package com.eros.moviesdb.app

import android.app.Application
import android.content.Context

class Eros : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
    companion object{
        lateinit var appContext: Context
    }
}