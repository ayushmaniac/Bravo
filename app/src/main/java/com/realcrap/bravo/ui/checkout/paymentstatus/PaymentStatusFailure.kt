package com.realcrap.bravo.ui.checkout.paymentstatus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.realcrap.bravo.R
import kotlinx.android.synthetic.main.activity_payment_status_failure.*

class PaymentStatusFailure : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_status_failure)

            failure.playAnimation()
    }
}
