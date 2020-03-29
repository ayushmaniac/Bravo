package com.realcrap.bravo.ui.checkout

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.ui.offers.Transfer
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class CheckOutViewModel(
        compositeDisposable: CompositeDisposable,
        schedulerProvider: SchedulerProvider,
        networkHelper: NetworkHelper,
        val userRepository: UserRepository
) : BaseViewModel(
        schedulerProvider,compositeDisposable,networkHelper
){

    val merchantIdData = MutableLiveData<String>()
    val orderDateData = MutableLiveData<String>()
    val orderTimeData = MutableLiveData<String>()
    val serviceIdsData = MutableLiveData<List<Transfer>>()
    var idList = ArrayList<String>()
    var checkOutProgress = MutableLiveData<Boolean>()
    val launchSuccess = MutableLiveData<Boolean>()
    val launchFailure = MutableLiveData<Boolean>()
    val couponField = MutableLiveData<String>()

    //coupon
    val couponProgress = MutableLiveData<Boolean>()
    val offAmoumt = MutableLiveData<String>()
    val totalAmount = MutableLiveData<String>()
    val couponError = MutableLiveData<String>()





    fun onCouponText(coupon : String) = couponField.postValue(coupon)




    fun onMerchantId(id : String) = merchantIdData.postValue(id)
    fun onOrderDate(orderDate : String) = orderDateData.postValue(orderDate)
    fun onOrderTime(orderTime : String) = orderTimeData.postValue(orderTime)
    fun onServiceIds(serviceIds : List<Transfer>) = serviceIdsData.postValue(serviceIds)

    fun extractIds(){
        for(element in serviceIdsData.value!!){
            idList.add(element.id)
        }
        Log.d("ACTI", idList.toString())

    }


    fun createOrderByCash(){
        extractIds()
        checkOutProgress.postValue(true)
        compositeDisposable.add(
                userRepository.checkOutCash("1", idList.toString(), orderDateData.value.toString(), orderTimeData.value.toString())
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                                {
                                    checkOutProgress.postValue(false)

                                    if(it.status.equals("1")){

                                        launchSuccess.postValue(true)
                                    }
                                    else {

                                        launchFailure.postValue(true)

                                    }
                                },
                                {
                                    checkOutProgress.postValue(false)
                                    launchSuccess.postValue(true)

                                }
                        )
        )


    }

    fun applyCoupon(merchantId: String, amount: String) {
        couponProgress.postValue(true)
        val couponCode = couponField.value
        if(couponCode!=null && checkInternetConnection()) {
            compositeDisposable.add(
                    userRepository.applyCoupon(merchantId, couponCode, amount)
                            .subscribeOn(schedulerProvider.io())
                            .subscribe(
                                    {
                                    couponProgress.postValue(false)
                                        if(it.status.equals("1")){
                                            offAmoumt.postValue(it.coupanamt)
                                            totalAmount.postValue(it.totalamt)
                                        }
                                        else if(it.status.equals("0")) {

                                            couponError.postValue(it.message)
                                        }


                                    },
                                    {
                                        handleNetworkError(it)
                                        couponProgress.postValue(false)


                                    }
                            )
            )
        }

    }


    fun removeCoupon(amount: String){
        couponProgress.postValue(true)
        if(checkInternetConnection()){
            compositeDisposable.add(
                    userRepository.removeCoupon(amount)
                            .subscribeOn(schedulerProvider.io())
                            .subscribe(
                                    {
                                        couponProgress.postValue(false)
                                        offAmoumt.postValue(it.coupanamt)
                                        totalAmount.postValue(it.totalamt)
                                    },
                                    {

                                        handleNetworkError(it)
                                        couponProgress.postValue(false)

                                    }
                            )
            )

        }

    }

    override fun onCreate() {


    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}