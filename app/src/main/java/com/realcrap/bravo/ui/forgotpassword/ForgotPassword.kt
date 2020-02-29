package com.realcrap.bravo.ui.forgotpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.forgotpassword.changepassword.ChangePassword
import com.realcrap.bravo.ui.location.Location
import com.realcrap.bravo.util.common.Event
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.change_pswd_sheet.*
import kotlinx.android.synthetic.main.verifyotp_sheet.*
import kotlinx.android.synthetic.main.verifyotp_sheet.view.*

class ForgotPassword : BaseActivity<ForgotPasswordViewModel>(){



    override fun provideLayoutId(): Int = R.layout.activity_forgot_password


    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        forgotEdText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onEmailChanged(s.toString())
            }

        })


        forgotPassButton.setOnClickListener {
            viewModel.generateOtp()
//
        }




    }

    override fun setupObservers() {


        super.setupObservers()
        viewModel.handlerNewPassword.observe(this, Observer<Event<Map<String, String>>> {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext, ChangePassword::class.java))
                finish()
            }
        })

    viewModel.launchSheet.observe(this, Observer<Event<Map<String, String>>> {



        it.getIfNotHandled()?.run {

            val bottomSheetDialog  = BottomSheetDialog(this@ForgotPassword)
            bottomSheetDialog.setContentView(R.layout.verifyotp_sheet)
            bottomSheetDialog.setCancelable(false)
            bottomSheetDialog.verifyOtpButton.setOnClickListener{


                var finalOtp = bottomSheetDialog.firstPinView.text.toString()
                viewModel.verifyOtp(finalOtp)

                viewModel.verifyOtpProgress.observe(this@ForgotPassword, Observer {
                    bottomSheetDialog.verifyOtpProgressbar.visibility = if (it) View.VISIBLE else View.GONE
                    bottomSheetDialog.dismiss()

                })

            }
            bottomSheetDialog.show()

        }


        })

        viewModel.otpProgressData.observe(this, Observer {
            forgotProgress.visibility = if (it) View.VISIBLE else View.GONE

        })



    }


    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}

//                val again  = BottomSheetDialog(applicationContext)
//                again.setContentView(R.layout.change_pswd_sheet)
//                again.setCancelable(false)
//                again.show()