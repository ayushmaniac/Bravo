package com.realcrap.bravo.ui.mybooking

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.data.remote.response.list.OrderListResponse
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MyBookingViewModel (compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper,
                          schedulerProvider: SchedulerProvider,
                          private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {
    override fun onCreate() {}

    val orderListProgress = MutableLiveData<Boolean>()
    val orderListWhole = MutableLiveData<OrderListResponse>()

    fun getOrderList()
    {
        if(checkInternetConnection()) {


            orderListProgress.postValue(true)
            compositeDisposable.add(
                    userRepository.getOrderList()
                            .subscribeOn(schedulerProvider.io())
                            .subscribe(
                                    {
                                        orderListProgress.postValue(false)
                                        orderListWhole.postValue(it)
                                    },
                                    {
                                        orderListProgress.postValue(false)
                                        handleNetworkError(it)

                                    }
                            )
            )
        }

    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}