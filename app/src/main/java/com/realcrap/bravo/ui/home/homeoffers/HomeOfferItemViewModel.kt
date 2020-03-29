package com.realcrap.bravo.ui.home.homeoffers

import com.realcrap.bravo.ui.base.BaseItemViewModel
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeOfferItemViewModel @Inject constructor(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
) : BaseItemViewModel<HomeOffers>(schedulerProvider,
        compositeDisposable, networkHelper) {
    override fun onCreate() {
    }


}