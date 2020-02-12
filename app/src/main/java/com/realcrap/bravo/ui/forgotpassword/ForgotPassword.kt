package com.realcrap.bravo.ui.forgotpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity

class ForgotPassword : BaseActivity<ForgotPasswordViewModel>(){


    override fun provideLayoutId(): Int = R.layout.activity_forgot_password


    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

    }


}
