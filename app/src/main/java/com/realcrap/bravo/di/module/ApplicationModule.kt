package com.realcrap.bravo.di.module

import android.content.Context
import android.content.SharedPreferences
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.realcrap.bravo.BuildConfig
import com.realcrap.bravo.data.remote.NetworkService
import com.realcrap.bravo.data.remote.Networking
import com.realcrap.bravo.di.application.BravoApplication
import com.realcrap.bravo.util.network.NetworkError
import com.realcrap.bravo.util.network.NetworkHelper
import com.realcrap.bravo.util.rx.RxSchedulerProvider
import com.realcrap.bravo.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: BravoApplication) {


    @Provides
    fun provideContext() : Context = application

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
   fun provideNetworkService(): NetworkService = Networking.create(

            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB

    )



    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = application.getSharedPreferences("bootcamp-instagram-project-prefs", Context.MODE_PRIVATE)





}