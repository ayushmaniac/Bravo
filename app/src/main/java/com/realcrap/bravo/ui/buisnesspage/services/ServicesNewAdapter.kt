package com.realcrap.bravo.ui.buisnesspage.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.realcrap.bravo.R
import kotlinx.android.synthetic.main.service_row_item.view.*

class ServicesNewAdapter(val salonServiceList : List<Services>) : RecyclerView.Adapter<ServicesNewAdapter.ServiceNewViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceNewViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.service_item_row, parent, false)
        return ServiceNewViewHolder(v)

    }

    override fun getItemCount(): Int = salonServiceList.size

    override fun onBindViewHolder(holder: ServiceNewViewHolder, position: Int) {
        holder.bindItems(salonServiceList[position])
    }


    class ServiceNewViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val serviceName: TextView? = itemView.tvserviceName
        val serviceDesc: TextView? = itemView.tvserviceDesc
        val servicePrice: TextView? = itemView.tvservicePrice

        fun bindItems(services: Services) {



            serviceName!!.text = services.title
            serviceDesc!!.text = services.excerpt
            servicePrice!!.text = services.price


        }
    }
}



