package com.realcrap.bravo.ui.login

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.common.Event
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        schedulerProvider: SchedulerProvider,
        val userRepository: UserRepository
) : BaseViewModel(schedulerProvider,compositeDisposable,networkHelper) {

    var loginResult = MutableLiveData<Boolean>()
    var signupResult = MutableLiveData<Boolean>()
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val launchMain : MutableLiveData<Event<Map<String, String>>> = MutableLiveData()
    val statusData : MutableLiveData<String> = MutableLiveData()



    override fun onCreate() {

    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


    fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        loginResult.postValue(true)

                    }
                    else {

                        loginResult.postValue(false)
                    }
                }
    }


    fun createNewUser(currentUser: FirebaseUser?) {

        if(currentUser!=null){
           compositeDisposable.addAll(
                   userRepository.
                           createAccount(currentUser!!.displayName.toString(),
                                   currentUser.phoneNumber.toString(),
                                   currentUser.email.toString(), "123456")
                           .subscribeOn(schedulerProvider.io())
                           .subscribe(
                                   {
                                       if(it.status==1){
                                           userRepository.saveNewUser(it)
                                           signupResult.postValue(true)
                                           launchMain.postValue(Event(emptyMap()))
                                       }
                                       else if(it.status==2){

                                           statusData.postValue(it.text)
                                           signupResult.postValue(true)


                                       }



                                   },
                                   {

                                       handleNetworkError(it)
                                       signupResult.postValue(false)

                                   }
                           )


           )


        }



    }
}



