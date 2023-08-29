package com.javokhirbekcoder.myapi.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.javokhirbekcoder.myapi.model.User

/*
Created by Javokhirbek on 17.08.2023 at 11:22
*/

class SharedPrefs(context: Context) {

    private var sharedPrefs: SharedPreferences
    private var editor: Editor

    private val LOGGED = "logged"
    private val LOGIN_DATA_USER = "loginUser"
    private val LOGIN_DATA_PASS = "loginPass"

    private val mainKeyAlias by lazy {
        val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
        MasterKeys.getOrCreate(keyGenParameterSpec)
    }

    private val sharedPrefsForPass by lazy {
        val sharedPrefsFile = "sharedPrefsForPass"

        EncryptedSharedPreferences.create(
            sharedPrefsFile,
            mainKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }


    init {
        sharedPrefs = context.getSharedPreferences("appConfig", Context.MODE_PRIVATE)
        editor = sharedPrefs.edit()
    }


    fun setLogged(logged: Boolean) {
        editor.putBoolean(LOGGED, logged)
        editor.apply()
    }

    fun getLogged(): Boolean {
        return sharedPrefs.getBoolean(LOGGED, false)
    }

    fun setLoginData(user: User) {
        editor.putString(LOGIN_DATA_USER, user.username)
        writePassToSharedPrefs(user.password)
        editor.apply()
    }

    fun getLoginData(): User? {
        try {
            if (getLogged()){
                val username = sharedPrefs.getString(LOGIN_DATA_USER, "")!!
                val password = readPassFromSharedPrefs()!!
                return User(username, password)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun writePassToSharedPrefs(value: String) {
        with (sharedPrefsForPass.edit()) {
            putString(LOGIN_DATA_PASS, value)
            apply()
        }
    }

    private fun readPassFromSharedPrefs(): String? {
        return sharedPrefsForPass.getString(LOGIN_DATA_PASS, "")
    }
}