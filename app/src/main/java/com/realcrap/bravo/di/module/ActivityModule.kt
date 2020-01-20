package com.realcrap.bravo.di.module

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.mainactivity.MainViewModel
import com.realcrap.bravo.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule (private val activity: BaseActivity<*>){


    @Provides
    fun provideContext(
    ) : Context = activity


    @Provides
    fun provideMainViewModel(
            compositeDisposable: CompositeDisposable

    ) : ViewModel = ViewModelProviders.of(activity, ViewModelProviderFactory(MainViewModel::class){
        MainViewModel(compositeDisposable)
    }).get(MainViewModel::class.java)
}