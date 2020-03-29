package com.realcrap.bravo.di.component

import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.di.module.ActivityModule
import com.realcrap.bravo.di.scope.ActivityScope
import com.realcrap.bravo.ui.allsalons.AllSalons
import com.realcrap.bravo.ui.bookingdetails.BookingDetails
import com.realcrap.bravo.ui.buisnesspage.Buisness
import com.realcrap.bravo.ui.checkout.Checkout
import com.realcrap.bravo.ui.checkout.paymentstatus.PaymentStatus
import com.realcrap.bravo.ui.editprofile.EditProfile
import com.realcrap.bravo.ui.forgotpassword.ForgotPassword
import com.realcrap.bravo.ui.forgotpassword.changepassword.ChangePassword
import com.realcrap.bravo.ui.location.Location
import com.realcrap.bravo.ui.login.Login
import com.realcrap.bravo.ui.loginemail.LoginEmail
import com.realcrap.bravo.ui.loginemail.LoginEmailViewModel
import com.realcrap.bravo.ui.main.MainActivity
import com.realcrap.bravo.ui.mybooking.MyBookings
import com.realcrap.bravo.ui.setuppassword.CreatePassword
import com.realcrap.bravo.ui.signup.Registration
import com.realcrap.bravo.ui.splash.Splash
import com.realcrap.bravo.ui.uploadpic.UploadPicture
import dagger.Component

@ActivityScope
@Component(
        dependencies = [ApplicationComponent::class],
        modules = [ActivityModule::class]
)
interface ActivityComponent {


    fun inject(activity: Login)

    fun inject(activity: LoginEmail)

    fun inject(activity:Registration)

    fun inject(activity : Splash)

    fun inject(activity : ForgotPassword)

    fun inject(activity : CreatePassword)


    fun inject(activity : MainActivity)

    fun inject(activity: Buisness)

    fun inject(activity: Checkout)

    fun inject(activity: AllSalons)

    fun inject(activity: BookingDetails)

    fun inject(activity: Location)
    fun inject(activity: ChangePassword)

    fun inject(activity: EditProfile)

    fun inject(activity: UploadPicture)
    fun inject(activity: PaymentStatus)

    fun inject(activity: MyBookings)









}