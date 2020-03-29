package com.realcrap.bravo.ui.allsalons

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.allsalons.salon.Salon
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.Resource
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.text.DateFormat
import java.util.*

class AllSalonsViewModel (compositeDisposable: CompositeDisposable,
                          schedulerProvider: SchedulerProvider,
                            networkHelper: NetworkHelper,
                        val userRepository: UserRepository,
                          val allSalonList: ArrayList<Salon>

) : BaseViewModel(
schedulerProvider,compositeDisposable,networkHelper

) {
    val dataAllSalons : MutableLiveData<Resource<List<Salon>>> = MutableLiveData()
    val getTodaysDate = MutableLiveData<String>()
    val calender : Calendar = Calendar.getInstance()
    val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calender.time)
    val allProgress = MutableLiveData<Boolean>()




    override fun onCreate() {
        getAllMerchants(userRepository.getUserLoc().toString())

        getTodaysDate.postValue(
                currentDate
        )

    }


    fun getAllMerchants(city : String) {
        allProgress.postValue(true)
        if (dataAllSalons.value == null && checkInternetConnectionWithMessage()) {

            compositeDisposable.add(
                    userRepository.getAllMerchantList(city)
                            .subscribeOn(schedulerProvider.io())
                            .subscribe(
                                    {
                                        allSalonList.addAll(it)
                                        dataAllSalons.postValue(Resource.success(it))
                                        allProgress.postValue(false)

                                    },
                                    {
                                        handleNetworkError(it)
                                        allProgress.postValue(false)


                                    }
                            )

            )

        }
    }
}
