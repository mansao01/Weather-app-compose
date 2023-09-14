package com.example.weatherappcompose

import android.app.Application
import com.example.weatherappcompose.data.AppContainer
import com.example.weatherappcompose.data.DefaultAppContainer

class WeatherApplication:Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}