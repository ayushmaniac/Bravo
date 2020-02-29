package com.realcrap.bravo.ui.loginemail

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.forgotpassword.ForgotPassword
import com.realcrap.bravo.ui.location.Location
import com.realcrap.bravo.ui.main.MainActivity
import com.realcrap.bravo.util.common.Event
import com.realcrap.bravo.util.common.Status
import kotlinx.android.synthetic.main.activity_login_email.*

class LoginEmail : BaseActivity<LoginEmailViewModel>() {

    companion object {
        const val TAG = "LoginActivity"
    }

    override fun provideLayoutId(): Int = R.layout.activity_login_email

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        et_email.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onEmailChanged(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        et_password.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onPasswordChanged(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        bt_login.setOnClickListener {

            viewModel.onLogin()

        }

        forgotPasswordText.setOnClickListener {

            startActivity(Intent(this, ForgotPassword::class.java))
        }


    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchMain.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, Location::class.java))
                finish()
            }
        })

        viewModel.emailField.observe(this, Observer {
            if (et_email.text.toString() != it) et_email.setText(it)
        })

        viewModel.emailValidation.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> layoutemail.error = it.data?.run { getString(this) }
                else -> layoutemail.isErrorEnabled = false
            }
        })

        viewModel.passwordField.observe(this, Observer {
            if (et_password.text.toString() != it) et_email.setText(it)
        })

        viewModel.passwordValidation.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> emailLayoutReg.error = it.data?.run { getString(this) }
                else -> emailLayoutReg.isErrorEnabled = false
            }
        })

        viewModel.logginIn.observe(this, Observer {
            pb_Loading.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

}




