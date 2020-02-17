package com.realcrap.bravo.ui.forgotpassword

import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ForgotPasswordViewModel(compositeDisposable: CompositeDisposable,
                              schedulerProvider: SchedulerProvider,
                              networkHelper: NetworkHelper) : BaseViewModel(schedulerProvider,
                                compositeDisposable,networkHelper){
    override fun onCreate() {
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}