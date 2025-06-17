package com.example.list

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import com.example.list.repositories.MainRepository

class AppList : Application() {
    override fun onCreate() {
        super.onCreate()
        MainRepository.getInstance().loadData()
    }
    init {
        instance = this
    }
    companion object {
        private var instance: AppList? = null

        val context
            get()=applicationContext()
        private fun applicationContext() : Context {
            return instance!!.applicationContext

        }
    }
}