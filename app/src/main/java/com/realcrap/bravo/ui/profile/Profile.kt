package com.realcrap.bravo.ui.profile


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.FragmentComponent
import com.realcrap.bravo.ui.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 */
class Profile : BaseFragment<ProfileViewModel>() {


    companion object {

        const val TAG = "Profile"

        fun newInstance() : Profile{
            val args = Bundle()
            val fragment = Profile()
            fragment.arguments = args
            return fragment
        }

    }

    override fun provideLayoutId(): Int = R.layout.fragment_userprofile

    override fun injectDependencies(fragmentComponent: FragmentComponent)  =
            fragmentComponent.inject(this)

    override fun setupView(view: View) {
    }


}
