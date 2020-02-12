package com.realcrap.bravo.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.forgotpassword.ForgotPasswordViewModel
import com.realcrap.bravo.ui.login.LoginViewModel
import com.realcrap.bravo.ui.loginemail.LoginEmailViewModel
import com.realcrap.bravo.ui.main.MainViewModel
import com.realcrap.bravo.ui.setuppassword.CreatePasswordViewModel
import com.realcrap.bravo.ui.signup.RegistrationViewModel
import com.realcrap.bravo.ui.splash.SplashViewModel
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
    fun provideLoginViewModel(
            schedulerProvider: SchedulerProvider,
            compositeDisposable: CompositeDisposable,
            networkHelper: NetworkHelper
    ): LoginViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(LoginViewModel::class) {
        LoginViewModel(compositeDisposable, networkHelper, schedulerProvider)
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
            networkHelper: NetworkHelper
    ): ForgotPasswordViewModel = ViewModelProviders.of(
            activity, ViewModelProviderFactory(ForgotPasswordViewModel::class) {
        ForgotPasswordViewModel(compositeDisposable, schedulerProvider, networkHelper)
    }).get(ForgotPasswordViewModel::class.java)


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
  
}