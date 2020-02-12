package com.realcrap.bravo.ui.splash

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.Event
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {


    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val launchLogin: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {
        if (userRepository.getCurrentUser() != null)
            launchMain.postValue(Event(emptyMap()))
        else
            launchLogin.postValue(Event(emptyMap()))

    }
}