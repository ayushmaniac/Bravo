package com.realcrap.bravo.ui.home

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.ui.home.homeoffers.HomeOffers
import com.realcrap.bravo.ui.home.homesalons.HomeSalons
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper){

    val homeOffersData = MutableLiveData<List<HomeOffers>>()
    val homeSalonsData = MutableLiveData<List<HomeSalons>>()



    override fun onCreate() {

        homeOffersData.postValue(arrayListOf(
                HomeOffers("PayTm", ""),
                HomeOffers("PhonePe", ""),
                HomeOffers("GooglePay", ""),
                HomeOffers("Meenakshi", ""),
                HomeOffers("Habibs", "")

        ))

        homeSalonsData.postValue(arrayListOf(
                HomeSalons("Meenakshi", ""),
                HomeSalons("Habibs", ""),
                HomeSalons("Shree Hair Cut", ""),
                HomeSalons("Lovely Hair Cut", ""),
                HomeSalons("Le Cut", ""),
                HomeSalons("IOSIS", ""),
                HomeSalons("New Mens", "")

                ))




    }




}