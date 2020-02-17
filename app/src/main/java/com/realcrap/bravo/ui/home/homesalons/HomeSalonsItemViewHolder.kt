package com.realcrap.bravo.ui.home.homesalons

import android.view.View
import android.view.ViewGroup
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ViewHolderComponent
import com.realcrap.bravo.ui.base.BaseItemViewHolder
import java.util.Observer

class HomeSalonsItemViewHolder (parent: ViewGroup) : BaseItemViewHolder<HomeSalons, HomeSalonsItemViewModel>(
        R.layout.homeoffers_row, parent
)
{
    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) = viewHolderComponent.inject(this)

    override fun setupView(view: View) {


    }


}