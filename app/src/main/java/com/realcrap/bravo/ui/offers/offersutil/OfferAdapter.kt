package com.realcrap.bravo.ui.offers.offersutil

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.realcrap.bravo.ui.base.BaseAdapter


class OfferAdapter (
        parentLifecycle: Lifecycle,
        offersArrayList : ArrayList<Offer>
): BaseAdapter<Offer, OfferViewHolder>(

        offersArrayList,
        parentLifecycle

){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder = OfferViewHolder(parent)


}