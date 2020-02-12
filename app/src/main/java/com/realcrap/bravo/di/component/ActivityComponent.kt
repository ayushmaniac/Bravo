package com.realcrap.bravo.di.component

import com.realcrap.bravo.data.repository.UserRepository
import com.realcrap.bravo.di.module.ActivityModule
import com.realcrap.bravo.di.scope.ActivityScope
import com.realcrap.bravo.ui.forgotpassword.ForgotPassword
import com.realcrap.bravo.ui.login.Login
import com.realcrap.bravo.ui.loginemail.LoginEmail
import com.realcrap.bravo.ui.loginemail.LoginEmailViewModel
import com.realcrap.bravo.ui.main.MainActivity
import com.realcrap.bravo.ui.setuppassword.CreatePassword
import com.realcrap.bravo.ui.signup.Registration
import com.realcrap.bravo.ui.splash.Splash
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


}