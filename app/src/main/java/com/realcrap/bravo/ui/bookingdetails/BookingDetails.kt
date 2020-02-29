package com.realcrap.bravo.ui.bookingdetails

import android.os.Bundle
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import kotlinx.android.synthetic.main.bookingdetails.*

class BookingDetails : BaseActivity<BookingDetailsViewModel>() {


    override fun provideLayoutId(): Int = R.layout.bookingdetails
    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {

    }
}