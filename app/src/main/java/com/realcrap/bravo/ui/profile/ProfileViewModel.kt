package com.realcrap.bravo.ui.profile

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.data.remote.response.UserDetailsResponse
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.Resource
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ProfileViewModel(compositeDisposable: CompositeDisposable,
                       schedulerProvider: SchedulerProvider,
                       networkHelper: NetworkHelper,
                      val userRepository: UserRepository) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper){


    val logoutData = MutableLiveData<Boolean>()
    val userNameData = MutableLiveData<String>()
    val dataProgress = MutableLiveData<Boolean>()
    val dataUser : MutableLiveData<Resource<UserDetailsResponse.Users>> = MutableLiveData()



    override fun onCreate() {
        if(userRepository.getCurrentUser()!=null){

        }
    }


     fun getUserDetails() {
        dataProgress.postValue(true)
        compositeDisposable.add(
                userRepository.getUserDetails()
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                                {

                                    dataUser.postValue(Resource.success(it))
                                    dataProgress.postValue(false)


                                },
                                {

                                    handleNetworkError(it)
                                    dataProgress.postValue(false)


                                }
                        )
        )
    }

    fun logOut(){
        if(userRepository.getCurrentUser()!=null){
            userRepository.removeCurrentUser()
            logoutData.postValue(true)
        }
        else {
            logoutData.postValue(false)
        }

    }

}