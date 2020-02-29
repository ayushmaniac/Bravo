package com.realcrap.bravo.ui.location

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_location.*

class Location : BaseActivity<LocationViewModel>() {
    override fun provideLayoutId(): Int = R.layout.activity_location

    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {

        cityOne.setOnClickListener{
            viewModel.onCityOneSelected()
        }

        cityTwo.setOnClickListener{
            viewModel.onCityTwoSelected()
        }

    }

    override fun setupObservers() {
        super.setupObservers()

      viewModel.cityOneData.observe(this, Observer {

          startActivity(Intent(applicationContext, MainActivity::class.java))
      })

        viewModel.cityTwoData.observe(this, Observer {

            startActivity(Intent(applicationContext, MainActivity::class.java))

        })


    }


}
