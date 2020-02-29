package com.realcrap.bravo.ui.buisnesspage.services

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.realcrap.bravo.ui.base.BaseItemViewModel
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ServicesViewModel@Inject constructor(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
) : BaseItemViewModel<Services>(

        schedulerProvider,
        compositeDisposable,
        networkHelper
) {

    val serviceName: LiveData<String> = Transformations.map(data) { it.title }
    val serviceDesc: LiveData<String> = Transformations.map(data) { it.excerpt}
    val servicePrice : LiveData<String> = Transformations.map(data){it.price}

    override fun onCreate() {


    }


}
