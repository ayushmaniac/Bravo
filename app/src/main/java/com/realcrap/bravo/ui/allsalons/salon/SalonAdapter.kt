package com.realcrap.bravo.ui.allsalons.salon

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.realcrap.bravo.ui.base.BaseAdapter

class SalonAdapter (parentLifecycle: Lifecycle,
                    salonsArrayList : ArrayList<Salon>
): BaseAdapter<Salon, SalonViewHolder>(

        salonsArrayList,
        parentLifecycle
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalonViewHolder = SalonViewHolder(parent)


    interface OnItemClickListener{

        fun onItemClick(){


        }
    }
}