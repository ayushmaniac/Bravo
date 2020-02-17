package com.realcrap.bravo.ui.offers.offersutil

import com.realcrap.bravo.ui.base.BaseItemViewModel
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class OfferViewModel @Inject constructor(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
) : BaseItemViewModel<Offer>(schedulerProvider,
        compositeDisposable, networkHelper) {



    override fun onCreate() {
    }
}