package com.realcrap.bravo.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.realcrap.bravo.ui.base.BaseFragment
import com.realcrap.bravo.ui.forgotpassword.ForgotPasswordViewModel
import com.realcrap.bravo.ui.home.HomeViewModel
import com.realcrap.bravo.ui.offers.OffersViewModel
import com.realcrap.bravo.ui.profile.ProfileViewModel
import com.realcrap.bravo.util.ViewModelProviderFactory
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideHomeViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper
    ): HomeViewModel = ViewModelProviders.of(
            fragment, ViewModelProviderFactory(HomeViewModel::class) {
        HomeViewModel(schedulerProvider, compositeDisposable, networkHelper)
    }).get(HomeViewModel::class.java)


    @Provides
    fun provideOffersViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper
    ): OffersViewModel = ViewModelProviders.of(
            fragment, ViewModelProviderFactory(OffersViewModel::class) {
        OffersViewModel(compositeDisposable, schedulerProvider, networkHelper)
    }).get(OffersViewModel::class.java)

    @Provides
    fun provideProfileViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper
    ): ProfileViewModel = ViewModelProviders.of(
            fragment, ViewModelProviderFactory(ProfileViewModel::class) {
        ProfileViewModel(compositeDisposable, schedulerProvider, networkHelper)
    }).get(ProfileViewModel::class.java)


}