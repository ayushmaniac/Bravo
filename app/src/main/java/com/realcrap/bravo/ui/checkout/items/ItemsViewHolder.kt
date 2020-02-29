package com.realcrap.bravo.ui.checkout.items

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ViewHolderComponent
import com.realcrap.bravo.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.service_item_row.view.*

class ItemsViewHolder (parent: ViewGroup): BaseItemViewHolder<Items, ItemsViewModel>
(
        R.layout.service_item_row,parent

) {
    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) = viewHolderComponent.inject(this)

    override fun setupView(view: View) {

        viewModel.data.observe(this, Observer {

            itemView.serviceContent.text = it.serviceName
        })
    }


}