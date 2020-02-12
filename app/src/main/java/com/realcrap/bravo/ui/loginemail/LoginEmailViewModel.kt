package com.realcrap.bravo.ui.loginemail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.*
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginEmailViewModel(compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper,
                          schedulerProvider: SchedulerProvider,
                            private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper){

    private val validationList : MutableLiveData<List<Validation>> = MutableLiveData()

    val launchMain : MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val emailField : MutableLiveData<String> = MutableLiveData()
    val passwordField : MutableLiveData<String> = MutableLiveData()
    val logginIn : MutableLiveData<Boolean> = MutableLiveData()

    val emailValidation : LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL);

    val passwordValidation : LiveData<Resource<Int>> = filterValidation(Validation.Field.PASSWORD);


    private fun filterValidation(string: Validation.Field) =

            Transformations.map(validationList){
                it.find {
                    validation ->  validation.field == string}
                        ?.run { return@run this.resource }
                        ?: Resource.unknown()
            }


    override fun onCreate() {


    }

    fun onEmailChanged(email : String) = emailField.postValue(email)
    fun onPasswordChanged(password : String) = passwordField.postValue(password)

    fun onLogin() {

        val email = emailField.value
        val password = passwordField.value

        val validations = Validator.validateLoginFields(email, password)
        validationList.postValue(validations)

        if(validations.isNotEmpty() && email != null && password!=null){
            val successfulValidation = validations.filter { it.resource.status == Status.SUCCESS }
            if(successfulValidation.size == validations.size && checkInternetConnection()){
                logginIn.postValue(true)
                compositeDisposable.addAll(
                        userRepository.doUserLogin(email, password)
                                .subscribeOn(schedulerProvider.io())
                                .subscribe(
                                        {
                                            userRepository.saveCurrentUser(it)
                                            logginIn.postValue(false)
                                            launchMain.postValue(Event(emptyMap()))

                                        },
                                        {
                                            handleNetworkError(it)
                                            logginIn.postValue(false)

                                        }
                                )

                )

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}