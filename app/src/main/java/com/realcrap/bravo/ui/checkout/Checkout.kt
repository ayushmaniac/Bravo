package com.realcrap.bravo.ui.checkout

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.checkout.items.ItemsAdapter
import com.realcrap.bravo.ui.checkout.paymentstatus.PaymentStatus
import com.realcrap.bravo.ui.offers.Transfer
import com.realcrap.bravo.util.display.Toaster
import kotlinx.android.synthetic.main.activity_checkout.*
import javax.inject.Inject

class   Checkout : BaseActivity<CheckOutViewModel>(){

    var transferList : List<Transfer>? = null
    var selectedDate : String? = null
    var selectedTime : String? = null
    var finalPrice : Int = 0
    var couponDirection : Boolean = false



    override fun provideLayoutId(): Int = R.layout.activity_checkout

    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun setupView(savedInstanceState: Bundle?) {


        serviceItemsRecycler.apply {

            layoutManager = linearLayoutManager

        }


        payCash.setOnClickListener{
            viewModel.createOrderByCash()
        }


        haveApromoCode.setOnClickListener {
            showCouponLayout.visibility = View.VISIBLE
            showCouponLayout.animation = (AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left))
        }

        couponCodeEdText.addTextChangedListener(object : TextWatcher{

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onCouponText(s.toString())

            }

        })


        applyCouponButton.setOnClickListener {

           when(couponDirection) {

               false ->  {
                   viewModel.applyCoupon("1", finalPrice.toString())
                   couponCodeEdText.isEnabled = false
                   applyCouponButton.text = "Remove"
                   couponDirection = true
               }
               true -> {
                   viewModel.removeCoupon(finalPrice.toString())
                   applyCouponButton.text = "Apply"
                   couponCodeEdText.isEnabled = true

                   couponDirection = false



               }


           }
        }






    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.checkOutProgress.observe(this, Observer {

            checkOutProgress.visibility = if (it) View.VISIBLE else View.GONE

        })

        viewModel.launchSuccess.observe(this, Observer {

            val intent = Intent(this, PaymentStatus::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)



        })

        viewModel.couponProgress.observe(this, Observer {

            if(it == true){
                couponProgress.visibility = View.VISIBLE
                viewModel.offAmoumt.observe(this, Observer {

                    couponAmount.text = it+"INR"

                })
                viewModel.totalAmount.observe(this, Observer {

                    grandTotalAmount.text = it+"INR"
                })


            }
            else {
                couponProgress.visibility = View.GONE


            }
        })

        viewModel.couponError.observe(this, Observer {

            Toaster.show(this, it)
        })




    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transferList = intent.getSerializableExtra("transfer") as List<Transfer>
        selectedTime = intent.getStringExtra("seltime")
        selectedDate = intent.getStringExtra("seldate")

        serviceItemsRecycler.adapter =  ItemsAdapter(transferList!!)
        serviceOptedText.text = "Services Opted on $selectedDate at $selectedTime"

        for(i in 0..transferList!!.size-1){

            finalPrice = finalPrice?.plus(transferList!![i].price.toInt())

        }

        val timeForServer = selectedTime!!.substring(0, selectedTime!!.indexOf("-"))
        Log.d("OL", timeForServer)
        viewModel.onOrderTime(timeForServer)
        viewModel.onOrderDate(selectedDate.toString())
        viewModel.onMerchantId("1")
        viewModel.onServiceIds(transferList!!)
        serviceTotalAmount.text = finalPrice.toString()+"INR"
        grandTotalAmount.text = finalPrice.toString()+"INR"








    }

}
