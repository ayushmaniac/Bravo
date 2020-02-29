package com.realcrap.bravo.ui.allsalons.salon

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Observer
import com.realcrap.bravo.R
import com.realcrap.bravo.di.component.ViewHolderComponent
import com.realcrap.bravo.ui.base.BaseItemViewHolder
import com.realcrap.bravo.ui.buisnesspage.Buisness
import com.realcrap.bravo.ui.location.Location
import com.realcrap.bravo.util.common.Event
import com.realcrap.bravo.util.display.Toaster
import kotlinx.android.synthetic.main.allsalons_item.view.*

class SalonViewHolder (parent: ViewGroup) : BaseItemViewHolder<Salon, SalonViewModel>(
        R.layout.allsalons_item, parent
), SalonAdapter.OnItemClickListener
{

     var merchantIdR : String? = null

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) = viewHolderComponent.inject(this)

    override fun setupView(view: View) {

        itemView.merchantCard.setOnClickListener {
            viewModel.onMerchantClicked(adapterPosition)
            Toaster.show(itemView.context, viewModel.merchantId.value.toString())
            val intent = Intent(itemView.context ,Buisness::class.java)
            intent.putExtra("unique_id","1")
            startActivity(itemView.context, intent, null)  //todo


        }
    }

    override fun setupObserver() {
        super.setupObserver()


        viewModel.name.observe(this, Observer {

            itemView.salonName.text = it

        })

        viewModel.desc.observe(this, Observer {

            itemView.salonDesc.text = it
        })


        viewModel.rating.observe(this, Observer {
            itemView.salonRating.text = it.toString()
        })



    }


}