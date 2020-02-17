package com.realcrap.bravo.ui.offers

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.ui.offers.offersutil.Offer
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class OffersViewModel(
        compositeDisposable: CompositeDisposable,
        schedulerProvider: SchedulerProvider,
        networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val offersData = MutableLiveData<List<Offer>>()

    override fun onCreate() {
        offersData.postValue(listOf(
                Offer("PayTM", "Get 100 OFF on using PayTm", ""),
                Offer("PayTM", "Get 100 OFF on using PayTm", ""),
                Offer("PayTM", "Get 100 OFF on using PayTm", ""),
                Offer("PayTM", "Get 100 OFF on using PayTm", ""),
                Offer("PayTM", "Get 100 OFF on using PayTm", ""),
                Offer("PayTM", "Get 100 OFF on using PayTm", ""),
                Offer("PayTM", "Get 100 OFF on using PayTm", ""),
                Offer("PayTM", "Get 100 OFF on using PayTm", ""),
                Offer("PayTM", "Get 100 OFF on using PayTm", ""),
                Offer("PayTM", "Get 100 OFF on using PayTm", ""),
                Offer("PayTM", "Get 100 OFF on using PayTm", ""),
                Offer("PayTM", "Get 100 OFF on using PayTm", "")


                ))
    }


}