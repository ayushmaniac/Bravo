package com.realcrap.bravo.ui.offers


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.FragmentComponent
import com.realcrap.bravo.ui.base.BaseFragment
import com.realcrap.bravo.ui.home.HomeViewModel

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


    override fun provideLayoutId(): Int = R.layout.fragment_offers

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)


    override fun setupView(view: View) {
    }


}
