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
import com.realcrap.bravo.util.display.Toaster
import dagger.multibindings.IntKey
import kotlinx.android.synthetic.main.fragment_offers.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class Offers :  BaseFragment<OffersViewModel>(), OffersClickListener {

    var offerlist : List<OfferModel>? = null

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



    override fun provideLayoutId(): Int = R.layout.fragment_offers

    override fun injectDependencies(fragmentComponent: FragmentComponent) = fragmentComponent.inject(this)


    override fun setupView(view: View) {

        offersRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = offerlist?.let { OffersAdapter(it, this@Offers) }
        }

    }

    override fun setupObservers() {
        super.setupObservers()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        offerlist = listOf(
                OfferModel("1", "2"),
                OfferModel("2", "@")
        )
    }

    override fun onImageClicked(view: View, offerModel: OfferModel) {
        Toaster.show(requireContext(), offerModel.name)
    }


}
