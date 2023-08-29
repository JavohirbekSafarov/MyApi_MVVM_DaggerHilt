package com.javokhirbekcoder.myapi

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.javokhirbekcoder.myapi.utils.SharedPrefs
import dagger.hilt.android.HiltAndroidApp


/*
Created by Javokhirbek on 17.08.2023 at 11:35
*/
@HiltAndroidApp
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}