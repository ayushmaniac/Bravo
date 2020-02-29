package com.realcrap.bravo.ui.referandearn

import android.os.Bundle
import android.view.View
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.FragmentComponent
import com.realcrap.bravo.ui.base.BaseFragment
import kotlinx.android.synthetic.main.refer.*

class Refer : BaseFragment<ReferViewModel>() {

    companion object {

        const val TAG = "Refer"

        fun newInstance(): Refer {
            val args = Bundle()
            val fragment = Refer()
            fragment.arguments = args
            return fragment
        }

    }

    override fun provideLayoutId(): Int  = R.layout.refer

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)

    override fun setupView(view: View) {

        lottieAnimationViewTwo.playAnimation()
    }
}
