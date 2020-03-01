package com.realcrap.bravo.ui.home.homesalons

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.realcrap.bravo.ui.base.BaseItemViewModel
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HomeSalonsItemViewModel @Inject constructor(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
) : BaseItemViewModel<HomeSalons>(schedulerProvider,
        compositeDisposable, networkHelper) {

    val name: LiveData<String> = Transformations.map(data) { it.storename }
    val rating: LiveData<Int> = Transformations.map(data) { it.rating }
    val image: LiveData<String> = Transformations.map(data) { it.logo }
    val merchantId: LiveData<String> = Transformations.map(data) { it.id }
    override fun onCreate() {
    }


}