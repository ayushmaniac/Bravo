package com.realcrap.bravo.ui.checkout.paymentstatus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.checkout.CheckOutViewModel
import com.realcrap.bravo.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_payment_status.*

class PaymentStatus :  BaseActivity<CheckOutViewModel>() {



    override fun provideLayoutId(): Int = R.layout.activity_payment_status

    override fun injectDependencies(activityComponent: ActivityComponent)  = activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {

        success.setOnClickListener {

            success.playAnimation()

        }

        proceedStatus.setOnClickListener {


            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
