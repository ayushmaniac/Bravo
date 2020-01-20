package com.realcrap.bravo.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(){

    @Inject
    lateinit var viewModel : VM

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    protected open fun setupObservers(){


    }

    @LayoutRes
    protected abstract fun provideLayoutId() : Int
}