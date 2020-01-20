package com.realcrap.bravo.ui.base

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

abstract class BaseItemViewModel<T: Any>(compositeDisposable: CompositeDisposable) : BaseViewModel(compositeDisposable)
{

    val data: MutableLiveData<T> = MutableLiveData()

    fun updateData(data : T){

        this.data.postValue(data)
    }

    fun onManualCleared() = onCleared()

}