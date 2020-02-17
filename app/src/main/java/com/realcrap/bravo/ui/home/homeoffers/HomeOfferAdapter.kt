package com.realcrap.bravo.ui.home.homeoffers

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.realcrap.bravo.ui.base.BaseAdapter

class HomeOfferAdapter
    (parentLifecycle: Lifecycle,
    offersArrayList : ArrayList<HomeOffers>
    ): BaseAdapter<HomeOffers, HomeOfferItemViewHolder>(

    offersArrayList,
    parentLifecycle
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOfferItemViewHolder = HomeOfferItemViewHolder(parent)
}