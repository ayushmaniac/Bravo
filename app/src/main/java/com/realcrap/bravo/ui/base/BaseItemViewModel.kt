package com.realcrap.bravo.ui.base

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseItemViewModel<T: Any>(schedulerProvider: SchedulerProvider, compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper) :
        BaseViewModel(schedulerProvider,compositeDisposable, networkHelper)
{

    val data: MutableLiveData<T> = MutableLiveData()

    fun updateData(data : T){

        this.data.postValue(data)
    }

    fun onManualCleared() = onCleared()

}