package com.realcrap.bravo.ui.editprofile

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.data.remote.response.UserDetailsResponse
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.Resource
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class EditProfileViewModel (compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper,
                            schedulerProvider: SchedulerProvider,
                            private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val dataUser : MutableLiveData<Resource<UserDetailsResponse.Users>> = MutableLiveData()
    val dataProgress = MutableLiveData<Boolean>()



    override fun onCreate() {

        getUserDetails()
    }

    private fun getUserDetails() {
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

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}