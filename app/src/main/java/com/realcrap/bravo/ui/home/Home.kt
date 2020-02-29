package com.realcrap.bravo.ui.home


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.FragmentComponent
import com.realcrap.bravo.ui.allsalons.AllSalons
import com.realcrap.bravo.ui.base.BaseFragment
import com.realcrap.bravo.ui.home.homeoffers.HomeOfferAdapter
import com.realcrap.bravo.ui.home.homesalons.HomeSalons
import com.realcrap.bravo.ui.home.homesalons.HomeSalonsAdapter
import com.realcrap.bravo.ui.location.Location
import com.realcrap.bravo.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class Home : BaseFragment<HomeViewModel>(){

    companion object {

        const val TAG = "Home"

        fun newInstance() : Home{
            val args = Bundle()
            val fragment = Home()
            fragment.arguments = args
            return fragment
        }

    }

    @Inject
    lateinit var offerLayoutManager: LinearLayoutManager

    @Inject
    lateinit var salonsLayoutManager: LinearLayoutManager

    @Inject
    lateinit var homeOfferAdapter: HomeOfferAdapter

    @Inject
    lateinit var homeSalonsAdapter: HomeSalonsAdapter


    override fun provideLayoutId(): Int = R.layout.fragment_home


    override fun injectDependencies(fragmentComponent: FragmentComponent) {

        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

        homeOffersRecyclerView.apply {
            adapter = homeOfferAdapter
            layoutManager = offerLayoutManager.apply {

                offerLayoutManager.orientation = LinearLayoutManager.HORIZONTAL

            }
        }

        homeSalonsRecyclerView.apply {
            adapter = homeSalonsAdapter
            layoutManager = salonsLayoutManager.apply {
                salonsLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            }
        }
        locationChange.setOnClickListener{

        startActivity(Intent(context, Location::class.java))
        }

        cardAllSalon.setOnClickListener {
            startActivity(Intent(context, AllSalons::class.java))

        }
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.homeOffersData.observe(this, Observer {
            homeOfferAdapter.appendData(it)
        })

        viewModel.homeSalonsData.observe(this, Observer {

            homeSalonsAdapter.appendData(it)
        })

        viewModel.cityData.observe(this, Observer {

            locationChange.text = it
        })


    }



}
