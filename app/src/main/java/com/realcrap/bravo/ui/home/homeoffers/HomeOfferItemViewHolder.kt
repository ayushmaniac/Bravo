package com.realcrap.bravo.ui.home.homeoffers

import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.lifecycle.Observer
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ViewHolderComponent
import com.realcrap.bravo.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.homeoffers_row.view.*

class HomeOfferItemViewHolder (parent: ViewGroup) : BaseItemViewHolder<HomeOffers, HomeOfferItemViewModel>(
        R.layout.homeoffers_row, parent
)
{
    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) = viewHolderComponent.inject(this)

    override fun setupView(view: View) {

        viewModel.data.observe(this, Observer {
            itemView.homeOfferText.text = it.homeOffer
        })

    }


}