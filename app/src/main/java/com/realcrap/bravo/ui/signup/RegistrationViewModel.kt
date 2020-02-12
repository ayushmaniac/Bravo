package com.realcrap.bravo.ui.signup

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.*
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class RegistrationViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val validationList: MutableLiveData<List<Validation>> = MutableLiveData()

    val launchCreatePassword: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val emailField: MutableLiveData<String> = MutableLiveData()
    val nameField: MutableLiveData<String> = MutableLiveData()
    val mobileField: MutableLiveData<String> = MutableLiveData()
    val emailValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL)
    val logginIn: MutableLiveData<Boolean> = MutableLiveData()


    private fun filterValidation(string: Validation.Field) =

            Transformations.map(validationList) {
                it.find { validation -> validation.field == string }
                        ?.run { return@run this.resource }
                        ?: Resource.unknown()
            }

    fun onEmailChanged(email: String) = emailField.postValue(email)
    fun onMobileChanged(mobile: String) = mobileField.postValue(mobile)
    fun onNameChanged(name: String) = nameField.postValue(name)


    override fun onCreate() {

    }


    fun onRegistration() {

        val email = emailField.value;
        val mobile = mobileField.value
        val name = nameField.value

        val validations = Validator.validateOnlyEmail(email)
        validationList.postValue(validations)

        if (validations.isNotEmpty() && email != null && mobile != null && name!=null) {
            val successfulValidation = validations.filter { it.resource.status == Status.SUCCESS }
            if (successfulValidation.size == validations.size && checkInternetConnection()) {
                logginIn.postValue(true)
                launchCreatePassword.postValue(Event(emptyMap()))


            } else {

                logginIn.postValue(false)
            }
        }
    }
}

