package com.realcrap.bravo.ui.home

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.allsalons.salon.Salon
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.ui.home.homeoffers.HomeOffers
import com.realcrap.bravo.ui.home.homesalons.HomeSalons
import com.realcrap.bravo.util.common.Resource
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.util.ArrayList

class HomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        val userRepository: UserRepository,
        val allSalonList: ArrayList<HomeSalons>
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper){

    val homeOffersData = MutableLiveData<List<HomeOffers>>()
    val cityData =  MutableLiveData<String>()
    val homeSalonsData : MutableLiveData<Resource<List<HomeSalons>>> = MutableLiveData()
    val homeSalonProgress =  MutableLiveData<Boolean>()





    override fun onCreate() {

        if(userRepository.getUserLoc()!= null){

            cityData.postValue(userRepository.getUserLoc())
        }
        else
        {
            cityData.postValue(null)
        }


        homeOffersData.postValue(arrayListOf(
                HomeOffers("PayTm", ""),
                HomeOffers("PhonePe", ""),
                HomeOffers("GooglePay", ""),
                HomeOffers("Meenakshi", ""),
                HomeOffers("Habibs", "")

        ))

         getHomeSalons()





    }

    private fun getHomeSalons() {
        homeSalonProgress.postValue(true)
        if (homeSalonsData.value == null && checkInternetConnectionWithMessage()) {

            compositeDisposable.add(
                    userRepository.getAllHomeMerchantList(userRepository.getUserLoc().toString())
                            .subscribeOn(schedulerProvider.io())
                            .subscribe(
                                    {
                                        allSalonList.addAll(it)
                                        homeSalonsData.postValue(Resource.success(it))
                                        homeSalonProgress.postValue(false)

                                    },
                                    {
                                        handleNetworkError(it)
                                        homeSalonProgress.postValue(false)


                                    }
                            )

            )

        }
    }


}