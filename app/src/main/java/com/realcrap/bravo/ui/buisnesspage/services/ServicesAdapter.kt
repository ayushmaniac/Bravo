package com.realcrap.bravo.ui.buisnesspage.services

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.realcrap.bravo.ui.base.BaseAdapter


class ServicesAdapter(
        parentLifecycle: Lifecycle,
        servicesArrayList : ArrayList<Services>
) : BaseAdapter<Services, ServicesViewHolder>(
        servicesArrayList,
        parentLifecycle
)
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesViewHolder = ServicesViewHolder(parent)


}