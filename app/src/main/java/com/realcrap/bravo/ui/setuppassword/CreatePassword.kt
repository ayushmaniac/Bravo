package com.realcrap.bravo.ui.setuppassword

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
import com.realcrap.bravo.ui.login.Login
import com.realcrap.bravo.ui.main.MainActivity
import com.realcrap.bravo.util.common.Event
import com.realcrap.bravo.util.common.Status
import com.realcrap.bravo.util.display.Toaster
import kotlinx.android.synthetic.main.activity_create_password.*
import kotlinx.android.synthetic.main.activity_login_email.*

class CreatePassword : BaseActivity<CreatePasswordViewModel>() {

    var name : String = ""
    var email : String = ""
    var mobile : String = ""

    lateinit var  realPassword : String
    lateinit var  confirmRealPassword : String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         name = intent.getStringExtra("name")
         email = intent.getStringExtra("email")
         mobile = intent.getStringExtra("mobile")

    }


    override fun provideLayoutId(): Int = R.layout.activity_create_password

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        createPassword.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

                realPassword = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        confirmPassword.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                confirmRealPassword = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })




        createAccountButton.setOnClickListener {

            if(realPassword.equals(confirmRealPassword)){

                viewModel.onPasswordChanged(realPassword)
                viewModel.onEmailChanged(email)
                viewModel.onNameChanged(name)
                viewModel.onMobileChanged(mobile)

                viewModel.createAccount()


            }
            else {

                Toaster.show(this, "Passwords do not match. Try again")

            }


        }



    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchMain.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, Login::class.java))
                finish()
            }
        })

        viewModel.logginIn.observe(this, Observer {
            createAccountProgress.visibility = if (it) View.VISIBLE else View.GONE
        })


        viewModel.messageText.observe(this, Observer {
            Toaster.show(this, it)
        })

    }



}
