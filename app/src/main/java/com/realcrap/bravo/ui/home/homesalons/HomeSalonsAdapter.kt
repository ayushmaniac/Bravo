package com.realcrap.bravo.ui.home.homesalons

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.realcrap.bravo.ui.base.BaseAdapter

class HomeSalonsAdapter
(parentLifecycle: Lifecycle,
 offersArrayList : ArrayList<HomeSalons>
): BaseAdapter<HomeSalons,HomeSalonsItemViewHolder >(

        offersArrayList,
        parentLifecycle
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeSalonsItemViewHolder = HomeSalonsItemViewHolder(parent)
}