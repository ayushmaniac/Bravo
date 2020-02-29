package com.realcrap.bravo.ui.bookingdetails

import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class BookingDetailsViewModel (compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper,
                               schedulerProvider: SchedulerProvider,
                               private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {


    override fun onCreate() {
    }
}
