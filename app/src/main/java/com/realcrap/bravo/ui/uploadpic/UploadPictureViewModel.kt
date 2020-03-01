package com.realcrap.bravo.ui.uploadpic

import androidx.lifecycle.MutableLiveData
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseViewModel
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.io.InputStream

class UploadPictureViewModel (compositeDisposable: CompositeDisposable, networkHelper: NetworkHelper,
                              schedulerProvider: SchedulerProvider,
                              private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate() {}





}

