package com.realcrap.bravo.ui.main

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.Event
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SharedViewModel  (schedulerProvider: SchedulerProvider,
                        compositeDisposable: CompositeDisposable,
                        networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {}

    val homeRedirection = MutableLiveData<Event<Boolean>>()


    fun onHomeRedirect() {
        homeRedirection.postValue(Event(true))
    }
}