package com.realcrap.bravo.ui.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.setuppassword.CreatePassword
import com.realcrap.bravo.util.common.Event
import com.realcrap.bravo.util.common.Status
import kotlinx.android.synthetic.main.activity_login_email.*
import kotlinx.android.synthetic.main.activity_registration.*

class Registration : BaseActivity<RegistrationViewModel>() {


    override fun provideLayoutId(): Int = R.layout.activity_registration

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        nameEdText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onNameChanged(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}


        })

        emailEdText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onEmailChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        mobileEdText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onMobileChanged(s.toString())

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        registerButton.setOnClickListener {

            viewModel.onRegistration()

        }

    }

    override fun setupObservers() {
        super.setupObservers()


        viewModel.launchCreatePassword.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {

                val intent = Intent(applicationContext, CreatePassword::class.java)
                    intent.putExtra("name", nameEdText.text.toString())
                    intent.putExtra("email", emailEdText.text.toString())
                    intent.putExtra("mobile", mobileEdText.text.toString())

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }

        })

        viewModel.emailField.observe(this, Observer {
            if (emailEdText.text.toString() != it) emailEdText.setText(it)
        })

        viewModel.emailValidation.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> emailLayoutHere.error = it.data?.run { getString(this) }
                else -> emailLayoutHere.isErrorEnabled = false
            }
        })


        viewModel.logginIn.observe(this, Observer {
            regProgress.visibility = if (it) View.VISIBLE else View.GONE
        })

    }


}