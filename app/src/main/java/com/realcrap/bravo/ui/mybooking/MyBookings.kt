package com.realcrap.bravo.ui.mybooking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.mybooking.booking.BookingAdapter
import kotlinx.android.synthetic.main.activity_my_bookings.*
import javax.inject.Inject

class MyBookings : BaseActivity<MyBookingViewModel>() {


    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun provideLayoutId(): Int  = R.layout.activity_my_bookings

    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {

        viewModel.getOrderList()

        goBackBook.setOnClickListener {
            finish()
        }

        urBookingsRecyclerView.apply {
            layoutManager = linearLayoutManager

        }

    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.orderListWhole.observe(this, Observer {

            urBookingsRecyclerView.adapter = BookingAdapter(it.orders)

        })

        viewModel.orderListProgress.observe(this, Observer {
            bookProgressBar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

}
