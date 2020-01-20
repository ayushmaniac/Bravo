package com.realcrap.bravo.di.component

import android.content.Context
import com.realcrap.bravo.di.application.BravoApplication
import com.realcrap.bravo.di.module.ApplicationModule
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: BravoApplication)

    fun getContext() : Context

    fun getCompositeDisposable() : CompositeDisposable


}