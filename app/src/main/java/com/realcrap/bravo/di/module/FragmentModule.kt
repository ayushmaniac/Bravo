package com.realcrap.bravo.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.di.scope.ActivityContext
import com.realcrap.bravo.di.scope.ActivityScope
import com.realcrap.bravo.ui.base.BaseFragment
import com.realcrap.bravo.ui.forgotpassword.ForgotPasswordViewModel
import com.realcrap.bravo.ui.home.HomeViewModel
import com.realcrap.bravo.ui.home.homeoffers.HomeOfferAdapter
import com.realcrap.bravo.ui.home.homesalons.HomeSalonsAdapter
import com.realcrap.bravo.ui.offers.OffersViewModel
import com.realcrap.bravo.ui.offers.offersutil.OfferAdapter
import com.realcrap.bravo.ui.profile.ProfileViewModel
import com.realcrap.bravo.ui.referandearn.ReferViewModel
import com.realcrap.bravo.util.ViewModelProviderFactory
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @ActivityContext
    @Provides
    fun provideContext() : Context = fragment.context!!

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideOffersAdapter()=OfferAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun provideHomeOffersAdapter()= HomeOfferAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun provideHomeSalonsAdapter()= HomeSalonsAdapter(fragment.lifecycle, ArrayList())


    @Provides
    fun provideHomeViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository
    ): HomeViewModel = ViewModelProviders.of(
            fragment, ViewModelProviderFactory(HomeViewModel::class) {
        HomeViewModel(schedulerProvider, compositeDisposable, networkHelper, userRepository)
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
            networkHelper: NetworkHelper,
            userRepository: UserRepository
    ): ProfileViewModel = ViewModelProviders.of(
            fragment, ViewModelProviderFactory(ProfileViewModel::class) {
        ProfileViewModel(compositeDisposable, schedulerProvider, networkHelper, userRepository)
    }).get(ProfileViewModel::class.java)

    @Provides
    fun provideReferViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper
    ): ReferViewModel = ViewModelProviders.of(
            fragment, ViewModelProviderFactory(ReferViewModel::class) {
        ReferViewModel(schedulerProvider, compositeDisposable, networkHelper)
    }).get(ReferViewModel::class.java)

}