package com.realcrap.bravo.ui.checkout

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.ui.checkout.items.Items
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class CheckOutViewModel(
        compositeDisposable: CompositeDisposable,
        schedulerProvider: SchedulerProvider,
        networkHelper: NetworkHelper
) : BaseViewModel(
        schedulerProvider,compositeDisposable,networkHelper
){

    val itemsData = MutableLiveData<List<Items>>()

    override fun onCreate() {

        itemsData.postValue(arrayListOf(
                Items("1", "Hair Cut @ 3:00PM", "3:00PM"),
                Items("1", "Hair Cut @ 4:00PM", "3:00PM")

                ))

    }


}