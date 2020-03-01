package com.realcrap.bravo.ui.location

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class LocationViewModel (
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        schedulerProvider: SchedulerProvider,
       val userRepository: UserRepository
) : BaseViewModel(schedulerProvider,compositeDisposable,networkHelper) {

    val cityOneData = MutableLiveData<Boolean>()
    val cityTwoData = MutableLiveData<Boolean>()


    override fun onCreate() {
    }

    fun onCityOneSelected() {

        if(userRepository.getUserLoc()!=null){
            userRepository.saveUserLoc("1")

            cityOneData.postValue(true)

        }
        else {
            userRepository.saveUserLoc("1")
            cityOneData.postValue(true)

        }

    }

    fun onCityTwoSelected(){

        if(userRepository.getUserLoc()!=null){
            userRepository.saveUserLoc("2")
            cityTwoData.postValue(true)


        }
        else {
            userRepository.saveUserLoc("2")
            cityTwoData.postValue(true)


        }

    }
}
