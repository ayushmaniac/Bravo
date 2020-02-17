package com.realcrap.bravo.ui.offers.offersutil

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ViewHolderComponent
import com.realcrap.bravo.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.offers_row.view.*

class OfferViewHolder (parent: ViewGroup) : BaseItemViewHolder<Offer, OfferViewModel>(
        R.layout.offers_row, parent

) {
    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupView(view: View) {

        //later
    }

    override fun setupObserver() {
        super.setupObserver()
        viewModel.data.observe(this, Observer {

                itemView.offerName.text = it.offerName
                itemView.offerDesc.text = it.offerDesc

        })

        itemView.setOnClickListener{
            
        }
    }
}