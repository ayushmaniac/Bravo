package com.realcrap.bravo.ui.checkout.items

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.realcrap.bravo.ui.base.BaseAdapter

class ItemsAdapter(
        parentLifecycle: Lifecycle,
        itemsArrayList : ArrayList<Items>
) : BaseAdapter<Items, ItemsViewHolder>(
        itemsArrayList,
        parentLifecycle
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder = ItemsViewHolder(parent)


}