package com.realcrap.bravo.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.realcrap.bravo.data.repository.OtpRepository
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.allsalons.AllSalonsViewModel
import com.realcrap.bravo.ui.allsalons.salon.SalonAdapter
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.bookingdetails.BookingDetailsViewModel
import com.realcrap.bravo.ui.buisnesspage.BuisnessViewModel
import com.realcrap.bravo.ui.buisnesspage.services.ServicesAdapter
import com.realcrap.bravo.ui.checkout.CheckOutViewModel
import com.realcrap.bravo.ui.checkout.items.ItemsAdapter
import com.realcrap.bravo.ui.editprofile.EditProfileViewModel
import com.realcrap.bravo.ui.forgotpassword.ForgotPasswordViewModel
import com.realcrap.bravo.ui.forgotpassword.changepassword.ChangePasswordViewModel
import com.realcrap.bravo.ui.location.LocationViewModel
import com.realcrap.bravo.ui.login.LoginViewModel
import com.realcrap.bravo.ui.loginemail.LoginEmailViewModel
import com.realcrap.bravo.ui.main.MainViewModel
import com.realcrap.bravo.ui.setuppassword.CreatePasswordViewModel
import com.realcrap.bravo.ui.signup.RegistrationViewModel
import com.realcrap.bravo.ui.splash.SplashViewModel
import com.realcrap.bravo.ui.uploadpic.UploadPictureViewModel
import com.realcrap.bravo.util.ViewModelProviderFactory
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule (private val activity: BaseActivity<*>){


    @Provides
    fun provideContext(
    ) : Context = activity

    @Provides
    fun provideGridLayoutManager() = GridLayoutManager(activity, 2)

    @Provides
    fun provideServiceAdapter() = ServicesAdapter(activity.lifecycle, ArrayList())

    @Provides
    fun provideItemsAdapter() = ItemsAdapter(activity.lifecycle, ArrayList())

    @Provides
    fun provideLinearLayout() = LinearLayoutManager(activity)

    @Provides
    fun provideSalonAdapter() = SalonAdapter(activity.lifecycle, ArrayList())







    @Provides
    fun provideLoginViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository
    ): LoginViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(LoginViewModel::class) {
        LoginViewModel(compositeDisposable, networkHelper, schedulerProvider, userRepository)
    }).get(LoginViewModel::class.java)

    @Provides
    fun provideLoginEmailViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository

    ): LoginEmailViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(LoginEmailViewModel::class) {
        LoginEmailViewModel(compositeDisposable, networkHelper, schedulerProvider, userRepository)
    }).get(LoginEmailViewModel::class.java)


    @Provides
    fun provideRegistrationViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper
    ): RegistrationViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(RegistrationViewModel::class) {
        RegistrationViewModel(compositeDisposable, networkHelper, schedulerProvider)
    }).get(RegistrationViewModel::class.java)




    @Provides
    fun provideSplashViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository
    ): SplashViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(SplashViewModel::class) {
        SplashViewModel(schedulerProvider, compositeDisposable, networkHelper,userRepository)
    }).get(SplashViewModel::class.java)


    @Provides
    fun provideForgotPasswordViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            otpRepository: OtpRepository
    ): ForgotPasswordViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(ForgotPasswordViewModel::class) {
        ForgotPasswordViewModel(compositeDisposable, schedulerProvider, networkHelper, otpRepository)
    }).get(ForgotPasswordViewModel::class.java)

    @Provides
    fun provideChangePasswordViewModelViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            otpRepository: OtpRepository
    ): ChangePasswordViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(ChangePasswordViewModel::class) {
        ChangePasswordViewModel(compositeDisposable, networkHelper, schedulerProvider, otpRepository)
    }).get(ChangePasswordViewModel::class.java)


    @Provides
    fun provideCreateAccountViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository
    ): CreatePasswordViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(CreatePasswordViewModel::class) {
        CreatePasswordViewModel(compositeDisposable, networkHelper, schedulerProvider, userRepository)
    }).get(CreatePasswordViewModel::class.java)


    @Provides
    fun provideMainViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper
    ): MainViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(MainViewModel::class) {
        MainViewModel(compositeDisposable, networkHelper, schedulerProvider)
    }).get(MainViewModel::class.java)


    @Provides
    fun provideBuisnessViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository
    ): BuisnessViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(BuisnessViewModel::class) {
        BuisnessViewModel(compositeDisposable, networkHelper, schedulerProvider, userRepository, ArrayList())
    }).get(BuisnessViewModel::class.java)

    @Provides
    fun provideCheckouViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper
    ): CheckOutViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(CheckOutViewModel::class) {
        CheckOutViewModel(compositeDisposable, schedulerProvider, networkHelper)
    }).get(CheckOutViewModel::class.java)


    @Provides
    fun provideAllSalonsViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository
    ): AllSalonsViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(AllSalonsViewModel::class) {
        AllSalonsViewModel(compositeDisposable, schedulerProvider, networkHelper, userRepository, ArrayList())
    }).get(AllSalonsViewModel::class.java)


    @Provides
    fun provideBookingDetailsViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository
    ): BookingDetailsViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(BookingDetailsViewModel::class) {
        BookingDetailsViewModel(compositeDisposable, networkHelper, schedulerProvider, userRepository)
    }).get(BookingDetailsViewModel::class.java)


    @Provides
    fun provideLocationViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository
    ): LocationViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(LocationViewModel::class) {
        LocationViewModel(compositeDisposable, networkHelper, schedulerProvider, userRepository)
    }).get(LocationViewModel::class.java)

    @Provides
    fun provideEditProfileViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository

    ): EditProfileViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(EditProfileViewModel::class) {
        EditProfileViewModel(compositeDisposable, networkHelper, schedulerProvider, userRepository)
    }).get(EditProfileViewModel::class.java)


    @Provides
    fun provideUploadProfileViewModelViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper,
            userRepository: UserRepository

    ): UploadPictureViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(UploadPictureViewModel::class) {
        UploadPictureViewModel(compositeDisposable, networkHelper, schedulerProvider, userRepository)
    }).get(UploadPictureViewModel::class.java)



}