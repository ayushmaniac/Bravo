package com.realcrap.bravo.ui.buisnesspage.services

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.realcrap.bravo.ui.allsalons.salon.Salon
import com.realcrap.bravo.ui.base.BaseAdapter

typealias ItemSelectionListener<T> = (position: Int, data: T) -> Unit
class ServicesAdapter(
        parentLifecycle: Lifecycle,
        servicesArrayList : ArrayList<Services>,
        var itemSelectionListener: ItemSelectionListener<Services>? = null

) : BaseAdapter<Services, ServicesViewHolder>(
        servicesArrayList,
        parentLifecycle
)

{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder = ServicesViewHolder(parent){ position, servicesArrayList ->
        itemSelectionListener?.run { this(position, servicesArrayList) }
    }





}

