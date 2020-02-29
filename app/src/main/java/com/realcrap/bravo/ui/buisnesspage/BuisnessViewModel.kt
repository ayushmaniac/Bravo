package com.realcrap.bravo.ui.buisnesspage

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.ui.buisnesspage.services.Services
import com.realcrap.bravo.util.common.Resource
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class BuisnessViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        schedulerProvider: SchedulerProvider,
        private val userRepository: UserRepository,
        val allServiceList: ArrayList<Services>

) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val buisnessData : MutableLiveData<Resource<List<Services>>> = MutableLiveData()
    val buisnessProgress : MutableLiveData<Boolean> = MutableLiveData()
    val buisnessName : MutableLiveData<String> = MutableLiveData()
    val buisnessType : MutableLiveData<String> = MutableLiveData()
    val buisnessAddress : MutableLiveData<String> = MutableLiveData()
    val buisnessRating : MutableLiveData<String> = MutableLiveData()
    val buisnessLati : MutableLiveData<String> = MutableLiveData()
    val buisnessLongi : MutableLiveData<String> = MutableLiveData()
    val buisnessError : MutableLiveData<Boolean> = MutableLiveData()










    override fun onCreate() {
    }

    fun loadServices(uniqueid : String){

        buisnessProgress.postValue(true)

        compositeDisposable.add(
                userRepository.getAllService(uniqueid)
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                                {
                                    buisnessData.postValue(Resource.success(it.servicesData))
                                    allServiceList.addAll(it.servicesData)
                                    buisnessProgress.postValue(false)
                                    buisnessName.postValue(it.merchants.storename)
                                    buisnessAddress.postValue(it.merchants.address)
                                    buisnessRating.postValue(it.merchants.rating.toString())
                                    buisnessType.postValue(it.merchants.storetype)
                                    buisnessLati.postValue(it.merchants.latitude)
                                    buisnessLongi.postValue(it.merchants.longitude)
                                },
                                {
                                    handleNetworkError(it)
                                    buisnessProgress.postValue(false)
                                    buisnessError.postValue(true)


                                }
                        )

        )


    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}