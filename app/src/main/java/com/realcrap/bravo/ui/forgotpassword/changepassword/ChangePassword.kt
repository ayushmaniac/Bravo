package com.realcrap.bravo.ui.forgotpassword.changepassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.location.Location
import com.realcrap.bravo.ui.login.Login
import com.realcrap.bravo.util.common.Event
import com.realcrap.bravo.util.display.Toaster
import kotlinx.android.synthetic.main.activity_change_password.*
import kotlinx.android.synthetic.main.activity_create_password.*
import kotlinx.android.synthetic.main.activity_login_email.*

class ChangePassword : BaseActivity<ChangePasswordViewModel>() {

    companion object {
        const val TAG = "LoginActivity"
    }


    lateinit var  realPassword : String
    lateinit var  confirmRealPassword : String

    override fun provideLayoutId(): Int = R.layout.activity_change_password


    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {

        newPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                realPassword = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        confirmNewPassword.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                confirmRealPassword = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })


        changeNewPasswordButton.setOnClickListener {
            if(realPassword.equals(confirmRealPassword)){
                viewModel.onPasswordChanged(realPassword)
                viewModel.changePassword()


            }
            else {

                Toaster.show(this, "Passwords do not match. Try again")

            }


        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchLogin.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, Login::class.java))
                finish()
            }
        })


        viewModel.changePasswordProgress.observe(this, Observer {
            changePswdProgress.visibility = if (it) View.VISIBLE else View.GONE
        })




    }




}
