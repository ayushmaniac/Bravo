package com.realcrap.bravo.ui.home.homesalons

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ViewHolderComponent
import com.realcrap.bravo.ui.base.BaseItemViewHolder
import kotlinx.android.synthetic.main.home_salon_row.view.*

class HomeSalonsItemViewHolder (parent: ViewGroup) : BaseItemViewHolder<HomeSalons, HomeSalonsItemViewModel>(
        R.layout.home_salon_row, parent
)
{
    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) = viewHolderComponent.inject(this)

    override fun setupView(view: View) {






    }


    override fun setupObserver() {
        super.setupObserver()

        viewModel.name.observe(this, Observer {

            itemView.homeSalonName.text = it
        })

        viewModel.image.observe(this, Observer {

            Glide.with(itemView.context)
                    .load(it)
                    .into(itemView.homeSalonImage)
        })

    }


}