package com.realcrap.bravo.ui.setuppassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.*
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class CreatePasswordViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        schedulerProvider: SchedulerProvider,
       private val userRepository: UserRepository

) : BaseViewModel(
        schedulerProvider, compositeDisposable, networkHelper
)
{

    val launchMain : MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val passwordField : MutableLiveData<String> = MutableLiveData()
    val emailField : MutableLiveData<String> = MutableLiveData()
    val nameField : MutableLiveData<String> = MutableLiveData()
    val mobileField : MutableLiveData<String> = MutableLiveData()
    val logginIn : MutableLiveData<Boolean> = MutableLiveData()
    val messageText : MutableLiveData<String> = MutableLiveData()


    fun onPasswordChanged(password : String) = passwordField.postValue(password)
    fun onEmailChanged(email : String) = emailField.postValue(email)
    fun onNameChanged(name : String) = nameField.postValue(name)
    fun onMobileChanged(mobile : String) = mobileField.postValue(mobile)




    override fun onCreate() {
    }




    fun createAccount(){

        val name = nameField.value
        val mobile = mobileField.value
        val email = emailField.value
        val password = passwordField.value



        if(name!=null &&mobile!=null && email!=null && password!=null) {
            if (checkInternetConnection()) {

                logginIn.postValue(true)
                compositeDisposable.addAll(
                    userRepository.createAccount(name, email, mobile, password)
                            .subscribeOn(schedulerProvider.io())
                            .subscribe(
                                    {

                                        if(it.status == 1){
                                            logginIn.postValue(false)
                                            launchMain.postValue(Event(emptyMap()))
                                            messageText.postValue(it.text)
                                        }
                                        else if(it.status == 0){
                                            logginIn.postValue(false)
                                            messageText.postValue(it.text)
                                        }

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


