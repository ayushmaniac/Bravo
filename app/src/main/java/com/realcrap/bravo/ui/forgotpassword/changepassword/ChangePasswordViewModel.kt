package com.realcrap.bravo.ui.forgotpassword.changepassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.realcrap.bravo.data.repository.OtpRepository
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.*
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ChangePasswordViewModel (compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper,
                               schedulerProvider: SchedulerProvider,
                               private val otpRepository: OtpRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val validationList : MutableLiveData<List<Validation>> = MutableLiveData()
    val passwordData : MutableLiveData<Boolean> = MutableLiveData()
    val passwordField : MutableLiveData<String> = MutableLiveData()
    val launchLogin : MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val changePasswordProgress : MutableLiveData<Boolean> = MutableLiveData()
    val passwordValidation : LiveData<Resource<Int>> = filterValidation(Validation.Field.PASSWORD);

    private fun filterValidation(string: Validation.Field) =

            Transformations.map(validationList){
                it.find {
                    validation ->  validation.field == string}
                        ?.run { return@run this.resource }
                        ?: Resource.unknown()
            }




    fun onPasswordChanged(password : String) = passwordField.postValue(password)


    override fun onCreate() {
    }


    fun changePassword() {
        changePasswordProgress.postValue(true)
        val password = passwordField.value
        val validations = Validator.validateOnlyPassword(password)
        validationList.postValue(validations)
        if (validations.isNotEmpty() && password != null) {
            val successfulValidation = validations.filter { it.resource.status == Status.SUCCESS }
            if (successfulValidation.size == validations.size && checkInternetConnection()) {

                compositeDisposable.addAll(
                        password?.let {
                            otpRepository.updatePassword(it)
                                    .subscribeOn(schedulerProvider.io())
                                    .subscribe(
                                            {
                                                passwordData.postValue(true)
                                                launchLogin.postValue(Event(emptyMap()))
                                                changePasswordProgress.postValue(false)

                                            },
                                            {
                                                handleNetworkError(it)
                                                passwordData.postValue(true)
                                                changePasswordProgress.postValue(false)

                                            }
                                    )
                        }

                )
            }


        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}