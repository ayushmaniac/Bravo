package com.realcrap.bravo.ui.offers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.realcrap.bravo.R
import com.realcrap.bravo.ui.buisnesspage.services.Services
import com.realcrap.bravo.util.display.Toaster
import kotlinx.android.synthetic.main.offers_row.view.*

class OffersAdapter(
        val servicesList : List<OfferModel>,
        private val listener: OffersClickListener

) : RecyclerView.Adapter<OffersAdapter.OfferViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OfferViewHolder(layoutInflater.inflate(R.layout.offers_row, parent, false))
    }

    override fun getItemCount(): Int = servicesList.size


    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val item = servicesList[position]
        holder.bind(item)
        holder.itemView.offerImage.setOnClickListener{

            listener.onImageClicked(holder.itemView.offerImage, item)
        }

    }

    class OfferViewHolder (view : View) : RecyclerView.ViewHolder(view){

        fun bind(offer : OfferModel){

            itemView.offerName.text = offer.name
        }

    }
}