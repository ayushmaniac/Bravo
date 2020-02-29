package com.realcrap.bravo.ui.main

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.Event
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        schedulerProvider: SchedulerProvider
) : BaseViewModel(

        schedulerProvider, compositeDisposable, networkHelper

){

    val profileNavigation = MutableLiveData<Event<Boolean>>()
    val homeNavigation = MutableLiveData<Event<Boolean>>()
    val offerNavigation = MutableLiveData<Event<Boolean>>()
    val referNavigation = MutableLiveData<Event<Boolean>>()

    override fun onCreate() {

        homeNavigation.postValue(Event(true))

    }

    fun onProfileSelected() {
        profileNavigation.postValue(Event(true))
    }
    fun onHomeSelected() {
        homeNavigation.postValue(Event(true))
    }

    fun onOfferSelected() {
        offerNavigation.postValue(Event(true))
    }

    fun onReferSelected() {
        referNavigation.postValue(Event(true))
    }

}