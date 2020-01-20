package com.realcrap.bravo.di.module

import android.content.Context
import com.realcrap.bravo.di.application.BravoApplication
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ApplicationModule(private val application: BravoApplication) {


    @Provides
    fun provideContext() : Context = application

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

}