package com.realcrap.bravo.ui.buisnesspage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ActivityComponent
import com.realcrap.bravo.ui.base.BaseActivity
import com.realcrap.bravo.ui.buisnesspage.services.ServicesAdapter
import com.realcrap.bravo.ui.checkout.Checkout
import com.realcrap.bravo.util.display.Toaster
import kotlinx.android.synthetic.main.activity_buisnesscord.*
import kotlinx.android.synthetic.main.activity_splash.*
import javax.inject.Inject

class Buisness : BaseActivity<BuisnessViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uniqueId=intent.getStringExtra("unique_id")
        buisnessProgress.visibility = View.VISIBLE
        viewModel.loadServices(uniqueId)

    }

    override fun provideLayoutId(): Int = R.layout.activity_buisnesscord

    override fun injectDependencies(activityComponent: ActivityComponent) = activityComponent.inject(this)

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var servicesAdapter : ServicesAdapter

    override fun setupView(savedInstanceState: Bundle?) {


        servicesRecyclerView.apply {
            layoutManager = linearLayoutManager.apply {
                true
            }
            adapter = servicesAdapter
        }

        proceedButton.setOnClickListener {

            startActivity(Intent(this, Checkout::class.java))
        }


    }


    override fun setupObservers() {
        super.setupObservers()

        viewModel.buisnessProgress.observe(this, Observer {

            if(it == false){
                buisnessProgress.visibility = View.GONE

                viewModel.buisnessData.observe(this, Observer {
                    it.data?.run { servicesAdapter.appendData(this) }

                })

                viewModel.buisnessName.observe(this, Observer {
                    merchantName.text = it
                })
                viewModel.buisnessType.observe(this, Observer {
                    merchantType.text = it
                })

                viewModel.buisnessAddress.observe(this, Observer {
                    merchantAddress.text = it
                })

                viewModel.buisnessRating.observe(this, Observer {
                    merchantRating.text = it
                })


            }

            viewModel.buisnessError.observe(this, Observer {

                if(it==true){

                    sad.visibility = View.VISIBLE
                    sad.playAnimation()

                }
            })
        })



    }

}
