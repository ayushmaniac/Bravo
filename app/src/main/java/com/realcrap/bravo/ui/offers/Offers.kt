package com.realcrap.bravo.ui.offers


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.FragmentComponent
import com.realcrap.bravo.ui.base.BaseFragment
import com.realcrap.bravo.ui.home.HomeViewModel
import com.realcrap.bravo.ui.offers.offersutil.OfferAdapter
import dagger.multibindings.IntKey
import kotlinx.android.synthetic.main.fragment_offers.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class Offers :  BaseFragment<OffersViewModel>() {

    companion object {

        const val TAG = "Offers"

        fun newInstance() : Offers{
            val args = Bundle()
            val fragment = Offers()
            fragment.arguments = args
            return fragment
        }

    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var offerAdapter: OfferAdapter

    override fun provideLayoutId(): Int = R.layout.fragment_offers

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)


    override fun setupView(view: View) {

        offersRecyclerView.apply {
            adapter = offerAdapter
            layoutManager = linearLayoutManager
        }

    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.offersData.observe(this, Observer {

            offerAdapter.appendData(it)

        })
    }


}
