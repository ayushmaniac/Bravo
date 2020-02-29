package com.realcrap.bravo.ui.referandearn

import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ReferViewModel (schedulerProvider: SchedulerProvider,
                      compositeDisposable: CompositeDisposable,
                      networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {

    }
}
