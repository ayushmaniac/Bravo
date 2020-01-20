package com.realcrap.bravo.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(
    protected  val compositeDisposable: CompositeDisposable
) : ViewModel(){




    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    abstract fun onCreate()
}