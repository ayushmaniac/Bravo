package com.realcrap.bravo.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.FragmentComponent
import com.realcrap.bravo.ui.base.BaseFragment


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


    override fun provideLayoutId(): Int = R.layout.fragment_home


    override fun injectDependencies(fragmentComponent: FragmentComponent) {

        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {
    }

    override fun setupObservers() {
        super.setupObservers()
    }

}
