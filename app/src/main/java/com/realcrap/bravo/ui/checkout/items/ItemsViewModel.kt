package com.realcrap.bravo.ui.checkout.items

import com.realcrap.bravo.ui.base.BaseItemViewHolder
import com.realcrap.bravo.ui.base.BaseItemViewModel
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ItemsViewModel @Inject constructor(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper

) : BaseItemViewModel<Items>(
        schedulerProvider,
        compositeDisposable,
        networkHelper

)
{
    override fun onCreate() {

    }
}