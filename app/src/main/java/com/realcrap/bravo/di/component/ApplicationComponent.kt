package com.realcrap.bravo.di.component

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.realcrap.bravo.data.remote.NetworkService
import com.realcrap.bravo.data.repository.OtpRepository
import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.di.application.BravoApplication
import com.realcrap.bravo.di.module.ApplicationModule
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: BravoApplication)

    fun getContext() : Context

    fun getCompositeDisposable() : CompositeDisposable

    fun getSchedulerProvider(): SchedulerProvider

    fun getNetworkHelper(): NetworkHelper

    fun getFirebaseAuth() : FirebaseAuth

    fun getUserRepository() : UserRepository

    fun getNetworkService() : NetworkService

    fun getOtpRepositopry() : OtpRepository



}