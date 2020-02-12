package com.realcrap.bravo.ui.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.display.Toaster
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider,compositeDisposable,networkHelper) {

    var loginResult = MutableLiveData<Boolean>()
    var signupResult = MutableLiveData<Boolean>()
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


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
}



