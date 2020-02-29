package com.realcrap.bravo.ui.allsalons.salon

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.realcrap.bravo.ui.base.BaseItemViewModel
import com.realcrap.bravo.util.common.Event
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SalonViewModel @Inject constructor(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
) : BaseItemViewModel<Salon>(schedulerProvider,
        compositeDisposable, networkHelper) {

    val name: LiveData<String> = Transformations.map(data) { it.storename }
    val rating: LiveData<Int> = Transformations.map(data) { it.rating }
    val desc : LiveData<String> = Transformations.map(data){it.address}
    val merchantId : MutableLiveData<String> = MutableLiveData()



    override fun onCreate() {
    }



    fun onMerchantClicked(adapterPosition: Int) {
        merchantId.postValue(adapterPosition.toString())
    }
}