package com.realcrap.bravo.di.application

import android.app.Application
import com.realcrap.bravo.di.component.ApplicationComponent
import com.realcrap.bravo.di.component.DaggerApplicationComponent
import com.realcrap.bravo.di.module.ApplicationModule

class BravoApplication : Application(){

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        getDependencies()
    }

    private fun getDependencies() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }

}