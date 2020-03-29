package com.realcrap.bravo.ui.checkout.paymentstatus

import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class PaymenStausViewModel (compositeDisposable: CompositeDisposable,
                            schedulerProvider: SchedulerProvider,
                            networkHelper: NetworkHelper,
                            val userRepository: UserRepository
) : BaseViewModel(
schedulerProvider,compositeDisposable,networkHelper) {
    override fun onCreate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}