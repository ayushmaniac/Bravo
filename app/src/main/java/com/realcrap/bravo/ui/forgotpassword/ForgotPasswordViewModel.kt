package com.realcrap.bravo.ui.forgotpassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.realcrap.bravo.data.repository.OtpRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.Event
import com.realcrap.bravo.util.common.Resource
import com.realcrap.bravo.util.common.Validation
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class ForgotPasswordViewModel(compositeDisposable: CompositeDisposable,
                              schedulerProvider: SchedulerProvider,
                              networkHelper: NetworkHelper,
                              val otpRepository: OtpRepository
) : BaseViewModel(schedulerProvider,
                                compositeDisposable,networkHelper){
    override fun onCreate() {
    }
    private val validationList : MutableLiveData<List<Validation>> = MutableLiveData()
    val emailField : MutableLiveData<String> = MutableLiveData()
    val otpProgressData : MutableLiveData<Boolean> = MutableLiveData()
    val otpGenerateData : MutableLiveData<Boolean> = MutableLiveData()
    val launchSheet : MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val verifyOtpProgress :  MutableLiveData<Boolean> = MutableLiveData()
    val handlerNewPassword : MutableLiveData<Event<Map<String, String>>> = MutableLiveData()




    val emailValidation : LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL)
    fun onEmailChanged(email : String) = emailField.postValue(email)




    private fun filterValidation(string: Validation.Field) =

            Transformations.map(validationList){
                it.find {
                    validation ->  validation.field == string}
                        ?.run { return@run this.resource }
                        ?: Resource.unknown()
            }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun generateOtp() {
        otpProgressData.postValue(true)
        otpGenerateData.postValue(true)
        val email = emailField.value

        compositeDisposable.addAll(

                email?.let {
                    otpRepository.generateOtp(it)
                            .subscribeOn(schedulerProvider.io())
                            .subscribe(
                                    {
                                        otpRepository.saveNewUserId(it)
                                        otpProgressData.postValue(false)
                                        otpGenerateData.postValue(true)
                                        launchSheet.postValue(Event(emptyMap()))
                                    },
                                    {
                                        otpProgressData.postValue(false)
                                        otpGenerateData.postValue(true)


                                    }
                            )
                }
        )

    }

    fun verifyOtp(otp : String) {
        verifyOtpProgress.postValue(true)

        compositeDisposable.addAll(
                otpRepository.verifyOtp(otp)
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                                {
                                    verifyOtpProgress.postValue(false)
                                    handlerNewPassword.postValue(Event(emptyMap()))


                                },

                                {

                                    verifyOtpProgress.postValue(false)
                                    handleNetworkError(it)

                                }
                        )

        )
    }

}